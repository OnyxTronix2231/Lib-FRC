package sensors.Switch;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.BaseTalon;

public class BaseTalonReverseLimitSwitch implements Switch {

    private final BaseTalon baseTalon;

    public BaseTalonReverseLimitSwitch(BaseTalon baseTalon, LimitSwitchSource source,
                                       LimitSwitchNormal electricalLimitType) {
        this.baseTalon = baseTalon;
        this.baseTalon.configForwardLimitSwitchSource(source, electricalLimitType);
    }

    @Override
    public boolean isOpen() {
        return baseTalon.isRevLimitSwitchClosed() != 0;
    }
}
