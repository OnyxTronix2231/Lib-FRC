package sensors.Switch;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class TalonFxForwardMicroswitch implements Switch {

    private final TalonFX talonFX;

    public TalonFxForwardMicroswitch(TalonFX talonFX, LimitSwitchSource source, LimitSwitchNormal electricalLimitType) {

        this.talonFX = talonFX;
        this.talonFX.configForwardLimitSwitchSource(source, electricalLimitType);
    }

    @Override
    public boolean isOpen() {
        return talonFX.getSensorCollection().isFwdLimitSwitchClosed() == 0;
    }
}
