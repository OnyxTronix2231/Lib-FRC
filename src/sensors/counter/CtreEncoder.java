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
  public int getCount() {
    return baseTalon.getSelectedSensorPosition(pidSlot);
  }

  @Override
  public double getRate() {
    return baseTalon.getSelectedSensorVelocity(pidSlot);
  }

  @Override
  public void reset() {
    setCount(0);
  }

  @Override
  public void setCount(int count) {
    baseTalon.setSelectedSensorPosition(count, pidSlot, timeoutResetMs);
  }
}
