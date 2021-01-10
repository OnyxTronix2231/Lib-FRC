package sensors.counter;

import edu.wpi.first.wpilibj.Encoder;

public class RoboRioEncoder implements Counter {
    private final Encoder encoder;

    public RoboRioEncoder(final Encoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public double getCount() {
        return encoder.get();
    }

    @Override
    public double getRate() {
        return encoder.getRate();
    }

    @Override
    public void reset() {
        encoder.reset();
    }
}
