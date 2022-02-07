package sensors.counter;

import com.ctre.phoenix.sensors.CANCoder;

import static pid.CtreConstants.CTRE_CAN_CODER_BUS;
import static pid.CtreConstants.CTRE_DEVICE_CALLS_TIMEOUT;

public class CtreCANCoder extends CANCoder implements CtreEncoder {

    private final int timeoutResetMs;

    public CtreCANCoder(int deviceNumber) {
        this(deviceNumber, CTRE_CAN_CODER_BUS);
    }

    public CtreCANCoder(int deviceNumber, String CANBus) {
        this(deviceNumber, CANBus, CTRE_DEVICE_CALLS_TIMEOUT);
    }

    public CtreCANCoder(int deviceNumber, String CANBus, int timeoutResetMs) {
        super(deviceNumber, CANBus);
        this.timeoutResetMs = timeoutResetMs;
    }

    @Override
    public double getCount() {
        return getPosition();
    }

    @Override
    public double getRate() {
        return getVelocity();
    }

    @Override
    public void reset() {
        setPosition(0, timeoutResetMs);
    }
}
