package sensors.gyro;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import java.util.function.Supplier;

public class NavX implements Gyro {
    private final AHRS ahrs;
    private final Supplier<Double> getAngle;
    private final Supplier<Double> getRate;

    public NavX(final AHRS ahrs) {
        this.ahrs = ahrs;
        getAngle = this.ahrs::getAngle;
        getRate = this.ahrs::getRate;
    }

    public NavX(final AHRS ahrs, final Supplier<Double> getAngle, final Supplier<Double> getRate) {
        this.ahrs = ahrs;
        this.getAngle = getAngle;
        this.getRate = getRate;
    }

    /**
     * Returns the total accumulated yaw angle (Z Axis, in degrees)
     * reported by the sensor.
     * <p>
     * NOTE: The angle is continuous, meaning it's range is beyond 360 degrees.
     * This ensures that algorithms that wouldn't want to see a discontinuity
     * in the gyro output as it sweeps past 0 on the second time around.
     * <p>
     * Note that the returned yaw value will be offset by a user-specified
     * offset value; this user-specified offset value is set by
     * invoking the zeroYaw() method.
     * <p>
     *
     * @return The current total accumulated yaw angle (Z axis) of the robot
     * in degrees. This heading is based on integration of the returned rate
     * from the Z-axis (yaw) gyro.
     */
    @Override
    public double getAngle() {
        return getAngle.get();
    }

    /**
     * Return the rate of rotation of the yaw (Z-axis) gyro, in degrees per second.
     * <p>
     * The rate is based on the most recent reading of the yaw gyro angle.
     * <p>
     *
     * @return The current rate of change in yaw angle (in degrees per second)
     */
    @Override
    public double getRate() {
        return getRate.get();
    }

    /**
     * No manual calibration method available :(
     */
    @Deprecated
    @Override
    public void calibrate() {
        // No manual calibration method available :(
    }

    /**
     * Reset the Yaw gyro.
     * <p>
     * Resets the Gyro Z (Yaw) axis to a heading of zero. This can be used if
     * there is significant drift in the gyro and it needs to be recalibrated
     * after it has been running.
     */
    @Override
    public void reset() {
        ahrs.reset();
    }

    @Deprecated
    @Override
    public void close() {
        // not available anymore
    }

    @Deprecated
    public double pidGet() {
        return getAngle();
    }
}
