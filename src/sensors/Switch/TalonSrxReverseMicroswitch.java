package sensors.Switch;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSrxReverseMicroswitch implements Switch {
  private final TalonSRX m_talonSRX;

  public TalonSrxReverseMicroswitch(final TalonSRX talonSRX, final LimitSwitchSource source,
                                    final LimitSwitchNormal electricalLimitType) {
    m_talonSRX = talonSRX;
    m_talonSRX.configReverseLimitSwitchSource(source, electricalLimitType);
  }

  @Override
  public boolean isOpen() {
    return m_talonSRX.getSensorCollection().isRevLimitSwitchClosed();
  }
}