package PID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class PIDControllerRunner {
  private IMotorControllerEnhanced motorControllerEnhanced;
  private  MotorControllerEnhancedPIDController motorControllerEnhancedPIDController;
  private double timeBetweenMeasurements;

  public PIDControllerRunner(IMotorControllerEnhanced motorControllerEnhanced,
                             MotorControllerEnhancedPIDController motorControllerEnhancedPIDController,
                             double timeBetweenMeasurements){
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.motorControllerEnhancedPIDController = motorControllerEnhancedPIDController;
    this.timeBetweenMeasurements = timeBetweenMeasurements;
  }

  public void startPIDLoop(){
    motorControllerEnhancedPIDController.resetSumOfErrors();
    motorControllerEnhanced.set(motorControllerEnhanced.getControlMode(),
        motorControllerEnhancedPIDController.calculate(timeBetweenMeasurements));
  }

  public void stopPIDLoop(double speedAfterStop){
    this.motorControllerEnhanced.set(ControlMode.PercentOutput, speedAfterStop);
  }
}
