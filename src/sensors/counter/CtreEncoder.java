package sensors.counter;

import Constants.CtreConstants;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.sensors.CANCoder;

public class CtreEncoder extends CANCoder implements Counter {
  private final int pidSlot;
  private final int timeoutResetMs;

  public CtreEncoder(int deviceID, int pidSlot) {
    this(deviceID, pidSlot, CtreConstants.CTRE_DEVICE_CALLS_TIMEOUT);
  }

  public CtreEncoder(IMotorControllerEnhanced baseTalon, int pidSlot) {
    this(baseTalon.getDeviceID(), pidSlot, CtreConstants.CTRE_DEVICE_CALLS_TIMEOUT);
  }

  public CtreEncoder(int deviceID, int pidSlot, int timeoutResetMs) {
    super(deviceID);
    this.pidSlot = pidSlot;
    this.timeoutResetMs = timeoutResetMs;
  }


  @Override
  public int getCount() {
    return (int) super.getPosition();
  }

  @Override
  public double getRate() {
    return super.getVelocity();
  }

  @Override
  public void reset() {
    super.setPosition(0);
  }

  public int getDeviceID(){
    return super.getDeviceID();
  }
}
