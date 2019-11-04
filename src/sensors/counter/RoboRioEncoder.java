package sensors.counter;

import edu.wpi.first.wpilibj.Encoder;

public class RoboRioEncoder implements Counter {
    private final Encoder m_encoder;
    private final double m_distancePerUnit;

    public RoboRioEncoder(final Encoder encoder, final double distancePerUnit) {
        m_encoder = encoder;
        m_distancePerUnit = distancePerUnit;
    }

    @Override
    public int getCount() {
        return m_encoder.get();
    }

    @Override
    public double getRate() {
        return m_encoder.getRate();
    }

    @Override
    public void reset() {
        m_encoder.reset();
    }
}
