package sensors.counter;

public class DistanceMeterEncoder implements Counter, DistanceMeter {
    private final Counter m_counter;
    private final double m_distancePerCount;

    public DistanceMeterEncoder(final Counter counter, final double distancePerCount) {
        m_counter = counter;
        m_distancePerCount = distancePerCount;
    }

    @Override
    public int getCount() {
        return m_counter.getCount();
    }

    @Override
    public double getRate() {
        return m_counter.getCount();
    }

    @Override
    public void reset() {
        m_counter.reset();
    }

    @Override
    public double getDistance() {
        return m_counter.getCount() * m_distancePerCount;
    }

    @Override
    public double getVelocity() {
        return m_counter.getRate() * m_distancePerCount;
    }
}