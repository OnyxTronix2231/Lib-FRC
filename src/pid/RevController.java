package pid;

import com.revrobotics.CANSparkMax;
import sensors.counter.Counter;
import sensors.counter.RevCounter;

public abstract class RevController extends AbstractController {

    protected CANSparkMax sparkMax;
    protected RevCounter encoder;
    protected int slotId;
    protected double firstError;

    public RevController(CANSparkMax canSparkMax, RevCounter encoder,
                         PIDFTerms pidfTerms) {
        this(canSparkMax, encoder, pidfTerms, 0);
    }

    public RevController(CANSparkMax canSparkMax, RevCounter encoder,
                         double kP, double kI, double kD, double kF, int slotId) {
        this(canSparkMax, encoder, new PIDFTerms(kP, kI, kD, kF), slotId);
    }

    public RevController(CANSparkMax sparkMax, RevCounter encoder,
                         double kP, double kI, double kD, double kF) {
        this(sparkMax, encoder, new PIDFTerms(kP, kI, kD, kF), 0);
    }

    public RevController(CANSparkMax sparkMax, RevCounter encoder,
                         PIDFTerms pidfTerms, int slotId) {
        super(pidfTerms);
        this.sparkMax = sparkMax;
        this.encoder = encoder;
        this.slotId = slotId;
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
        this.sparkMax.getPIDController().setReference(setpoint / encoder.getRevCounterType().getEncoderUnitsPerRotation(), controlType, slotId, feedForward);
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
