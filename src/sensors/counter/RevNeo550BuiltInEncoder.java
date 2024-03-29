package sensors.counter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

public class RevNeo550BuiltInEncoder extends RevCounter {

    public RevNeo550BuiltInEncoder(CANSparkMax sparkMax) {
        super(sparkMax, CounterType.Neo550);
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
        return sparkMax.getEncoder();
    }
}
