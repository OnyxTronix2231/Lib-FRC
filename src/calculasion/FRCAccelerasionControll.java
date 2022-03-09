package calculasion;

import edu.wpi.first.wpilibj.Timer;

public class FRCAccelerasionControll {

    private int counter = 0;
    private double lastTime = 0;
    private double lastSpeed = 0;
    private double currentAcceleration = 0;

    /**
     * call this function on periodic
     *
     * @param speed - the current speed of the system
     * @return the current Acceleration of the system
     */
    public double caculateAcceleration(double speed) {
        double timestamp = Timer.getFPGATimestamp();
        double dt = timestamp - lastTime;
        double dv = speed - lastSpeed;
        lastSpeed = speed;
        currentAcceleration = dv / dt;
        lastTime = timestamp;
        counter++;
        return currentAcceleration;
    }

    /**
     * @return the Average Acceleration of the system
     */
    public double getAvgAcceleration() {
        if (counter != 0) {
            return currentAcceleration / counter;
        } else return 0;
    }

    /**
     * @return the Current Acceleration of the system
     */
    public double getCurrentAcceleration() {
        return currentAcceleration;
    }
}
