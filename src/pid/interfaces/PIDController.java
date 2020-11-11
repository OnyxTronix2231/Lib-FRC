package pid.interfaces;

import pid.PIDFTerms;

public interface PIDController {
  PIDFTerms getPIDFTerms();

  double getSetpoint();

  void setVelocitySetpoint(double setpoint);

  void setPositionSetpoint(double setpoint);

  double getProcessVariable();

  double getCurrentError();

  void setPIDFTerms(double kP, double kI, double kD, double kF);
}
