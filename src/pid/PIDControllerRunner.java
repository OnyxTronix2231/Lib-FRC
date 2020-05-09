package pid;

import static pid.PIDConstants.INTERVAL_BETWEEN_MEASUREMENTS;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class PIDControllerRunner {
  private IMotorControllerEnhanced motorControllerEnhanced;
  private MotorControllerEnhancedPIDController motorControllerEnhancedPIDController;
  private double intervalBetweenMeasurements;

  public PIDControllerRunner(IMotorControllerEnhanced motorControllerEnhanced,
                             MotorControllerEnhancedPIDController motorControllerEnhancedPIDController,
                             double intervalBetweenMeasurements) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.motorControllerEnhancedPIDController = motorControllerEnhancedPIDController;
    this.intervalBetweenMeasurements = intervalBetweenMeasurements;
  }

  public PIDControllerRunner(IMotorControllerEnhanced motorControllerEnhanced,
                             MotorControllerEnhancedPIDController motorControllerEnhancedPIDController) {
    this.motorControllerEnhanced = motorControllerEnhanced;
    this.motorControllerEnhancedPIDController = motorControllerEnhancedPIDController;
    this.intervalBetweenMeasurements = INTERVAL_BETWEEN_MEASUREMENTS;
  }

  public void startPIDLoop() {
    motorControllerEnhancedPIDController.resetSumOfErrors();
    motorControllerEnhanced.set(motorControllerEnhanced.getControlMode(),
        motorControllerEnhancedPIDController.calculate(intervalBetweenMeasurements));
  }

  public void stopPIDLoop(double speedAfterStop) {
    this.motorControllerEnhanced.set(ControlMode.PercentOutput, speedAfterStop);
  }
}
