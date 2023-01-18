package pid;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import motors.RevMotorType;
import sensors.counter.Counter;

public class RevSmartMotionController extends RevController {

    private final SparkMaxPIDController controller;
    private double maxAcceleration;
    private double minVelocity;
    private double maxVelocity;
    private double minOutput;
    private double maxOutput;
    private double kIZ;
    private double tolerance;

    public RevSmartMotionController(CANSparkMax sparkMax, RevMotorType revMotorType, Counter counter,
                                    PIDFTerms pidfTerms, double maxAcceleration, double minVelocity, double maxVelocity, double tolerance) {
        this(sparkMax, revMotorType, counter, pidfTerms.getKp(), pidfTerms.getKi(), pidfTerms.getKd(),
                pidfTerms.getKf(), 0, maxAcceleration,
                minVelocity, maxVelocity, tolerance);
    }

    public RevSmartMotionController(CANSparkMax sparkMax, RevMotorType revMotorType, Counter counter,
                                    double kP, double kI, double kD, double kF, double maxAcceleration,
                                    double minVelocity, double maxVelocity, double tolerance) {
        this(sparkMax, revMotorType, counter, kP, kI, kD, kF, 0, maxAcceleration, minVelocity, maxVelocity, tolerance);
    }

    public RevSmartMotionController(CANSparkMax sparkMax, RevMotorType revMotorType, Counter counter,
                                    PIDFTerms pidfTerms, int slotId, double maxAcceleration,
                                    double minVelocity, double maxVelocity, double tolerance) {
        this(sparkMax, revMotorType, counter, pidfTerms.getKp(), pidfTerms.getKi(), pidfTerms.getKd(),
                pidfTerms.getKf(), slotId, maxAcceleration, minVelocity, maxVelocity, tolerance);
    }

    public RevSmartMotionController(CANSparkMax sparkMax, RevMotorType revMotorType, Counter counter,
                                    double kP, double kI, double kD, double kF, int slotId,
                                    double maxAcceleration, double minVelocity, double maxVelocity, double tolerance) {
        super(sparkMax, revMotorType, counter, kP, kI, kD, kF, slotId);
        this.controller = sparkMax.getPIDController();
        setMaxAcceleration(maxAcceleration);
        setMinAndMaxVelocity(minVelocity, maxVelocity);
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
        return this.encoder.getCount();
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
        setMinAndMaxVelocity(minVelocity, maxVelocity);
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

    public void setMinAndMaxVelocity(double minVelocity, double maxVelocity) {
        this.minVelocity = minVelocity;
        this.maxVelocity = maxVelocity;
        this.controller.setSmartMotionMinOutputVelocity(minVelocity, slotId);
        this.controller.setSmartMotionMaxVelocity(maxVelocity, slotId);
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
