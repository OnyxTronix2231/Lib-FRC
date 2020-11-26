package pid.interfaces;

import pid.PIDControlMode;

public interface PIDController extends Controller {
  double getProcessVariable();

  double getCurrentError();

  void enable(PIDControlMode controlMode);

  void restartControllerState();
}
