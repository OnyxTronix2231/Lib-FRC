package pid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import exceptions.UnsupportedControlModeException;
import pid.interfaces.PIDController;
import sensors.counter.CtreEncoder;

public class CtrePIDController extends CtreController implements PIDController {

  public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder,
                           PIDFTerms pidfTerms) {
    super(ctreMotorController, ctreEncoder, pidfTerms);
  }

  public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder,
                           double kP, double kI, double kD, double kF, int pidSlot, int timeoutMs) {
    super(ctreMotorController, ctreEncoder, kP, kI, kD, kF, pidSlot, timeoutMs);
  }

  public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder,
                           PIDFTerms pidfTerms, int pidSlot, int timeoutMs) {
    super(ctreMotorController, ctreEncoder, pidfTerms, pidSlot, timeoutMs);
  }


  @Override
  public double getProcessVariable() {
    if (this.ctreMotorController.getControlMode() == ControlMode.Velocity) {
      return this.ctreMotorController.getSelectedSensorVelocity(pidSlot);
    } else if (this.ctreMotorController.getControlMode() == ControlMode.Position) {
      return this.ctreMotorController.getSelectedSensorPosition(pidSlot);
    }
    throw new UnsupportedControlModeException();
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

  public void restartControllerState() {
    this.ctreMotorController.setIntegralAccumulator(0,
        this.pidSlot, this.timeoutMs);
  }

  @Override
  public void enable(PIDControlMode controlMode) {
    super.setPIDFTerms(this.pidfTerms.getKp(), this.pidfTerms.getKi(), this.pidfTerms.getKd(), this.pidfTerms.getKf());
    if (controlMode == PIDControlMode.Position) {
      this.ctreMotorController.set(ControlMode.Position, this.setpoint);
    } else {
      this.ctreMotorController.set(ControlMode.Velocity, this.setpoint);
    }
  }
}
