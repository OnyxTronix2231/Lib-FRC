package pid;

import com.revrobotics.CANSparkMax;
import motors.RevMotorType;
import sensors.counter.RevEncoder;

import static pid.RevConstants.DEFAULT_SLOT_ID;

public abstract class RevController extends AbstractController {

    protected CANSparkMax sparkMax;
    protected RevEncoder revEncoder;
    protected RevMotorType revMotorType;
    protected int slotId;
    protected double firstError;

    public RevController(CANSparkMax canSparkMax, RevMotorType revMotorType,
                         PIDFTerms pidfTerms) {
        this(canSparkMax, revMotorType, pidfTerms, DEFAULT_SLOT_ID);
    }

    public RevController(CANSparkMax canSparkMax, RevMotorType revMotorType,
                         double kP, double kI, double kD, double kF, int slotId) {
        this(canSparkMax, revMotorType, new PIDFTerms(kP, kI, kD, kF), slotId);
    }

    public RevController(CANSparkMax sparkMax, RevMotorType revMotorType,
                         double kP, double kI, double kD, double kF) {
        this(sparkMax, revMotorType, new PIDFTerms(kP, kI, kD, kF), DEFAULT_SLOT_ID);
    }

    public RevController(CANSparkMax sparkMax, RevMotorType revMotorType,
                         PIDFTerms pidfTerms, int slotId) {
        super(pidfTerms);
        this.sparkMax = sparkMax;
        this.revMotorType = revMotorType;
        this.slotId = slotId;
        this.revEncoder = new RevEncoder(sparkMax);
    }

    public CANSparkMax getSparkMax() {
        return sparkMax;
    }

    public RevEncoder getRevEncoder() {
        return revEncoder;
    }

    @Override
    public void setPIDFTerms(PIDFTerms pidfTerms) {
        super.setPIDFTerms(pidfTerms);
        sparkMax.getPIDController().setP(pidfTerms.getKp());
        sparkMax.getPIDController().setI(pidfTerms.getKi());
        sparkMax.getPIDController().setD(pidfTerms.getKd());
        sparkMax.getPIDController().setFF(pidfTerms.getKf());
    }

    protected boolean isFirstRun() {
        return getCurrentError() == firstError;
    }

    protected void setReference(double setpoint, CANSparkMax.ControlType controlType, double feedForward) {
        this.sparkMax.getPIDController().setReference(setpoint / revMotorType.getEncoderUnitsPerRotation(), controlType, slotId, feedForward);
    }

    protected void setReference(double setpoint, CANSparkMax.ControlType controlType) {
        setReference(setpoint, controlType, 0);
    }

    @Override
    public void disable() {
        this.sparkMax.getPIDController().setReference(0, CANSparkMax.ControlType.kVoltage);
    }

    public void enable(double feedForward) {
        firstError = getCurrentError();
        this.configVariables();
    }

    public void enable() {
        firstError = getCurrentError();
        this.configVariables();
    }

    protected void configVariables() {
        this.setPIDFTerms(pidfTerms);
    }

    public abstract void update(double setpoint, double feedForward);

    public void resetEncoder() {
        revEncoder.reset();
    }
}
