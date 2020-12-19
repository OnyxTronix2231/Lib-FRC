package pid;

import static pid.PIDConstants.CTRE_DEVICE_CALLS_TIMEOUT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import sensors.counter.CtreEncoder;

public abstract class CtreController extends AbstractController {
  protected IMotorControllerEnhanced ctreMotorController;
  protected CtreEncoder ctreEncoder;
  protected int pidSlot;
  protected int timeoutMs;

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                        PIDFTerms pidfTerms) {
    super(pidfTerms);
    this.ctreMotorController = motorControllerEnhanced;
    this.ctreEncoder = ctreEncoder;
    this.pidSlot = 0;
    this.timeoutMs = CTRE_DEVICE_CALLS_TIMEOUT;
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
    this.pidSlot = pidSlot;
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

  public int getPidSlot() {
    return this.pidSlot;
  }

  public void setPidSlot(int pidSlot) {
    this.pidSlot = pidSlot;
  }

  @Override
  public void setPIDFTerms(double kP, double kI, double kD, double kF) {
    super.setPIDFTerms(kP, kI, kD, kF);
    ctreMotorController.config_kP(pidSlot, kP, this.timeoutMs);
    ctreMotorController.config_kI(pidSlot, kI, this.timeoutMs);
    ctreMotorController.config_kD(pidSlot, kD, this.timeoutMs);
    ctreMotorController.config_kF(pidSlot, kF, this.timeoutMs);
  }

  @Override
  public void disable() {
    this.ctreMotorController.set(ControlMode.PercentOutput, 0);
  }
}
