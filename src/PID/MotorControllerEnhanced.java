package PID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class MotorControllerEnhanced implements PIDController {
  private IMotorControllerEnhanced motorControllerEnhanced;
  private PIDFTerms pidfTerms;

  public MotorControllerEnhanced(IMotorControllerEnhanced motorControllerEnhanced) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.pidfTerms = new PIDFTerms(0, 0, 0, 0);
  }

  public MotorControllerEnhanced(IMotorControllerEnhanced motorControllerEnhanced, double kP, double kI, double kD,
                                 double kF) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.pidfTerms = new PIDFTerms(kP, kI, kD, kF);
    this.setPID(kP, kI, kD, kF);
  }

  public MotorControllerEnhanced(IMotorControllerEnhanced motorControllerEnhanced, double kP, double kI, double kD,
                                 double kF, double setpoint) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    PIDFTerms pidfTerms = new PIDFTerms(kP, kI, kD, kF);
    this.setPID(kP, kI, kD, kF);
    this.setSetpoint(setpoint);
  }


  @Override
  public PIDFTerms getPID() {
    return this.pidfTerms;
  }

  @Override
  public double getSetpoint() {
    return 0;
  }

  @Override
  public void setSetpoint(double setpoint) {
    motorControllerEnhanced.set(motorControllerEnhanced.getControlMode(), setpoint);
  }

  @Override
  public double getProcessVariable() {
    if (motorControllerEnhanced.getControlMode() == ControlMode.Velocity) {
      return motorControllerEnhanced.getSelectedSensorVelocity(0);
    } else if (motorControllerEnhanced.getControlMode() == ControlMode.Position) {
      return motorControllerEnhanced.getSelectedSensorPosition(0);
    }
    return -1;
  }

  @Override
  public double getLastError() {
    return motorControllerEnhanced.getLastError().value;
  }

  @Override
  public void setPID(double kP, double kI, double kD, double kF) {
    motorControllerEnhanced.config_kP(0, kP, 100);
    motorControllerEnhanced.config_kI(0, kI, 100);
    motorControllerEnhanced.config_kD(0, kD, 100);
    motorControllerEnhanced.config_kF(0, kF, 100);
    this.pidfTerms.setP(kP);
    this.pidfTerms.setP(kI);
    this.pidfTerms.setP(kD);
    this.pidfTerms.setP(kF);
  }

}
