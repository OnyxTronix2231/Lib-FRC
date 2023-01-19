package sensors.counter;

import com.revrobotics.CANSparkMax;

public class RevAlternateEncoder extends RevCounter {


    public RevAlternateEncoder(CANSparkMax sparkMax,RevCounterType revCounterType) {
        super(sparkMax, revCounterType);
        this.sparkMax = sparkMax;
        this.revCounterType = revCounterType;
    }

    @Override
    public double getCount() {
        return sparkMax.getAlternateEncoder(revCounterType.getEncoderUnitsPerRotation()).getPosition();
    }

    @Override
    public double getRate() {
        return sparkMax.getAlternateEncoder(revCounterType.getEncoderUnitsPerRotation()).getVelocity();
    }

    @Override
    public void reset() {
        sparkMax.getAlternateEncoder(revCounterType.getEncoderUnitsPerRotation()).setPosition(0);
    }
}
