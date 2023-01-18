package pid;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import motors.RevMotorType;

import static pid.RevConstants.DEFAULT_SLOT_ID;

public class RevSmartMotionController extends RevController {

    private final SparkMaxPIDController controller;
    private double maxAcceleration;
    private double maxVelocity;
    private double minVelocity;
    private double minOutput;
    private double maxOutput;
    private double kIZ;
    private double tolerance;

    public RevSmartMotionController(CANSparkMax sparkMax, RevMotorType revMotorType,
                                    PIDFTerms pidfTerms, double maxAcceleration, double maxVelocity, double minVelocity, double tolerance) {
        this(sparkMax, revMotorType, pidfTerms.getKp(), pidfTerms.getKi(), pidfTerms.getKd(),
                pidfTerms.getKf(), DEFAULT_SLOT_ID, maxAcceleration,
                maxVelocity, minVelocity, tolerance);
    }

    public RevSmartMotionController(CANSparkMax sparkMax, RevMotorType revMotorType,
                                    double kP, double kI, double kD, double kF, double maxAcceleration,
                                    double maxVelocity, double minVelocity, double tolerance) {
        this(sparkMax, revMotorType, kP, kI, kD, kF, DEFAULT_SLOT_ID, maxAcceleration, maxVelocity, minVelocity, tolerance);
    }

    public RevSmartMotionController(CANSparkMax sparkMax, RevMotorType revMotorType,
                                    PIDFTerms pidfTerms, int slotId, double maxAcceleration,
                                    double maxVelocity, double minVelocity, double tolerance) {
        this(sparkMax, revMotorType, pidfTerms.getKp(), pidfTerms.getKi(), pidfTerms.getKd(),
                pidfTerms.getKf(), slotId, maxAcceleration, maxVelocity, minVelocity, tolerance);
    }

    public RevSmartMotionController(CANSparkMax sparkMax, RevMotorType revMotorType,
                                    double kP, double kI, double kD, double kF, int slotId,
                                    double maxAcceleration, double maxVelocity, double minVelocity, double tolerance) {
        super(sparkMax, revMotorType, kP, kI, kD, kF, slotId);
        this.controller = sparkMax.getPIDController();
        setMaxAcceleration(maxAcceleration);
        setMaxAndMinVelocity(maxVelocity, minVelocity);
        setOutputRange(controller.getOutputMin(), controller.getOutputMax());
        setIZone(kIZ);
        setTolerance(tolerance);
    }

    @Override
    public void enable() {
        super.enable();
        setReference(setpoint, CANSparkMax.ControlType.kSmartMotion);
    }

    @Override
    public void enable(double feedForward) {
        super.enable(feedForward);
        firstError = getCurrentError();
        setReference(setpoint, CANSparkMax.ControlType.kSmartMotion, feedForward);
    }

    @Override
    public void update(double setpoint) {
        this.setSetpoint(setpoint);
        setReference(setpoint, CANSparkMax.ControlType.kSmartMotion);
    }

    @Override
    public void update(double setpoint, double feedForward) {
        this.setSetpoint(setpoint);
        setReference(setpoint, CANSparkMax.ControlType.kSmartMotion, feedForward);
    }

    @Override
    public double getCurrentError() {
        return setpoint - getProcessVariable();
    }

    @Override
    public double getProcessVariable() {
        return this.revEncoder.getCount();
    }

    @Override
    public boolean isOnTarget(double belowTolerance, double aboveTolerance) {
        if (isFirstRun()) {
            return firstError > belowTolerance && firstError < aboveTolerance;
        }
        firstError = Integer.MIN_VALUE;
        return super.isOnTarget(belowTolerance, aboveTolerance);
    }

    @Override
    public boolean isOnTarget(double tolerance) {
        if (isFirstRun()) {
            return Math.abs(firstError) < tolerance;
        }
        firstError = Integer.MIN_VALUE;
        return super.isOnTarget(tolerance);
    }

    @Override
    protected void configVariables() {
        super.configVariables();
        setMaxAcceleration(maxAcceleration);
        setMaxAndMinVelocity(maxVelocity, minVelocity);
        setOutputRange(minOutput, maxOutput);
        setIZone(kIZ);
        setTolerance(tolerance);

    }

    public void setMaxAcceleration(double maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
        this.controller.setSmartMotionMaxAccel(maxAcceleration, slotId);
    }

    public double getMaxAcceleration() {
        return controller.getSmartMotionMaxAccel(slotId);
    }

    public double getMaxVelocity() {
        return controller.getSmartMotionMaxVelocity(slotId);
    }

    public void setMaxAndMinVelocity(double maxVelocity, double minVelocity) {
        this.maxVelocity = maxVelocity;
        this.minVelocity = minVelocity;
        this.controller.setSmartMotionMaxVelocity(maxVelocity, slotId);
        this.controller.setSmartMotionMinOutputVelocity(minVelocity, slotId);
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
        this.controller.setSmartMotionAllowedClosedLoopError(tolerance, slotId);
    }

    public void setOutputRange(double min, double max) {
        this.minOutput = min;
        this.maxOutput = max;
        controller.setOutputRange(min, max);
    }

    public void setIZone(double kIZ) {
        this.kIZ = kIZ;
        controller.setIZone(kIZ);
    }

    public double getMinOutputRange() {
        return controller.getOutputMin();
    }

    public double getMaxOutputRange() {
        return controller.getOutputMax();
    }
}
