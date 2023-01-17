package pid;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import pid.PIDFTerms;

import static pid.RevConstant.*;

public class RevSmartMotionController extends RevController {

    private final SparkMaxPIDController sparkMaxPIDController;
    private final double maxAcceleration;
    private final double maxVelocity;
    private final double minVelocity;
    private final double minOutput;
    private final double maxOutput;
    private final double kIZ;

    public RevSmartMotionController(CANSparkMax sparkMax,
                                    PIDFTerms pidfTerms, double maxAcceleration, double maxVelocity, double minVelocity) {
        this(sparkMax, pidfTerms.getKp(), pidfTerms.getKi(), pidfTerms.getKd(),
                pidfTerms.getKf(), DEFAULT_SLOT_ID, maxAcceleration,
                maxVelocity, minVelocity);
    }

    public RevSmartMotionController(CANSparkMax sparkMax, double kP,
                                    double kI, double kD, double kF, double maxAcceleration,
                                    double maxVelocity, double minVelocity) {
        this(sparkMax, kP, kI, kD, kF, DEFAULT_SLOT_ID, maxAcceleration, maxVelocity, minVelocity);
    }

    public RevSmartMotionController(CANSparkMax sparkMax,
                                    PIDFTerms pidfTerms, int slotId, int maxAcceleration,
                                    int maxVelocity, int minVelocity) {
        this(sparkMax, pidfTerms.getKp(), pidfTerms.getKi(), pidfTerms.getKd(),
                pidfTerms.getKf(), slotId, maxAcceleration, maxVelocity, minVelocity);
    }

    public RevSmartMotionController(CANSparkMax sparkMax, double kP,
                                    double kI, double kD, double kF, int slotId,
                                    int maxAcceleration, int maxVelocity, int minVelocity) {
        super(sparkMax, kP, kI, kD, kF, slotId);
        this.sparkMaxPIDController = sparkMax.getPIDController();
        setMaxAcceleration(maxAcceleration);
        setMaxAndMinVelocity(maxVelocity, minVelocity);
        setOutputRange(DEFAULT_MIN_OUTPUT, DEFAULT_MAX_OUTPUT);
        setIZone(kIZ);
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
        setReference(setpoint, CANSparkMax.ControlType.kSmartMotion, slotId, feedForward);
    }

    @Override
    public void update(double setpoint) {
        this.setSetpoint(setpoint);
        setReference(setpoint, CANSparkMax.ControlType.kSmartMotion);
    }

    @Override
    public void update(double setpoint, double feedForward) {
        this.setSetpoint(setpoint);
        setReference(setpoint, CANSparkMax.ControlType.kSmartMotion, slotId, feedForward);
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
        setOutputRange(minOutput,maxOutput);
        setIZone(kIZ);

    }

    public void setMaxAcceleration(double maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
        this.sparkMaxPIDController.setSmartMotionMaxAccel(maxAcceleration, slotId);
    }

    public double getMaxAcceleration() {
        return sparkMaxPIDController.getSmartMotionMaxAccel(slotId);
    }

    public double getMaxVelocity() {
        return sparkMaxPIDController.getSmartMotionMaxVelocity(slotId);
    }

    public void setMaxAndMinVelocity(double maxVelocity, double minVelocity) {
        this.maxVelocity = maxVelocity;
        this.maxVelocity = minVelocity;
        this.sparkMaxPIDController.setSmartMotionMaxVelocity(maxVelocity, slotId);
        this.sparkMaxPIDController.setSmartMotionMinOutputVelocity(minVelocity, slotId);
    }

    public void setTolerance(double tolerance) {
        this.sparkMaxPIDController.setSmartMotionAllowedClosedLoopError(tolerance, slotId);
    }

    public void setOutputRange(double min, double max) {
        this.minOutput = min;
        this.maxOutput = max;
        sparkMaxPIDController.setOutputRange(min, max);
    }

    public void setIZone(double kIZ) {
        this.kIZ = kIZ;
        sparkMaxPIDController.setIZone(kIZ);
    }

    public double getMinOutputRange() {
        return sparkMaxPIDController.getOutputMin();
    }

    public double getMaxOutputRange() {
        return sparkMaxPIDController.getOutputMax();
    }
}
//by.Ben
