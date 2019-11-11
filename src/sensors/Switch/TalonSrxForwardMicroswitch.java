package sensors.Switch;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSrxForwardMicroswitch implements Switch {
  private final TalonSRX talonSRX;

  public TalonSrxForwardMicroswitch(final TalonSRX talonSRX, final LimitSwitchSource source,
                                    final LimitSwitchNormal electricalLimitType) {
    this.talonSRX = talonSRX;
    this.talonSRX.configForwardLimitSwitchSource(source, electricalLimitType);
  }

  @Override
  public boolean isOpen() {
    return talonSRX.getSensorCollection().isFwdLimitSwitchClosed();
  }
}
