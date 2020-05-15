package pid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class PIDControllerRunner implements PIDRunner {
  private IMotorControllerEnhanced motorControllerEnhancedPIDRunner;
  private MotorControllerEnhancedPIDController motorControllerEnhancedPIDController;

  public PIDControllerRunner(IMotorControllerEnhanced MotorControllerEnhancedPIDRunner,
                             MotorControllerEnhancedPIDController motorControllerEnhancedPIDController) {
    this.motorControllerEnhancedPIDRunner = MotorControllerEnhancedPIDRunner;
    this.motorControllerEnhancedPIDController = motorControllerEnhancedPIDController;
  }

  public void startPIDLoop() {
    this.motorControllerEnhancedPIDController.resetAccumulator();
    motorControllerEnhancedPIDRunner.set(motorControllerEnhancedPIDRunner.getControlMode(),
        motorControllerEnhancedPIDController.calculate());
  }

  public void stopPIDLoop(int remainOrStop) {
    if (remainOrStop == 0) {
      this.motorControllerEnhancedPIDRunner.set(ControlMode.PercentOutput, 0);
    } else {
      this.motorControllerEnhancedPIDRunner.set(ControlMode.PercentOutput,
          this.motorControllerEnhancedPIDRunner.getSelectedSensorVelocity(
              this.motorControllerEnhancedPIDController.getPidSlot()));
    }
  }

}
