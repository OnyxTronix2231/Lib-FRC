package sensors.counter;

public class DistanceMeterEncoder implements Counter, DistanceMeter {
    private final Counter counter;
    private final double distancePerCount;

    public DistanceMeterEncoder(final Counter counter, final double distancePerCount) {
        this.counter = counter;
        this.distancePerCount = distancePerCount;
    }

    @Override
    public int getCount() {
        return counter.getCount();
    }

    @Override
    public double getRate() {
        return counter.getCount();
    }

    @Override
    public void setCount(int count) {

    }

    @Override
    public void reset() {
        counter.reset();
    }

    @Override
    public double getDistance() {
        return counter.getCount() * distancePerCount;
    }

    @Override
    public double getVelocity() {
        return counter.getRate() * distancePerCount;
    }
}