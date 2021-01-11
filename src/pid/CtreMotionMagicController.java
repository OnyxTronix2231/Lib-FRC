package pid;

import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import pid.interfaces.MotionMagicController;
import sensors.counter.CtreEncoder;

public class CtreMotionMagicController extends CtreController implements MotionMagicController {

  public CtreMotionMagicController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                                   PIDFTerms pidfTerms) {
    super(motorControllerEnhanced, ctreEncoder, pidfTerms);
  }

  public CtreMotionMagicController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder, double kP,
                                   double kI, double kD, double kF, int pidSlot, int timeoutMs) {
    super(motorControllerEnhanced, ctreEncoder, kP, kI, kD, kF, pidSlot, timeoutMs);
  }

  public CtreMotionMagicController(IMotorControllerEnhanced motorControllerEnhanced, CtreEncoder ctreEncoder,
                                   PIDFTerms pidfTerms, int pidSlot, int timeoutMs) {
    super(motorControllerEnhanced, ctreEncoder, pidfTerms, pidSlot, timeoutMs);
  }
}
