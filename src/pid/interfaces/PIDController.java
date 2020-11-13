package pid.interfaces;

import com.ctre.phoenix.motorcontrol.ControlMode;
import pid.CustomizeControlMode;

public interface PIDController extends Controller {
  double getSetpoint();

  void setSetpoint(double setpoint);

  double getProcessVariable();

  double getCurrentError();
  void enable(CustomizeControlMode controlMode);
}
