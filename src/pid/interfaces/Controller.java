package pid.interfaces;

import pid.CustomizeControlMode;
import pid.PIDFTerms;

public interface Controller {
  PIDFTerms getPIDFTerms();

  void setPIDFTerms(double kP, double kI, double kD, double kF);

  double getSetpoint();

  void setSetpoint(double setpoint);

  void enable(CustomizeControlMode controlMode);

  void disable();
}
