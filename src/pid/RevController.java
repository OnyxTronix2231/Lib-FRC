package frc.robot.sparkPid;

import com.revrobotics.CANSparkMax;
import frc.robot.sparkEncoder.RevEncoder;
import pid.AbstractController;
import pid.PIDFTerms;

import static pid.CtreConstants.*;

public abstract class RevController extends AbstractController {

    protected CANSparkMax sparkMax;
    protected RevEncoder revEncoder;
    protected int slotId;

    protected double firstError;

    public RevController(CANSparkMax canSparkMax,
                         PIDFTerms pidfTerms) {
        this(canSparkMax, pidfTerms, DEFAULT_SLOT_IDX);
    }

    public RevController(CANSparkMax canSparkMax,
                         double kP, double kI, double kD, double kF, int slotIdx) {
        this(canSparkMax, new PIDFTerms(kP, kI, kD, kF), slotIdx);
    }

    public RevController(CANSparkMax sparkMax,
                         double kP, double kI, double kD, double kF) {
        this(sparkMax, new PIDFTerms(kP, kI, kD, kF), DEFAULT_SLOT_IDX);
    }

    public RevController(CANSparkMax sparkMax,
                         PIDFTerms pidfTerms, int slotIdx) {
        super(pidfTerms);
        this.sparkMax = sparkMax;
        this.slotId = slotIdx;
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
        this.sparkMax.getPIDController().setReference(setpoint / 42, controlType, slotId, feedForward);
    }

    protected void setReference(double setpoint, CANSparkMax.ControlType controlType) {
        setReference(setpoint,controlType,0,0);
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
