package pid;

import static pid.CtreConstants.CTRE_DEVICE_CALLS_TIMEOUT;
import static pid.CtreConstants.DEFAULT_PID_IDX;
import static pid.CtreConstants.DEFAULT_SLOT_IDX;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import sensors.counter.Counter;

public abstract class CtreController extends AbstractController {
  protected IMotorControllerEnhanced ctreMotorController;
  protected Counter encoder;
  protected int slotIdx;
  protected int pidIdx;
  protected int timeoutMs;
  /**
   To avoid returning true on the first calls of IsOnTarget due to the Talon's
   slow update rate, we need to make sure that the error has been updated
   */
  protected double firstError;

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, Counter ctreEncoder,
                        PIDFTerms pidfTerms) {
    this(motorControllerEnhanced, ctreEncoder, pidfTerms, DEFAULT_SLOT_IDX, DEFAULT_PID_IDX, CTRE_DEVICE_CALLS_TIMEOUT);
  }

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, Counter ctreEncoder,
                        double kP, double kI, double kD, double kF, int slotIdx, int pidIdx, int timeoutMs) {
    this(motorControllerEnhanced, ctreEncoder, new PIDFTerms(kP, kI, kD, kF), slotIdx, pidIdx, timeoutMs);
  }

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, Counter ctreEncoder,
                        double kP, double kI, double kD, double kF) {
    this(motorControllerEnhanced, ctreEncoder, new PIDFTerms(kP, kI, kD, kF), DEFAULT_SLOT_IDX, DEFAULT_PID_IDX,
        CTRE_DEVICE_CALLS_TIMEOUT);
  }

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, Counter ctreEncoder,
                        PIDFTerms pidfTerms, int slotIdx,int pidIdx, int timeoutMs) {
    super(pidfTerms);
    this.ctreMotorController = motorControllerEnhanced;
    this.encoder = ctreEncoder;
    this.slotIdx = slotIdx;
    this.pidIdx = pidIdx;
    this.timeoutMs = timeoutMs;
    configVariables();
  }

  public IMotorControllerEnhanced getCtreMotorController() {
    return ctreMotorController;
  }

  public Counter getCtreEncoder() {
    return encoder;
  }

  public int getTimeoutMs() {
    return timeoutMs;
  }

  public void setTimeoutMs(int timeoutMs) {
    this.timeoutMs = timeoutMs;
  }

  @Override
  public void setPIDFTerms(PIDFTerms pidfTerms) {
    super.setPIDFTerms(pidfTerms);
    ctreMotorController.config_kP(slotIdx, pidfTerms.getKp(), this.timeoutMs);
    ctreMotorController.config_kI(slotIdx, pidfTerms.getKi(), this.timeoutMs);
    ctreMotorController.config_kD(slotIdx, pidfTerms.getKd(), this.timeoutMs);
    ctreMotorController.config_kF(slotIdx, pidfTerms.getKf(), this.timeoutMs);
  }

  protected boolean isFirstRun() {
    return getCurrentError() == firstError;
  }

  @Override
  public void disable() {
    this.ctreMotorController.set(ControlMode.PercentOutput, 0);
  }

  public void enable(double feedForward){
    firstError = getCurrentError();
    configVariables();
  }

  public void enable(){
    firstError = getCurrentError();
    configVariables();
  }

  protected void configVariables() {
    this.setPIDFTerms(pidfTerms);
    ctreMotorController.selectProfileSlot(slotIdx, pidIdx);
  }

  public abstract void update(double setpoint, double feedForward);

  public void resetEncoder() {
    encoder.reset();
  }
}
