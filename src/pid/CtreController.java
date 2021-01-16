package pid;

import static pid.CtreConstants.CTRE_DEVICE_CALLS_TIMEOUT;
import static pid.CtreConstants.DEFAULT_PID_IDX;
import static pid.CtreConstants.DEFAULT_SLOT_IDX;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import sensors.counter.CtreEncoder;

public abstract class CtreController extends AbstractController {
  protected IMotorControllerEnhanced ctreMotorController;
  protected CtreEncoder ctreEncoder;
  protected int slotIdx;
  protected int pidIdx;
  protected int timeoutMs;
  /**
   To avoid returning true on the first calls of IsOnTarget due to the Talon's
   slow update rate, we need to make sure that the error has been updated
   */
  protected double firstError;

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                        PIDFTerms pidfTerms) {
    this(motorControllerEnhanced, ctreEncoder, pidfTerms, DEFAULT_SLOT_IDX, DEFAULT_PID_IDX, CTRE_DEVICE_CALLS_TIMEOUT);
  }

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                        double kP, double kI, double kD, double kF, int slotIdx, int pidIdx, int timeoutMs) {
    this(motorControllerEnhanced, ctreEncoder, new PIDFTerms(kP, kI, kD, kF), slotIdx, pidIdx, timeoutMs);
  }

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                        double kP, double kI, double kD, double kF) {
    this(motorControllerEnhanced, ctreEncoder, new PIDFTerms(kP, kI, kD, kF), DEFAULT_SLOT_IDX, DEFAULT_PID_IDX,
        CTRE_DEVICE_CALLS_TIMEOUT);
  }

  public CtreController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                        PIDFTerms pidfTerms, int slotIdx,int pidIdx, int timeoutMs) {
    super(pidfTerms);
    this.ctreMotorController = motorControllerEnhanced;
    this.ctreEncoder = ctreEncoder;
    this.slotIdx = slotIdx;
    this.pidIdx = pidIdx;
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

  @Override
  public void setPIDFTerms(double kP, double kI, double kD, double kF) {
    super.setPIDFTerms(kP, kI, kD, kF);
    ctreMotorController.config_kP(slotIdx, kP, this.timeoutMs);
    ctreMotorController.config_kI(slotIdx, kI, this.timeoutMs);
    ctreMotorController.config_kD(slotIdx, kD, this.timeoutMs);
    ctreMotorController.config_kF(slotIdx, kF, this.timeoutMs);
  }

  @Override
  public double getCurrentError() {
    return this.ctreMotorController.getClosedLoopError(pidIdx);
  }

  @Override
  public boolean isOnTarget(double tolerance) {
    if (getCurrentError() == firstError) {
      return false;
    }
    firstError = Integer.MIN_VALUE;
    return Math.abs(this.getCurrentError()) < tolerance;
  }

  @Override
  public boolean isOnTarget(double belowTolerance, double aboveTolerance) {
    if (getCurrentError() == firstError) {
      return false;
    }
    firstError = Integer.MIN_VALUE;
    return this.getCurrentError() > belowTolerance && this.getCurrentError() < aboveTolerance;
  }

  @Override
  public void disable() {
    this.ctreMotorController.set(ControlMode.PercentOutput, 0);
  }

  public void enable(double feedForward){
    firstError = getCurrentError();
  }

  public void enable(){
    firstError = getCurrentError();
  }

  public abstract void update(double setpoint, double feedForward);
}
