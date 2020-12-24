package controllers;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.BaseTalon;
import edu.wpi.first.wpilibj.SpeedController;

public class VelocityController implements SpeedController {

  private final BaseTalon motor;
  private final double maxVelocity;
  private final int pidSlot;
  private final int pidIdx;

  public VelocityController(final BaseTalon motor, final double maxVelocity, final int pidSlot) {
    this.motor = motor;
    this.maxVelocity = maxVelocity;
    this.pidSlot = pidSlot;
    pidIdx = 0;
  }

  public VelocityController(final BaseTalon motor, final double maxVelocity, final int pidSlot, final int pidIdx) {
    this.motor = motor;
    this.maxVelocity = maxVelocity;
    this.pidSlot = pidSlot;
    this.pidIdx = pidIdx;
  }

  public void initVelocityController() {
    motor.selectProfileSlot(pidSlot, pidIdx);
  }

  private double getVelocityBySpeed(final double speed) {
    return maxVelocity * speed;
  }

  @Override
  public void set(final double speed) {
    motor.set(ControlMode.Velocity, getVelocityBySpeed(speed));
  }

  @Override
  public double get() {
    return motor.getSelectedSensorVelocity(pidIdx);
  }

  @Override
  public void setInverted(final boolean isInverted) {
    motor.setInverted(isInverted);
  }

  @Override
  public boolean getInverted() {
    return motor.getInverted();
  }

  @Override
  public void disable() {
    motor.set(ControlMode.Disabled, 0);
  }

  @Override
  public void stopMotor() {
    motor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void pidWrite(final double output) {
    motor.set(ControlMode.PercentOutput, output);
  }
}
