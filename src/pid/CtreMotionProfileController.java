package pid;

import static pid.PIDConstants.TIMEOUT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import pid.interfaces.MotionProfilerController;
import sensors.counter.CtreEncoder;

public class CtreMotionProfileController extends CtreController implements MotionProfilerController {

  public CtreMotionProfileController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder) {
    super(motorControllerEnhanced, ctreEncoder);
  }

  public CtreMotionProfileController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                                     int pidSlot, int timeoutMs) {
    super(motorControllerEnhanced, ctreEncoder, pidSlot, timeoutMs);
  }

  public CtreMotionProfileController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                                     double kP, double kI, double kD, double kF, int pidSlot, int timeoutMs) {
    super(motorControllerEnhanced, ctreEncoder, kP, kI, kD, kF, pidSlot, timeoutMs);
  }

  @Override
  public void enable() {
    this.ctreMotorController.set(ControlMode.MotionProfile, this.setpoint);
  }
}
