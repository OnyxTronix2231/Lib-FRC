package sensors.counter;

import com.revrobotics.CANSparkMax;

public abstract class RevCounter implements Counter {

    protected CANSparkMax sparkMax;
    protected RevCounterType revCounterType;

    public RevCounter(CANSparkMax sparkMax, RevCounterType revCounterType) {
        this.sparkMax = sparkMax;
        this.revCounterType = revCounterType;
    }

    @Override
    public double getCount() {
        return 0;
    }

    @Override
    public double getRate() {
        return 0;
    }

    @Override
    public void reset() {

    }

    public RevCounterType getRevCounterType(){
        return revCounterType;
    }
}
