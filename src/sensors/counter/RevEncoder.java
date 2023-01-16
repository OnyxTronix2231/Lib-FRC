package frc.robot.sparkEncoder;

import com.revrobotics.CANSparkMax;
import sensors.counter.Counter;

public class RevEncoder implements Counter {

    private final CANSparkMax sparkMax;

    public RevEncoder(CANSparkMax sparkMax) {
        this.sparkMax = sparkMax;
    }

    @Override
    public double getCount() {
        return sparkMax.getEncoder().getPosition();
    }

    @Override
    public double getRate() {
        return sparkMax.getEncoder().getVelocity();
    }

    @Override
    public void reset() {
        sparkMax.getEncoder().setPosition(0);
    }
}
