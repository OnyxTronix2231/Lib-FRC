package PID;

public interface PIDController {
  double getP();
  double getI();
  double getD();
  double getF();
  double getSetpoint();
  double getProcessVariable();
  double getLastError();
  void setP(double kP);
  void setI(double kI);
  void setD(double kD);
  void setF(double kF);
  void setSetpoint(double setPoint);
}
