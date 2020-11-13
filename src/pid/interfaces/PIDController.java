package pid.interfaces;

import pid.CustomizeControlMode;

public interface PIDController extends Controller {
  double getSetpoint();

  void setSetpoint(double setpoint);

  double getProcessVariable();

  double getCurrentError();
  
  void enable(CustomizeControlMode controlMode);
}
