package pid;

import static pid.PIDConstants.CTRE_DEVICE_CALLS_TIMEOUT;
import static pid.PIDConstants.DEFAULT_PID_SLOT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import sensors.counter.CtreEncoder;

public abstract class CtreController extends AbstractController {
  protected IMotorControllerEnhanced ctreMotorController;
  protected CtreEncoder ctreEncoder;
  protected int pidIdx;
  protected int timeoutMs;

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                        PIDFTerms pidfTerms) {
    this(motorControllerEnhanced, ctreEncoder, pidfTerms, DEFAULT_PID_SLOT, CTRE_DEVICE_CALLS_TIMEOUT);
  }

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                        double kP, double kI, double kD, double kF, int pidSlot, int timeoutMs) {
    this(motorControllerEnhanced, ctreEncoder, new PIDFTerms(kP, kI, kD, kF), pidSlot, timeoutMs);
  }

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                        PIDFTerms pidfTerms, int pidSlot, int timeoutMs) {
    super(pidfTerms);
    this.ctreMotorController = motorControllerEnhanced;
    this.ctreEncoder = ctreEncoder;
    this.pidIdx = pidSlot;
    this.timeoutMs = timeoutMs;
  }

  public IMotorControllerEnhanced getCtreMotorController() {
    return ctreMotorController;
  }

  public CtreEncoder getCtreEncoder() {
    return ctreEncoder;
  }

  public int getTimeoutMs() {
    return timeoutMs;
  }

  public void setTimeoutMs(int timeoutMs) {
    this.timeoutMs = timeoutMs;
  }

  public int getPidIdx() {
    return this.pidIdx;
  }

  public void setPidIdx(int pidIdx) {
    this.pidIdx = pidIdx;
  }

  @Override
  public void setPIDFTerms(double kP, double kI, double kD, double kF) {
    super.setPIDFTerms(kP, kI, kD, kF);
    ctreMotorController.config_kP(pidIdx, kP, this.timeoutMs);
    ctreMotorController.config_kI(pidIdx, kI, this.timeoutMs);
    ctreMotorController.config_kD(pidIdx, kD, this.timeoutMs);
    ctreMotorController.config_kF(pidIdx, kF, this.timeoutMs);
  }

  @Override
  public void disable() {
    this.ctreMotorController.set(ControlMode.PercentOutput, 0);
  }
}
