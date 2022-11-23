package motors;

import com.revrobotics.CANSparkMax;

public class LazySparkMax extends CANSparkMax {
    protected double mLastSet = Double.NaN;
    protected ControlType mLastControlType = null;

    public LazySparkMax(int deviceNumber) {
        super(deviceNumber, MotorType.kBrushless);
    }

    /**
     * wrapper method to mimic CANSparkMax set method
     */
    public void set(ControlType type, double setpoint) {
        if (setpoint != mLastSet || type != mLastControlType) {
            mLastSet = setpoint;
            mLastControlType = type;
            super.getPIDController().setReference(setpoint, type);
        }
    }
}
