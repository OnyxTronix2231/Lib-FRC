package sensors.linearServo;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class LinearServo extends Servo {

    private final static double MAX_PWM_PULSE = 2.0;
    private final static double DEADBAND_MAX = 1.8;
    private final static double MIDDLE_PWM_PULSE = 1.5;
    private final static double DEADBAND_MIN = 1.2;
    private final static double MIN_PWM_PULSE = 1;
    private final double maxSpeed;
    private final double maxLength;
    private double setPos;
    private double currentPos;

    /**
     * Parameters for L16-R Actuonix Linear Actuators
     *
     * @param channel PWM channel used to control the servo
     * @param maxLength max length of the servo [mm]
     * @param maxSpeed max speed of the servo [mm/second]
     */
    public LinearServo(int channel, int maxLength, int maxSpeed) {
        super(channel);
        this.maxLength = maxLength;
        this.maxSpeed = maxSpeed;
        setBounds(MAX_PWM_PULSE, DEADBAND_MAX, MIDDLE_PWM_PULSE, DEADBAND_MIN, MIN_PWM_PULSE);
        currentPos = this.maxLength * this.get();
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
    public void updateCurrentPosition() {
        double timestamp = Timer.getFPGATimestamp();
        double dt = timestamp - lastTime;
        double distance = maxSpeed * dt;
        if (currentPos > setPos + distance) {
            currentPos -= distance;
        } else if (currentPos < setPos - distance) {
            currentPos += distance;
        } else {
            currentPos = setPos;
        }
        lastTime = timestamp;
    }

    /**
     * Current position of the servo, must be calling {@link #updateCurrentPosition()
     * updateCurPos()} periodically
     *
     * @return Servo Position [mm]
     */
    public double getPosition() {
        return currentPos;
    }

    /**
     * Checks if the servo is at its target position, must be calling {@link #updateCurrentPosition()
     * updateCurPos()} periodically
     *
     * @return true when servo is at its target
     */
    public boolean isOnTarget() {
        return currentPos == setPos;
    }
}

