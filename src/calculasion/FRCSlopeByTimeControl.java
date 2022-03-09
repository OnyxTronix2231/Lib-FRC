package calculasion;

import edu.wpi.first.wpilibj.Timer;

public class FRCSlopeByTimeControl {

    private int counter = 0;
    private double lastTime = 0;
    private double lastSpeed = 0;
    private double currentSlope = 0;
    private double totalSlope = 0;

    /**
     * call this function on periodic
     *
     * @param stamp - the current speed of the system
     * @return the current Acceleration of the system
     */
    public double calculateSlope(double stamp) {
        double timestamp = Timer.getFPGATimestamp();
        double dt = timestamp - lastTime;
        lastTime = timestamp;
        double dx = stamp - lastSpeed;
        lastSpeed = stamp;
        currentSlope = dx / dt;
        totalSlope += currentSlope;
        counter++;
        return currentSlope;
    }

    /**
     * @return the Average Acceleration of the system
     */
    public double getAvgSlope() {
        if (counter != 0) {
            return totalSlope / counter;
        }
        return 0;
    }

    /**
     * @return the Current Acceleration of the system
     */
    public double getCurrentSlope() {
        return currentSlope;
    }

    /**
     * reset all parameters
     */
    public void reset(){
        counter = 0;
        totalSlope = 0;
        currentSlope = 0;
        lastTime = 0;
    }
}
