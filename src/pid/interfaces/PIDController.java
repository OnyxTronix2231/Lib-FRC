package pid.interfaces;

import pid.PIDControlMode;

public interface PIDController extends Controller {
  double getProcessVariable();

  void restartControllerState();
}
