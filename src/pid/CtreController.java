package pid;

import static pid.PIDConstants.TIMEOUT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import pid.interfaces.Controller;
import sensors.counter.CtreEncoder;

public abstract class CtreController extends AbstractController {
  protected IMotorControllerEnhanced ctreMotorController;
  protected CtreEncoder ctreEncoder;
  protected int pidSlot;
  protected int timeoutMs;

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder) {
    super(0, 0, 0, 0);
    this.ctreMotorController = motorControllerEnhanced;
    this.ctreEncoder = ctreEncoder;
    this.pidSlot = 0;
    this.timeoutMs = TIMEOUT;
  }

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                        int pidSlot, int timeoutMs) {
    super(0, 0, 0, 0);
    this.ctreMotorController = motorControllerEnhanced;
    this.ctreEncoder = ctreEncoder;
    this.pidSlot = pidSlot;
    this.timeoutMs = timeoutMs;
  }

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                        double kP, double kI, double kD, double kF, int pidSlot, int timeoutMs) {
    super(kP, kI, kD, kF);
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

  public void setRampSec(double rampSec) {
    this.ctreMotorController.configClosedloopRamp(rampSec, timeoutMs);
  }

  public void disableRamp() {
    this.setRampSec(0);
  }

  public void setVoltageCompensation(double voltage) {
    this.ctreMotorController.configVoltageCompSaturation(voltage, timeoutMs);
    this.ctreMotorController.enableVoltageCompensation(true);
  }

  public void disableVoltageCompensation() {
    this.ctreMotorController.enableVoltageCompensation(false);
  }

  public void setCurrentLimits(boolean enable, double currentLimit, double triggerThresholdCurrent,
                               double triggerThresholdTime) {
    if (this.ctreMotorController instanceof TalonFX) {
      ((TalonFX) this.ctreMotorController).configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(
          enable, currentLimit, triggerThresholdCurrent, triggerThresholdTime), this.timeoutMs);
    } else if (this.ctreMotorController instanceof TalonSRX) {
      ((TalonSRX) this.ctreMotorController).configSupplyCurrentLimit(
          new SupplyCurrentLimitConfiguration(enable, currentLimit, triggerThresholdCurrent,
              triggerThresholdTime), this.timeoutMs);
    }
  }

  @Override
  public void disable() {
    this.ctreMotorController.set(ControlMode.PercentOutput, 0);
  }
}
