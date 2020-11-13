package pid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import pid.interfaces.MotionMagicController;
import sensors.counter.CtreEncoder;

public class CtreMotionMagicController extends CtreController implements MotionMagicController {
  private double maxAcceleration;
  private double cruiseVelocity;

  public CtreMotionMagicController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder) {
    super(motorControllerEnhanced, ctreEncoder);
  }

  public CtreMotionMagicController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                                   int pidSlot, int timeoutMs) {
    super(motorControllerEnhanced, ctreEncoder, pidSlot, timeoutMs);
  }

  public CtreMotionMagicController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                                   double kP, double kI, double kD, double kF, int pidSlot, int timeoutMs) {
    super(motorControllerEnhanced, ctreEncoder, kP, kI, kD, kF, pidSlot, timeoutMs);
  }

  @Override
  public void enable() {
    this.ctreMotorController.set(ControlMode.MotionMagic, this.setpoint);
  }
}
