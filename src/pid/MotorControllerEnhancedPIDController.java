package pid;

import static pid.PIDConstants.TIMEOUT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class MotorControllerEnhancedPIDController implements PIDController {
  private IMotorControllerEnhanced motorControllerEnhanced;
  private PIDFTerms pidfTerms;
  private int pidSlot;
  private double sumOfErrors;
  private double previousError;

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double setpoint) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.pidfTerms = new PIDFTerms(0, 0, 0, 0);
    this.setSetpoint(setpoint);
    this.pidSlot = 0;
    this.sumOfErrors = 0;
    if (this.getProcessVariable() > setpoint)
      previousError = setpoint + this.getProcessVariable();
    else
      previousError = setpoint - this.getProcessVariable();
  }

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double setpoint,
                                              int pidSlot) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.pidfTerms = new PIDFTerms(0, 0, 0, 0);
    this.setSetpoint(setpoint);
    this.pidSlot = pidSlot;
    this.sumOfErrors = 0;
    this.previousError = setpoint;
    if (this.getProcessVariable() > setpoint)
      previousError = setpoint + this.getProcessVariable();
    else
      previousError = setpoint - this.getProcessVariable();
  }

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double kP, double kI,
                                              double kD, double kF, double setpoint) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    PIDFTerms pidfTerms = new PIDFTerms(kP, kI, kD, kF);
    this.setPIDFTerms(kP, kI, kD, kF);
    this.setSetpoint(setpoint);
    this.pidSlot = 0;
    this.sumOfErrors = 0;
    this.previousError = setpoint;
    if (this.getProcessVariable() > setpoint)
      previousError = setpoint + this.getProcessVariable();
    else
      previousError = setpoint - this.getProcessVariable();
  }

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double kP, double kI,
                                              double kD, double kF, double setpoint, int pidSlot) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    PIDFTerms pidfTerms = new PIDFTerms(kP, kI, kD, kF);
    this.setPIDFTerms(kP, kI, kD, kF);
    this.setSetpoint(setpoint);
    this.pidSlot = pidSlot;
    if (this.getProcessVariable() > setpoint)
      previousError = setpoint + this.getProcessVariable();
    else
      previousError = setpoint - this.getProcessVariable();
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
    return ((this.getSetpoint() - Math.abs(this.getCurrentError())) > (this.getSetpoint() - tolerance));
  }

  public boolean isOnTarget(double belowTolerance, double aboveTolerance) {
    return ((this.getSetpoint() - this.getCurrentError()) > (this.getSetpoint() - belowTolerance)) &&
        ((this.getSetpoint() + this.getCurrentError()) < (this.getSetpoint() + aboveTolerance));
  }

  public void resetSumOfErrors() {
    this.sumOfErrors = 0;
  }

  public double calculate(double intervalBetweenMeasurements) {
    this.sumOfErrors += this.getCurrentError();
    double derivative = (this.previousError + this.getCurrentError()) / intervalBetweenMeasurements;
    double output = pidfTerms.getKp() * this.getCurrentError() + pidfTerms.getKi() * this.sumOfErrors +
        pidfTerms.getKd() * derivative + this.pidfTerms.getKf();
    this.updateLastError();
    return output;
  }

  private void updateLastError() {
    this.previousError = this.getCurrentError();
  }

}
