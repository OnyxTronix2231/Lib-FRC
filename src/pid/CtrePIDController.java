package pid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import pid.interfaces.PIDController;
import sensors.counter.CtreEncoder;

public class CtrePIDController extends CtreController implements PIDController {

  public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder) {
    super(ctreMotorController, ctreEncoder);
  }

  public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder,
                           int pidSlot, int timeoutMs) {
    super(ctreMotorController, ctreEncoder, pidSlot, timeoutMs);
  }

  public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder,
                           double kP, double kI, double kD, double kF, int pidSlot, int timeoutMs) {
    super(ctreMotorController, ctreEncoder, kP, kI, kD, kF, pidSlot, timeoutMs);
  }


  @Override
  public double getProcessVariable() {
    if (this.ctreMotorController.getControlMode() == ControlMode.Velocity) {
      return this.ctreMotorController.getSelectedSensorVelocity(pidSlot);
    } else if (this.ctreMotorController.getControlMode() == ControlMode.Position) {
      return this.ctreMotorController.getSelectedSensorPosition(pidSlot);
    }
    return -1;
  }

  @Override
  public double getCurrentError() {
    return this.ctreMotorController.getClosedLoopError(pidSlot);
  }

  public boolean isOnTarget(double tolerance) {
    return Math.abs(getCurrentError()) < tolerance;
  }

  public boolean isOnTarget(double belowTolerance, double aboveTolerance) {
    return getCurrentError() > belowTolerance && getCurrentError() < aboveTolerance;
  }

  public void resetAccumulator() {
    this.ctreMotorController.setIntegralAccumulator(0,
        this.pidSlot, this.timeoutMs);
  }

  @Override
  public void enable(CustomizeControlMode controlMode) {
    if (controlMode == CustomizeControlMode.Position) {
      this.ctreMotorController.set(ControlMode.Position, this.setpoint);
    } else {
      this.ctreMotorController.set(ControlMode.Velocity, this.setpoint);
    }
  }
}
