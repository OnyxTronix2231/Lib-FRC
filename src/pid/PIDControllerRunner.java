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
    motorControllerEnhancedPIDController.resetAccumulator();
    motorControllerEnhancedPIDRunner.set(motorControllerEnhancedPIDRunner.getControlMode(),
        motorControllerEnhancedPIDController.calculate());
  }

  public void terminatePIDLoop(int remainOrStop) {
    if (remainOrStop == 0) {
      motorControllerEnhancedPIDRunner.set(ControlMode.PercentOutput, 0);
    } else {
      motorControllerEnhancedPIDRunner.set(ControlMode.PercentOutput,
          motorControllerEnhancedPIDRunner.getMotorOutputPercent());
    }
  }

}
