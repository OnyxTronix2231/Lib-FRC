package sensors.counter;

import com.revrobotics.CANSparkMax;

public class MagEncoder extends RevAlternateEncoder{
    
    public MagEncoder(CANSparkMax sparkMax) {
        super(sparkMax, CounterType.MagEncoder);
    }
}
