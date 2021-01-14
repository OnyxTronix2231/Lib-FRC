package sensors.counter;

import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import static pid.CtreConstants.CTRE_DEVICE_CALLS_TIMEOUT;
import static pid.CtreConstants.DEFAULT_SLOT_IDX;

public class CtreEncoder implements Counter {
  private final IMotorControllerEnhanced baseTalon;
  private final int slotIdx;
  private final int timeoutResetMs;

  public CtreEncoder(IMotorControllerEnhanced baseTalon, int slotIdx) {
    this(baseTalon, slotIdx, CTRE_DEVICE_CALLS_TIMEOUT);
  }

  public CtreEncoder(IMotorControllerEnhanced baseTalon) {
    this(baseTalon, DEFAULT_SLOT_IDX, CTRE_DEVICE_CALLS_TIMEOUT);
  }

  public CtreEncoder(IMotorControllerEnhanced baseTalon, int slotIdx, int timeoutResetMs) {
    this.baseTalon = baseTalon;
    this.slotIdx = slotIdx;
    this.timeoutResetMs = timeoutResetMs;
  }


  @Override
  public double getCount() {
    return baseTalon.getSelectedSensorPosition(slotIdx);
  }

  @Override
  public double getRate() {
    return baseTalon.getSelectedSensorVelocity(slotIdx);
  }

  @Override
  public void reset() {
    baseTalon.setSelectedSensorPosition(0, slotIdx, timeoutResetMs);
  }
}
