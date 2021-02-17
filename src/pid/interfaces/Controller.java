package pid.interfaces;

import pid.PIDFTerms;

public interface Controller {
  PIDFTerms getPIDFTerms();

  void setPIDFTerms(double kP, double kI, double kD, double kF);

  double getSetpoint();

  void setSetpoint(double setpoint);

  double getCurrentError();

  boolean isOnTarget(double tolerance);

  boolean isOnTarget(double belowTolerance, double aboveTolerance);

  void enable();

  void update(double setpoint);

  void disable();
}
