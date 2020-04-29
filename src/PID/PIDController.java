package PID;

public interface PIDController {
  double getP();
  double getI();
  double getD();
  double getSetPoint();
  double getProcessVariable();
  double setP();
  double setI();
  double setD();
  double setSetPoint();
}
