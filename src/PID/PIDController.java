package PID;

public interface PIDController {
  PIDFTerms getPID();
  double getSetpoint();
  double getProcessVariable();
  double getLastError();
  void setPID(double kP, double kI, double kD, double kF);
  void setSetpoint(double setPoint);
}
