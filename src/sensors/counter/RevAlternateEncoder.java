package sensors.counter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

public class RevAlternateEncoder extends RevCounter {

    public RevAlternateEncoder(CANSparkMax sparkMax, CounterType counterType) {
        super(sparkMax, counterType);
        this.sparkMax = sparkMax;
        this.counterType = counterType;
    }

    @Override
    public double getCount() {
        return encoder.getPosition();
    }

    @Override
    public double getRate() {
        return encoder.getVelocity();
    }

    @Override
    public void reset() {
        encoder.setPosition(0);
    }

    @Override
    protected RelativeEncoder getEncoder() {
        return sparkMax.getAlternateEncoder(counterType.getEncoderUnitsPerRotation());
    }
}
