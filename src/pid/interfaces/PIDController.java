package pid.interfaces;

import pid.CustomizeControlMode;

public interface PIDController extends Controller {
  double getProcessVariable();

  double getCurrentError();

  void enable(CustomizeControlMode controlMode);
}
