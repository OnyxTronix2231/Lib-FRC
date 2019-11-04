package sensors.Switch;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSrxForwardMicroswitch implements Switch {
  private final TalonSRX m_talonSRX;

  public TalonSrxForwardMicroswitch(final TalonSRX talonSRX, final LimitSwitchSource source,
                                    final LimitSwitchNormal electricalLimitType) {
    m_talonSRX = talonSRX;
    m_talonSRX.configForwardLimitSwitchSource(source, electricalLimitType);
  }

  @Override
  public boolean isOpen() {
    return m_talonSRX.getSensorCollection().isFwdLimitSwitchClosed();
  }
}
