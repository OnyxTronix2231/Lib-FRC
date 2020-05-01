package PID;

public interface PIDController {
  PIDFTerms getPIDFTerms();
  double getSetpoint();
  double getProcessVariable();
  double getLastError();
  void setPIDFTerms(double kP, double kI, double kD, double kF);
  void setSetpoint(double setPoint);
}
