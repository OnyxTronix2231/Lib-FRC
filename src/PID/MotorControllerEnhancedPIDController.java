package PID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class MotorControllerEnhancedPIDController implements PIDController {
  private IMotorControllerEnhanced motorControllerEnhanced;
  private PIDFTerms pidfTerms;
  private int PIDSlot;
  private double sumOfErrors;
  private double lastError;

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double setpoint) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.pidfTerms = new PIDFTerms(0, 0, 0, 0);
    this.setSetpoint(setpoint);
    this.PIDSlot = 0;
    this.sumOfErrors = 0;
    lastError = setpoint;
  }

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double setpoint,
                                              int PIDSlot) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.pidfTerms = new PIDFTerms(0, 0, 0, 0);
    this.setSetpoint(setpoint);
    this.PIDSlot = PIDSlot;
    this.sumOfErrors = 0;
    lastError = setpoint;
  }

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double kP, double kI,
                                              double kD, double kF, double setpoint) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    PIDFTerms pidfTerms = new PIDFTerms(kP, kI, kD, kF);
    this.setPIDFTerms(kP, kI, kD, kF);
    this.setSetpoint(setpoint);
    this.PIDSlot = 0;
    this.sumOfErrors = 0;
    lastError = setpoint;
  }

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double kP, double kI,
                                              double kD, double kF, double setpoint, int PIDSlot) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    PIDFTerms pidfTerms = new PIDFTerms(kP, kI, kD, kF);
    this.setPIDFTerms(kP, kI, kD, kF);
    this.setSetpoint(setpoint);
    this.PIDSlot = PIDSlot;
  }


  @Override
  public PIDFTerms getPIDFTerms() {
    return this.pidfTerms;
  }

  @Override
  public double getSetpoint() {
    return motorControllerEnhanced.getClosedLoopTarget(PIDSlot);
  }

  @Override
  public void setSetpoint(double setpoint) {
    motorControllerEnhanced.set(motorControllerEnhanced.getControlMode(), setpoint);
  }

  @Override
  public double getProcessVariable() {
    if (motorControllerEnhanced.getControlMode() == ControlMode.Velocity) {
      return motorControllerEnhanced.getSelectedSensorVelocity(PIDSlot);
    } else if (motorControllerEnhanced.getControlMode() == ControlMode.Position) {
      return motorControllerEnhanced.getSelectedSensorPosition(PIDSlot);
    }
    return -1;
  }

  @Override
  public double getCurrentError() {
    return motorControllerEnhanced.getClosedLoopError(PIDSlot);
  }

  @Override
  public void setPIDFTerms(double kP, double kI, double kD, double kF) {
    motorControllerEnhanced.config_kP(PIDSlot, kP, 100);
    motorControllerEnhanced.config_kI(PIDSlot, kI, 100);
    motorControllerEnhanced.config_kD(PIDSlot, kD, 100);
    motorControllerEnhanced.config_kF(PIDSlot, kF, 100);
    this.pidfTerms.setP(kP);
    this.pidfTerms.setP(kI);
    this.pidfTerms.setP(kD);
    this.pidfTerms.setP(kF);
  }

  public void startPIDLoop(){
    this.motorControllerEnhanced.set(this.motorControllerEnhanced.getControlMode(), this. getSetpoint());
  }

  public void stopPIDLoop(double speedAfterStop){
    this.motorControllerEnhanced.set(ControlMode.PercentOutput, speedAfterStop);
  }

  public boolean isOnTarget(double tolerance){
    return ((this.getSetpoint() - Math.abs(this.getCurrentError())) > (this.getSetpoint() - tolerance));
  }

  public boolean isOnTarget(double belowTolerance, double aboveTolerance){
    return ((this.getSetpoint() - this.getCurrentError()) > (this.getSetpoint() - belowTolerance)) &&
        ((this.getSetpoint() + this.getCurrentError()) < (this.getSetpoint() + aboveTolerance));
  }

  public double calculate(){
    return pidfTerms.getKp() * this.getCurrentError() + pidfTerms.getKi() * this.sumOfErrors +
        pidfTerms.getKd() * this.lastError;
  }

}
