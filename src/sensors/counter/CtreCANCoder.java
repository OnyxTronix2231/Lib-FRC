package sensors.counter;

import com.ctre.phoenix.sensors.CANCoder;

import static pid.CtreConstants.CTRE_DEVICE_CALLS_TIMEOUT;

public class CtreCANCoder extends CANCoder implements CtreEncoder {

    private final int timeoutResetMs;

    public CtreCANCoder(int deviceNumber, String canbus, int timeoutResetMs) {
        super(deviceNumber, canbus);
        this.timeoutResetMs = timeoutResetMs;
    }

    public CtreCANCoder(int deviceNumber, String canbus) {
        this(deviceNumber, canbus, CTRE_DEVICE_CALLS_TIMEOUT);
    }

    public CtreCANCoder(int deviceNumber) {
        this(deviceNumber, CTRE_DEVICE_CALLS_TIMEOUT);
    }

    public CtreCANCoder(int deviceNumber, int timeoutResetMs) {
        super(deviceNumber);
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
