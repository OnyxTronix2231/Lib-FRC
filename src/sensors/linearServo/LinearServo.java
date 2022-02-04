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
     * convert setpoint[mm] to PWM value
     *
     * @param setpoint the target position of the servo [mm]
     */
    private double convertSetpointToPWM(double setpoint){
        setPos = MathUtil.clamp(setpoint, 0, maxLength);
        return (setPos / maxLength * 2) - 1;
    }

    /**
     * @param setpoint the target position of the servo [mm]
     */
    public void setPosition(double setpoint) {
        super.setSpeed(convertSetpointToPWM(setpoint));
    }

    /** calibrate the linear servo
     * you can override this function and use different position or use setPosition once.
     *
     * the calibrate position doesn't meter and you only need to do this once*/
    public void calibrate() {
        setPosition(0);
    }

    /** move the linear servo forward or reverse according to the speed sign
     *
     *@param speed determine the direction
     *
     * speed equal to 0 stops the linear servo
     * speed > 0 moves the linear servo forward
     * speed < 0 moves the linear servo reverse
     *
     * dont forget to calibrate once the linearServo other wise it will not stop.
     * */
    @Override
    public void setSpeed(double speed) {
        setPosition(speed == 0 ? currentPos : Math.min(Math.max(Math.ceil(speed),0) * maxLength , maxLength));
    }

    private double lastTime = 0;

    /**
     * Run this method in any periodic function to update the position estimation of your servo
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
     * Current position of the servo, must be calling {@link #updateCurrentPosition()} periodically
     *
     * dont forget to calibrate the linear servo before reading the position
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

    /** disable motor and stop it */
    public void disableLinearServo(){
        setDisabled();
    }
}

