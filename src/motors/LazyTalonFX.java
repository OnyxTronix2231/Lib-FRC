package motors;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class LazyTalonFX extends WPI_TalonFX {
    protected double mLastSet = Double.NaN;
    protected ControlMode mLastControlMode = null;

    public LazyTalonFX(int deviceNumber) {
        super(deviceNumber);
    }

    public double getLastSet() {
        return mLastSet;
    }

    /**
     * wrapper method to mimic WPI_TalonFX set method
     */

    @Override
    public void set(ControlMode mode, double value) {
        if (value != mLastSet || mode != mLastControlMode) {
            mLastSet = value;
            mLastControlMode = mode;
            super.set(mode, value);
        }
    }
}
