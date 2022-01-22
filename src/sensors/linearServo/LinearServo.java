package sensors.linearServo;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class LinearServo extends Servo {

    private final double maxSpeed;
    private final double maxLength;
    private double setPos;
    private double curPos;

    /**
     * Parameters for L16-R Actuonix Linear Actuators
     *
     * @param channel PWM channel used to control the servo
     * @param length  max length of the servo [mm]
     * @param speed   max speed of the servo [mm/second]
     */
    public LinearServo(int channel, int length, int speed) {
        super(channel);
        setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
        maxLength = length;
        maxSpeed = speed;
        curPos = maxLength * this.get();
    }

    /**
     * @param setpoint the target position of the servo [mm]
     */
    public void setPosition(double setpoint) {
        setPos = MathUtil.clamp(setpoint, 0, maxLength);
        setSpeed((setPos / maxLength * 2) - 1);
    }

    private double lastTime = 0;

    /**
     * Run this method in any periodic function to update the position estimation of your
     * servo
     */
    public void updateCurPos() {
        double timestamp = Timer.getFPGATimestamp();
        double dt = timestamp - lastTime;
        lastTime = timestamp;
        if (curPos > setPos + maxSpeed * dt) {
            curPos -= maxSpeed * dt;
        } else if (curPos < setPos - maxSpeed * dt) {
            curPos += maxSpeed * dt;
        } else {
            curPos = setPos;
        }
    }

    /**
     * Current position of the servo, must be calling {@link #updateCurPos()
     * updateCurPos()} periodically
     *
     * @return Servo Position [mm]
     */
    public double getPosition(){
        return curPos;
    }

    /**
     * Checks if the servo is at its target position, must be calling {@link #updateCurPos()
     * updateCurPos()} periodically
     *
     * @return true when servo is at its target
     */
    public boolean isOnTarget() {
        return curPos == setPos;
    }
}

