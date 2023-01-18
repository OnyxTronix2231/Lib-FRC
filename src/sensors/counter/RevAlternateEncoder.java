package sensors.counter;

import com.revrobotics.CANSparkMax;

public class RevAlternateEncoder implements Counter {

    private final CANSparkMax sparkMax;
    private final int countPerRev;

    public RevAlternateEncoder(CANSparkMax sparkMax, int countPerRev) {
        this.sparkMax = sparkMax;
        this.countPerRev = countPerRev;
    }

    @Override
    public double getCount() {
        return sparkMax.getAlternateEncoder(countPerRev).getPosition();
    }

    @Override
    public double getRate() {
        return sparkMax.getAlternateEncoder(countPerRev).getVelocity();
    }

    @Override
    public void reset() {
        sparkMax.getAlternateEncoder(countPerRev).setPosition(0);
    }
}
