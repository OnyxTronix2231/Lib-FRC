package sensors.counter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

public class RevNeo550BuiltInEncoder extends RevCounter {

    private final CANSparkMax sparkMax;

    public RevNeo550BuiltInEncoder(CANSparkMax sparkMax) {
        super(sparkMax, CounterType.Neo550);
        this.sparkMax = sparkMax;
    }


    @Override
    protected RelativeEncoder getEncoder() {
        return sparkMax.getEncoder();
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
}
