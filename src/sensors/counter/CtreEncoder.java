package sensors.counter;

import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class CtreEncoder implements Counter {
  private final IMotorControllerEnhanced baseTalon;
  private final int pidSlot;
  private final int timeoutResetMs;

  public CtreEncoder(IMotorControllerEnhanced baseTalon, int pidSlot) {
    this.baseTalon = baseTalon;
    this.pidSlot = pidSlot;
    this.timeoutResetMs = 100;
  }

  public CtreEncoder(IMotorControllerEnhanced baseTalon, int pidSlot, int timeoutResetMs) {
    this.baseTalon = baseTalon;
    this.pidSlot = pidSlot;
    this.timeoutResetMs = timeoutResetMs;
  }


  @Override
  public double getCount() {
    return baseTalon.getSelectedSensorPosition(pidSlot);
  }

  @Override
  public double getRate() {
    return baseTalon.getSelectedSensorVelocity(pidSlot);
  }

  @Override
  public void reset() {
    baseTalon.setSelectedSensorPosition(0, pidSlot, timeoutResetMs);
  }
}
