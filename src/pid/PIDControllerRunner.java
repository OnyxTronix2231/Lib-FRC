package pid;

import static pid.PIDConstants.INTERVAL_BETWEEN_MEASUREMENTS;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class PIDControllerRunner implements PIDRunner {
  private IMotorControllerEnhanced MotorControllerEnhancedPIDRunner;
  private MotorControllerEnhancedPIDController motorControllerEnhancedPIDController;
  private double intervalBetweenMeasurements;

  public PIDControllerRunner(IMotorControllerEnhanced MotorControllerEnhancedPIDRunner,
                             MotorControllerEnhancedPIDController motorControllerEnhancedPIDController,
                             double intervalBetweenMeasurements) {
    this.MotorControllerEnhancedPIDRunner = MotorControllerEnhancedPIDRunner;
    this.motorControllerEnhancedPIDController = motorControllerEnhancedPIDController;
    this.intervalBetweenMeasurements = intervalBetweenMeasurements;
  }

  public PIDControllerRunner(IMotorControllerEnhanced MotorControllerEnhancedPIDRunner,
                             MotorControllerEnhancedPIDController motorControllerEnhancedPIDController) {
    this.MotorControllerEnhancedPIDRunner = MotorControllerEnhancedPIDRunner;
    this.motorControllerEnhancedPIDController = motorControllerEnhancedPIDController;
    this.intervalBetweenMeasurements = INTERVAL_BETWEEN_MEASUREMENTS;
  }

  public void startPIDLoop() {
    motorControllerEnhancedPIDController.resetSumOfErrors();
    MotorControllerEnhancedPIDRunner.set(MotorControllerEnhancedPIDRunner.getControlMode(),
        motorControllerEnhancedPIDController.calculate(intervalBetweenMeasurements));
  }

  public void stopPIDLoop(double speedAfterStop) {
    this.MotorControllerEnhancedPIDRunner.set(ControlMode.PercentOutput, speedAfterStop);
  }
  
}
