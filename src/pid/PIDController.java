package pid;

public interface PIDController {
  PIDFTerms getPIDFTerms();

  double getSetpoint();

  void setSetpoint(double setPoint);

  double getProcessVariable();

  double getCurrentError();

  void setPIDFTerms(double kP, double kI, double kD, double kF);
}
