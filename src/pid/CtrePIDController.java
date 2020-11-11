package pid;

import static pid.PIDConstants.TIMEOUT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import pid.interfaces.PIDController;
import sensors.counter.CtreEncoder;

public class CtrePIDController extends CtreController implements PIDController {

  public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder) {
    super(ctreMotorController, ctreEncoder);
  }

  public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder,
                           int pidSlot) {
    super(ctreMotorController, ctreEncoder, pidSlot);
  }

  public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder,
                           double kP, double kI, double kD, double kF) {
    super(ctreMotorController, ctreEncoder, kP, kI, kD, kF);
  }

  public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder,
                           double kP, double kI, double kD, double kF, int pidSlot) {
    super(ctreMotorController, ctreEncoder, kP, kI, kD, kF, pidSlot);
  }

  @Override
  public double getSetpoint() {
    return ctreMotorController.getClosedLoopTarget(pidSlot);
  }

  @Override
  public void setVelocitySetpoint(double setpoint) {
    ctreMotorController.set(ControlMode.Velocity, setpoint);
  }

  @Override
  public void setPositionSetpoint(double setpoint) {
    ctreMotorController.set(ControlMode.Position, setpoint);
  }

  @Override
  public double getProcessVariable() {
    if (ctreMotorController.getControlMode() == ControlMode.Velocity) {
      return ctreMotorController.getSelectedSensorVelocity(pidSlot);
    } else if (ctreMotorController.getControlMode() == ControlMode.Position) {
      return ctreMotorController.getSelectedSensorPosition(pidSlot);
    }
    return -1;
  }

  @Override
  public double getCurrentError() {
    return ctreMotorController.getClosedLoopError(pidSlot);
  }

  public boolean isOnTarget(double tolerance) {
    return Math.abs(getCurrentError()) < tolerance;
  }

  public boolean isOnTarget(double belowTolerance, double aboveTolerance) {
    return getCurrentError() > belowTolerance && getCurrentError() < aboveTolerance;
  }

  public void resetAccumulator() {
    this.ctreMotorController.setIntegralAccumulator(0,
        this.pidSlot, TIMEOUT);
  }
}
