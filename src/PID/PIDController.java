package PID;

public interface PIDController {
  PIDFTerms getPIDFTerms();
  double getSetpoint();
  double getProcessVariable();
  double getCurrentError();
  void setPIDFTerms(double kP, double kI, double kD, double kF);
  void setSetpoint(double setPoint);
}
