package sensors.counter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

public abstract class RevCounter implements Counter {

    protected CANSparkMax sparkMax;
    protected CounterType counterType;
    protected RelativeEncoder encoder;

    public RevCounter(CANSparkMax sparkMax, CounterType counterType) {
        this.sparkMax = sparkMax;
        this.counterType = counterType;
        this.encoder = getEncoder();
    }

    public CounterType getRevCounterType(){
        return counterType;
    }

    protected abstract RelativeEncoder getEncoder();
}
