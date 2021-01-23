package pid;

import static pid.CtreConstants.CTRE_DEVICE_CALLS_TIMEOUT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import pid.interfaces.MotionMagicController;
import sensors.counter.CtreEncoder;

public class CtreMotionMagicController extends CtreController implements MotionMagicController {

  private int acceleration;
  private int cruiseVelocity;
  private int accelerationSmoothing;

  public CtreMotionMagicController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                                   PIDFTerms pidfTerms, int acceleration, int cruiseVelocity,
                                   int accelerationSmoothing) {
    super(motorControllerEnhanced, ctreEncoder, pidfTerms);
    this.acceleration = acceleration;
    this.cruiseVelocity = cruiseVelocity;
    this.accelerationSmoothing =accelerationSmoothing;
  }

  public CtreMotionMagicController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder, double kP,
                                   double kI, double kD, double kF, int slotIdx, int pidIdx, int timeoutMs, int acceleration,
                                   int cruiseVelocity, int accelerationSmoothing) {
    super(motorControllerEnhanced, ctreEncoder, kP, kI, kD, kF, slotIdx, pidIdx, timeoutMs);
    this.acceleration = acceleration;
    this.cruiseVelocity = cruiseVelocity;
    this.accelerationSmoothing =accelerationSmoothing;
  }

  public CtreMotionMagicController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder, double kP,
                                   double kI, double kD, double kF, int acceleration,
                                   int cruiseVelocity, int accelerationSmoothing) {
    super(motorControllerEnhanced, ctreEncoder, kP, kI, kD, kF);
    this.acceleration = acceleration;
    this.cruiseVelocity = cruiseVelocity;
    this.accelerationSmoothing =accelerationSmoothing;
  }

  public CtreMotionMagicController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                                   PIDFTerms pidfTerms, int slotIdx, int pidIdx, int timeoutMs, int acceleration,
                                   int cruiseVelocity, int accelerationSmoothing) {
    super(motorControllerEnhanced, ctreEncoder, pidfTerms, slotIdx, pidIdx, timeoutMs);
    this.acceleration = acceleration;
    this.cruiseVelocity = cruiseVelocity;
    this.accelerationSmoothing =accelerationSmoothing;
  }

  @Override
  public void enable() {
    super.enable();
    this.ctreMotorController.selectProfileSlot(slotIdx, pidIdx);
    this.ctreMotorController.set(ControlMode.MotionMagic, setpoint);
  }

  @Override
  public void enable(double feedForward) {
    super.enable(feedForward);
    firstError = getCurrentError();
    this.ctreMotorController.selectProfileSlot(slotIdx, pidIdx);
    this.ctreMotorController.set(ControlMode.MotionMagic, setpoint, DemandType.ArbitraryFeedForward, feedForward);
  }

  @Override
  public void update(double setpoint) {
    this.setSetpoint(setpoint);
    this.ctreMotorController.set(ControlMode.MotionMagic, setpoint);
  }

  @Override
  public void update(double setpoint, double feedForward) {
    this.setSetpoint(setpoint);
    this.ctreMotorController.set(ControlMode.MotionMagic, setpoint, DemandType.ArbitraryFeedForward, feedForward);
  }

  @Override
  public double getProcessVariable() {
    return this.ctreMotorController.getSelectedSensorPosition(pidIdx);
  }

  @Override
  public double getCurrentError() {
    return this.setpoint - getProcessVariable();
  }

  @Override
  public int getAcceleration() {
    return acceleration;
  }

  @Override
  public void setAcceleration(int acceleration) {
    this.acceleration = acceleration;
    this.ctreMotorController.configMotionAcceleration(acceleration, CTRE_DEVICE_CALLS_TIMEOUT);
  }

  @Override
  public int getCruiseVelocity() {
    return cruiseVelocity;
  }

  @Override
  public void setCruiseVelocity(int cruiseVelocity) {
    this.cruiseVelocity = cruiseVelocity;
    this.ctreMotorController.configMotionCruiseVelocity(cruiseVelocity, CTRE_DEVICE_CALLS_TIMEOUT);
  }

  @Override
  public int getAccelerationSmoothing() {
    return accelerationSmoothing;
  }

  @Override
  public void setAccelerationSmoothing(int accelerationSmoothing) {
    this.accelerationSmoothing = accelerationSmoothing;
    this.ctreMotorController.configMotionSCurveStrength(accelerationSmoothing, CTRE_DEVICE_CALLS_TIMEOUT);
  }

  @Override
  protected void configVariables(){
    super.configVariables();
    this.setAcceleration(acceleration);
    this.setCruiseVelocity(cruiseVelocity);
    this.setAccelerationSmoothing(accelerationSmoothing);
  }
}
