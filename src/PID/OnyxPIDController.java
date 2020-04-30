package PID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class OnyxPIDController implements PIDController {
  private final IMotorControllerEnhanced motorControllerEnhanced;

  public OnyxPIDController(IMotorControllerEnhanced motorControllerEnhanced) {
    this.motorControllerEnhanced = motorControllerEnhanced;
  }

  public OnyxPIDController(IMotorControllerEnhanced motorControllerEnhanced, double kP, double kI, double kD,
                           double kF) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.setP(kP);
    this.setI(kI);
    this.setD(kD);
    this.setF(kF);
  }

  public OnyxPIDController(IMotorControllerEnhanced motorControllerEnhanced, double kP, double kI, double kD,
                           double kF, double setPoint) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.setP(kP);
    this.setI(kI);
    this.setD(kD);
    this.setF(kF);
    this.setSetPoint(setPoint);
  }

  @Override
  public double getP() {
    return 0;
  }

  @Override
  public double getI() {
    return 0;
  }

  @Override
  public double getD() {
    return 0;
  }

  @Override
  public double getF() {
    return 0;
  }

  @Override
  public double getSetPoint() {
    return 0;
  }

  @Override
  public double getProcessVariable() {
    if(motorControllerEnhanced.getControlMode() == ControlMode.Velocity) {
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
  public void setP(double kP) {
    motorControllerEnhanced.config_kP(0, kP, 100);
  }

  @Override
  public void setI(double kI) {
    motorControllerEnhanced.config_kI(0, kI, 100);

  }

  @Override
  public void setD(double kD) {
    motorControllerEnhanced.config_kD(0, kD, 100);
  }

  @Override
  public void setF(double kF) {
    motorControllerEnhanced.config_kF(0, kF, 100);
  }

  @Override
  public void setSetPoint(double setPoint) {
    motorControllerEnhanced.set(motorControllerEnhanced.getControlMode(), setPoint);
  }

}
