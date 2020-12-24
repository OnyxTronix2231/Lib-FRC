package pid.interfaces;

import exceptions.UnsupportedControlModeException;
import pid.PIDControlMode;

public interface PIDController extends Controller {
  double getProcessVariable() throws UnsupportedControlModeException;

  double getCurrentError();

  void enable(PIDControlMode controlMode);

  void restartControllerState();

  boolean isOnTarget(double tolerance);

  boolean isOnTarget(double belowTolerance, double aboveTolerance);
}
