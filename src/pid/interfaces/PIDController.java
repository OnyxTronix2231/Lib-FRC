package pid.interfaces;

import pid.PIDControlMode;

public interface PIDController extends Controller {
  double getProcessVariable();

  double getCurrentError();

  void restartControllerState();

  boolean isOnTarget(double tolerance);

  boolean isOnTarget(double belowTolerance, double aboveTolerance);
}
