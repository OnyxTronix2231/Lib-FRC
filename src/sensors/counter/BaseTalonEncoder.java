package sensors.counter;

import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class BaseTalonEncoder implements Counter {
  private  final IMotorControllerEnhanced baseTalon;
  private final int pidSlot;

  public BaseTalonEncoder(IMotorControllerEnhanced baseTalon, int pidSlot) {
    this.baseTalon = baseTalon;
    this.pidSlot = pidSlot;
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
    baseTalon.setSelectedSensorPosition(0, pidSlot, 100);
  }
}
