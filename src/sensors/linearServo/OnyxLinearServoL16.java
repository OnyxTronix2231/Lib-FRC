package sensors.linearServo;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class OnyxLinearServoL16 extends Servo {

    private final double m_speed;
    private final double m_length;
    private double setPos;
    private double curPos;


    /**
     * Constructor.<br>
     *
     * <p>By default {@value #kDefaultMaxServoPWM} ms is used as the maxPWM value<br>
     * By default {@value #kDefaultMinServoPWM} ms is used as the minPWM value<br>
     *
     * @param channel The PWM channel to which the servo is attached. 0-9 are on-board, 10-19 are on
     *                the MXP port
     *
     * Parameters for L16-R Actuonix Linear Actuators
     *
     * @param channel PWM channel used to control the servo
     * @param length max length of the servo [mm]
     * @param speed max speed of the servo [mm/second]
     */
    public OnyxLinearServoL16(int channel, int length, int speed) {
        super(channel);
        setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
        m_length = length;
        m_speed = speed;

    }
    /**
     * Run this method in any periodic function to update the position estimation of your
     servo
     *
     * @param setPoint the target position of the servo [mm]
     */
    public void setPosition(double setPoint) {
        setPos = MathUtil.clamp(setPoint, 0, m_length);
        setSpeed( (setPos/m_length * 2)-1);
    }
    /**
     * Run this method in any periodic function to update the position estimation of your
     servo
     */
    public void updateCurPos() {
        curPos = m_length * this.get();

    }
    /**
     * Current position of the servo, must be calling {@link #updateCurPos()
    updateCurPos()} periodically
     *
     * @return Servo Position [mm]
     */
    public double getPosition() {
        return curPos;
    }

    /**
     * Checks if the servo is at its target position, must be calling {@link #updateCurPos()
    updateCurPos()} periodically
     * @return true when servo is at its target
     */
    public boolean isFinished() {
        return curPos == setPos;
    }
}
