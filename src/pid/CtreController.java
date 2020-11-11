package pid;

import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import pid.interfaces.Controller;

public abstract class CtreController implements Controller {
  private IMotorControllerEnhanced motorControllerEnhanced;
  private int pidSlot;
}
