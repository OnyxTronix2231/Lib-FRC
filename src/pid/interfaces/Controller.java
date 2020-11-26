package pid.interfaces;

import pid.PIDFTerms;

public interface Controller {
  PIDFTerms getPIDFTerms();

  void setPIDFTerms(double kP, double kI, double kD, double kF);

  void setPIDFTerms(PIDFTerms pidfTerms);

  double getSetpoint();

  void setSetpoint(double setpoint);

  void disable();
}
