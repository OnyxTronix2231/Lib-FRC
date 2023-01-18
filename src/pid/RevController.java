package pid;

import com.revrobotics.CANSparkMax;
import motors.RevMotorType;
import sensors.counter.Counter;
import sensors.counter.RevEncoder;

public abstract class RevController extends AbstractController {

    protected CANSparkMax sparkMax;
    protected Counter encoder;
    protected RevMotorType revMotorType;
    protected int slotId;
    protected double firstError;

    public RevController(CANSparkMax canSparkMax, RevMotorType revMotorType, Counter encoder,
                         PIDFTerms pidfTerms) {
        this(canSparkMax, revMotorType,encoder, pidfTerms, 0);
    }

    public RevController(CANSparkMax canSparkMax, RevMotorType revMotorType, Counter encoder,
                         double kP, double kI, double kD, double kF, int slotId) {
        this(canSparkMax, revMotorType,encoder, new PIDFTerms(kP, kI, kD, kF), slotId);
    }

    public RevController(CANSparkMax sparkMax, RevMotorType revMotorType, Counter encoder,
                         double kP, double kI, double kD, double kF) {
        this(sparkMax, revMotorType, encoder,new PIDFTerms(kP, kI, kD, kF), 0);
    }

    public RevController(CANSparkMax sparkMax, RevMotorType revMotorType,Counter encoder,
                         PIDFTerms pidfTerms, int slotId) {
        super(pidfTerms);
        this.sparkMax = sparkMax;
        this.revMotorType = revMotorType;
        this.slotId = slotId;
        this.encoder = encoder;
    }

    public CANSparkMax getSparkMax() {
        return sparkMax;
    }

    public Counter getCounter() {
        return encoder;
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
        encoder.reset();
    }
}
