package pid.interfaces;

import pid.PIDFTerms;

public interface Controller {
  PIDFTerms getPIDFTerms();

  void setPIDFTerms(double kP, double kI, double kD, double kF);

  double getSetpoint();

  void setSetpoint(double setpoint);

  void disable();
}
