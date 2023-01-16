package pid;

import com.revrobotics.CANSparkMax;
import sensors.counter.RevEncoder;
import pid.AbstractController;
import pid.PIDFTerms;

import static frc.robot.sparkPid.RevConstant.*;

public abstract class RevController extends AbstractController {

    protected CANSparkMax sparkMax;
    protected RevEncoder revEncoder;
    protected int slotId;
    protected double firstError;

    public RevController(CANSparkMax canSparkMax,
                         PIDFTerms pidfTerms) {
        this(canSparkMax, pidfTerms, DEFAULT_SLOT_ID);
    }

    public RevController(CANSparkMax canSparkMax,
                         double kP, double kI, double kD, double kF, int slotId) {
        this(canSparkMax, new PIDFTerms(kP, kI, kD, kF), slotId);
    }

    public RevController(CANSparkMax sparkMax,
                         double kP, double kI, double kD, double kF) {
        this(sparkMax, new PIDFTerms(kP, kI, kD, kF), DEFAULT_SLOT_ID);
    }

    public RevController(CANSparkMax sparkMax,
                         PIDFTerms pidfTerms, int slotId) {
        super(pidfTerms);
        this.sparkMax = sparkMax;
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

    protected void setReference(double setpoint, CANSparkMax.ControlType controlType, int slotId, double feedForward) {
        this.sparkMax.getPIDController().setReference(setpoint / DEFAULT_ENCODER_UNITS_PER_ROTATION, controlType, slotId, feedForward);
    }

    protected void setReference(double setpoint, CANSparkMax.ControlType controlType) {
        setReference(setpoint, controlType, ZERO, ZERO);
    }

    @Override
    public void disable() {
        this.sparkMax.getPIDController().setReference(ZERO, CANSparkMax.ControlType.kVoltage);
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
//by.Ben
