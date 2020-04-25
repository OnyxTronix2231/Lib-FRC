package sensors.counter;

import edu.wpi.first.wpilibj.Encoder;

public class RoboRioEncoder implements Counter {
    private final Encoder encoder;
    private final double distancePerUnit;

    public RoboRioEncoder(final Encoder encoder, final double distancePerUnit) {
        this.encoder = encoder;
        this.distancePerUnit = distancePerUnit;
    }

    @Override
    public int getCount() {
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
