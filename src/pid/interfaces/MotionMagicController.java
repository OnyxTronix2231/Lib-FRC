package pid.interfaces;

import pid.PIDControlMode;

public interface MotionMagicController extends Controller {
  double getCurrentError();

  public void enable(double feedForward);

  public int getAcceleration();

  public void setAcceleration(int acceleration);

  public int getCruiseVelocity();

  public void setCruiseVelocity(int cruiseVelocity);

  public int getAccelerationSmoothing();

  public void setAccelerationSmoothing(int accelerationSmoothing);

  public void update(double setpoint, double feedForward);
}
