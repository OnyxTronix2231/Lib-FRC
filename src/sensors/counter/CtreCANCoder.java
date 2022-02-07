package sensors.counter;

import com.ctre.phoenix.sensors.CANCoder;

public class CtreCANCoder extends CANCoder implements Counter {

    public CtreCANCoder(int deviceNumber, String canbus) {
        super(deviceNumber, canbus);
    }

    public CtreCANCoder(int deviceNumber) {
        super(deviceNumber);
    }

    @Override
    public double getCount() {
        return this.getCount();
    }

    @Override
    public double getRate() {
        return this.getRate();
    }

    @Override
    public void reset() {
        this.reset();
    }
}
