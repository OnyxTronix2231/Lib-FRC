package pid;

import static pid.PIDConstants.TIMEOUT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class MotorControllerEnhancedPIDController implements PIDController {
  private IMotorControllerEnhanced motorControllerEnhanced;
  private PIDFTerms pidfTerms;
  private int pidSlot;
  private double sumOfErrors;
  private double lastError;

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double setpoint,
                                              double currentProcessVariable) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.pidfTerms = new PIDFTerms(0, 0, 0, 0);
    this.setSetpoint(setpoint);
    this.pidSlot = 0;
    this.sumOfErrors = 0;
    if (currentProcessVariable > setpoint)
      lastError = setpoint + currentProcessVariable;
    else
      lastError = setpoint - currentProcessVariable;
  }

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double setpoint,
                                              int pidSlot, double currentProcessVariable) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.pidfTerms = new PIDFTerms(0, 0, 0, 0);
    this.setSetpoint(setpoint);
    this.pidSlot = pidSlot;
    this.sumOfErrors = 0;
    this.lastError = setpoint;
    if (currentProcessVariable > setpoint)
      lastError = setpoint + currentProcessVariable;
    else
      lastError = setpoint - currentProcessVariable;
  }

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double kP, double kI,
                                              double kD, double kF, double setpoint, double currentProcessVariable) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    PIDFTerms pidfTerms = new PIDFTerms(kP, kI, kD, kF);
    this.setPIDFTerms(kP, kI, kD, kF);
    this.setSetpoint(setpoint);
    this.pidSlot = 0;
    this.sumOfErrors = 0;
    this.lastError = setpoint;
    if (currentProcessVariable > setpoint)
      lastError = setpoint + currentProcessVariable;
    else
      lastError = setpoint - currentProcessVariable;
  }

  public MotorControllerEnhancedPIDController(IMotorControllerEnhanced motorControllerEnhanced, double kP, double kI,
                                              double kD, double kF, double setpoint, int pidSlot,
                                              double currentProcessVariable) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    PIDFTerms pidfTerms = new PIDFTerms(kP, kI, kD, kF);
    this.setPIDFTerms(kP, kI, kD, kF);
    this.setSetpoint(setpoint);
    this.pidSlot = pidSlot;
    if (currentProcessVariable > setpoint)
      lastError = setpoint + currentProcessVariable;
    else
      lastError = setpoint - currentProcessVariable;
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
    this.pidfTerms.setP(kI);
    this.pidfTerms.setP(kD);
    this.pidfTerms.setP(kF);
  }

  public boolean isOnTarget(double tolerance){
    return ((this.getSetpoint() - Math.abs(this.getCurrentError())) > (this.getSetpoint() - tolerance));
  }

  public boolean isOnTarget(double belowTolerance, double aboveTolerance){
    return ((this.getSetpoint() - this.getCurrentError()) > (this.getSetpoint() - belowTolerance)) &&
        ((this.getSetpoint() + this.getCurrentError()) < (this.getSetpoint() + aboveTolerance));
  }

  public void resetSumOfErrors(){
    this.sumOfErrors = 0;
  }

  public double calculate(double timeBetweenMeasurements){
    this.sumOfErrors += this.getCurrentError();
    double derivative = (this.lastError + this.getCurrentError()) / timeBetweenMeasurements;
    return pidfTerms.getKp() * this.getCurrentError() + pidfTerms.getKi() * this.sumOfErrors +
        pidfTerms.getKd() * derivative + this.pidfTerms.getKf();
  }

}
