package onyxTronix;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.hal.SimEnum;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import java.util.Arrays;

public class OnyxTalonSRX extends WPI_TalonSRX {

  private SimDevice simDevice;
  private SimDouble simValue;
  private SimEnum simControlMode;

  /**
   * Constructor for TalonSRX object
   *
   * @param deviceNumber CAN Device ID of Device
   */
  public OnyxTalonSRX(final int deviceNumber) {
    super(deviceNumber);
    configFactoryDefault();
    simDevice = SimDevice.create("Talon SRX", deviceNumber);
    if(simDevice != null) {
      simValue = simDevice.createDouble("Value", false, 0);
      simControlMode = simDevice.createEnum("Control Mode", false, Arrays.stream(ControlMode.values()).
          map(Enum::name).toArray(String[]::new), getControlMode().value);
    }
  }

  @Override
  public void set(final ControlMode mode, final double value) {
    super.set(mode, value);
    if(simControlMode != null) {
      simControlMode.set(mode.value);
    }

    if(simValue != null) {
      simValue.set(value);
    }
  }

  @Override
  public double get() {
    if(simValue != null) {
      return simValue.get();
    }

    return super.get();
  }

  @Override
  public ControlMode getControlMode() {
    if(simControlMode != null) {
      return ControlMode.values()[simControlMode.get()];
    }
    return super.getControlMode();
  }
}
