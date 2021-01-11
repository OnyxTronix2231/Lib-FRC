package pid;

import static pid.PIDConstants.CTRE_DEVICE_CALLS_TIMEOUT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import exceptions.UnsupportedControlModeException;
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
                                   double kI, double kD, double kF, int pidSlot, int timeoutMs, int acceleration,
                                   int cruiseVelocity, int accelerationSmoothing) {
    super(motorControllerEnhanced, ctreEncoder, kP, kI, kD, kF, pidSlot, timeoutMs);
    this.acceleration = acceleration;
    this.cruiseVelocity = cruiseVelocity;
    this.accelerationSmoothing =accelerationSmoothing;
  }

  public CtreMotionMagicController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                                   PIDFTerms pidfTerms, int pidSlot, int timeoutMs, int acceleration,
                                   int cruiseVelocity, int accelerationSmoothing) {
    super(motorControllerEnhanced, ctreEncoder, pidfTerms, pidSlot, timeoutMs);
    this.acceleration = acceleration;
    this.cruiseVelocity = cruiseVelocity;
    this.accelerationSmoothing =accelerationSmoothing;
  }


  @Override
  public double getCurrentError() {
    return this.ctreMotorController.getClosedLoopError(pidIdx);
  }

  @Override
  public void enable() {
    if (this.ctreMotorController.getControlMode() == ControlMode.MotionMagic){
      this.ctreMotorController.set(ControlMode.MotionMagic, setpoint);
    }
    throw new UnsupportedControlModeException(this.ctreMotorController.getControlMode().name());
  }

  @Override
  public void restartControllerState() {
    this.ctreMotorController.setIntegralAccumulator(0,
        this.pidIdx, this.timeoutMs);
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
}
