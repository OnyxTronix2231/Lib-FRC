package pid;

import static pid.PIDConstants.TIMEOUT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class MotorControllerEnhancedPIDController implements PIDController {
  private IMotorControllerEnhanced motorControllerEnhanced;
  private PIDFTerms pidfTerms;
  private int pidSlot;

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double setpoint) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.pidfTerms = new PIDFTerms(0, 0, 0, 0);
    this.setSetpoint(setpoint);
    this.pidSlot = 0;
  }

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double setpoint,
                                              int pidSlot) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.pidfTerms = new PIDFTerms(0, 0, 0, 0);
    this.setSetpoint(setpoint);
    this.pidSlot = pidSlot;
  }

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double kP, double kI,
                                              double kD, double kF, double setpoint) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    PIDFTerms pidfTerms = new PIDFTerms(kP, kI, kD, kF);
    this.setPIDFTerms(kP, kI, kD, kF);
    this.setSetpoint(setpoint);
    this.pidSlot = 0;
  }

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double kP, double kI,
                                              double kD, double kF, double setpoint, int pidSlot) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    PIDFTerms pidfTerms = new PIDFTerms(kP, kI, kD, kF);
    this.setPIDFTerms(kP, kI, kD, kF);
    this.setSetpoint(setpoint);
    this.pidSlot = pidSlot;
  }


  @Override
  public PIDFTerms getPIDFTerms() {
    return this.pidfTerms;
  }

  @Override
  public double getSetpoint() {
    return motorControllerEnhanced.getClosedLoopTarget(pidSlot);
  }

  @Override
  public void setSetpoint(double setpoint) {
    motorControllerEnhanced.set(motorControllerEnhanced.getControlMode(), setpoint);
  }

  public int getPidSlot() {
    return this.pidSlot;
  }

  @Override
  public double getProcessVariable() {
    if (motorControllerEnhanced.getControlMode() == ControlMode.Velocity) {
      return motorControllerEnhanced.getSelectedSensorVelocity(pidSlot);
    } else if (motorControllerEnhanced.getControlMode() == ControlMode.Position) {
      return motorControllerEnhanced.getSelectedSensorPosition(pidSlot);
    }
    return -1;
  }

  @Override
  public double getCurrentError() {
    return motorControllerEnhanced.getClosedLoopError(pidSlot);
  }

  @Override
  public void setPIDFTerms(double kP, double kI, double kD, double kF) {
    motorControllerEnhanced.config_kP(pidSlot, kP, TIMEOUT);
    motorControllerEnhanced.config_kI(pidSlot, kI, TIMEOUT);
    motorControllerEnhanced.config_kD(pidSlot, kD, TIMEOUT);
    motorControllerEnhanced.config_kF(pidSlot, kF, TIMEOUT);
    this.pidfTerms.setP(kP);
    this.pidfTerms.setI(kI);
    this.pidfTerms.setD(kD);
    this.pidfTerms.setF(kF);
  }

  public boolean isOnTarget(double tolerance) {
    return Math.abs(getCurrentError()) < tolerance;
  }

  public boolean isOnTarget(double belowTolerance, double aboveTolerance) {
    return getCurrentError() > belowTolerance && getCurrentError() < aboveTolerance;
  }

  public double calculate() {
    return pidfTerms.getKp() * this.getCurrentError() + pidfTerms.getKi() *
        this.motorControllerEnhanced.getIntegralAccumulator(this.pidSlot) + pidfTerms.getKd() *
        this.motorControllerEnhanced.getErrorDerivative(this.pidSlot) + this.pidfTerms.getKf();
  }

  public void resetAccumulatorAndPreviousError() {
    this.motorControllerEnhanced.setIntegralAccumulator(0,
        this.pidSlot, TIMEOUT);
  }
}
