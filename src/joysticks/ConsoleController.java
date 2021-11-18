package joysticks;

import edu.wpi.first.wpilibj.GenericHID;

public class ConsoleController extends GenericHID {

    private final int buttonDown;
    private final int buttonUp;
    private final int buttonLeft;
    private final int buttonRight;
    private final int centerRight;
    private final int centerLeft;
    private final int stickLeft;
    private final int stickRight;
    private final int axisLeftX;
    private final int axisLeftY;
    private final int axisRightX;
    private final int axisRightY;
    private final int leftTrigger;
    private final int rightTrigger;
    private final int bumperRight;
    private final int bumperLeft;
    private final int port;

    public ConsoleController(int port, int buttonDown, int buttonUp, int buttonLeft, int buttonRight, int centerRight,
                             int centerLeft, int stickLeft, int stickRight, int axisLeftX, int axisLeftY,
                             int axisRightX, int axisRightY, int leftTrigger, int rightTrigger, int bumperRight,
                             int bumperLeft) {
        super(port);

        this.port = port;

        this.buttonDown = buttonDown;
        this.buttonUp = buttonUp;
        this.buttonLeft = buttonLeft;
        this.buttonRight = buttonRight;
        this.centerRight = centerRight;
        this.centerLeft = centerLeft;
        this.stickLeft = stickLeft;
        this.stickRight = stickRight;
        this.axisLeftX = axisLeftX;

        this.axisLeftY = axisLeftY;
        this.axisRightX = axisRightX;
        this.axisRightY = axisRightY;
        this.leftTrigger = leftTrigger;
        this.rightTrigger = rightTrigger;
        this.bumperRight = bumperRight;
        this.bumperLeft = bumperLeft;
    }

    @Override
    public double getX(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawAxis(axisLeftX);
        } else {
            return getRawAxis(axisRightX);
        }
    }

    @Override
    public double getY(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawAxis(axisLeftY);
        } else {
            return getRawAxis(axisRightY);
        }
    }

    /**
     * Get the port of the controller.
     */
    public int getPort(){
        return port;
    }

    /**
     * Get the trigger axis value of the controller.
     *
     * @param hand Side of controller whose value should be returned.
     * @return The trigger axis value of the controller.
     */
    public double getRawTriggerAxis(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawAxis(leftTrigger);
        } else {
            return getRawAxis(rightTrigger);
        }
    }

    /**
     * Read the value of the bumper button on the controller.
     *
     * @param hand Side of controller whose value should be returned.
     * @return The state of the button.
     */
    public boolean getRawBumper(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButton(bumperLeft);
        } else {
            return getRawButton(bumperRight);
        }
    }

    /**
     * Whether the bumper was pressed since the last check.
     *
     * @param hand Side of controller whose value should be returned.
     * @return Whether the button was pressed since the last check.
     */
    public boolean getRawBumperPressed(Hand hand) {
        if (hand == Hand.kLeft) {
            return getRawButtonPressed(bumperLeft);
        } else {
            return getRawButtonPressed(bumperRight);
        }
    }

    /**
     * Whether the bumper was released since the last check.
     *
     * @param hand Side of controller whose value should be returned.
     * @return Whether the button was released since the last check.
     */
    public boolean getRawBumperReleased(Hand hand) {
        if (hand == Hand.kLeft) {
            return getRawButtonReleased(bumperLeft);
        } else {
            return getRawButtonReleased(bumperRight);
        }
    }

    /**
     * Read the value of the stick button on the controller.
     *
     * @param hand Side of controller whose value should be returned.
     * @return The state of the button.
     */
    public boolean getRawStickButton(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButton(stickLeft);
        } else {
            return getRawButton(stickRight);
        }
    }

    /**
     * Whether the stick button was pressed since the last check.
     *
     * @param hand Side of controller whose value should be returned.
     * @return Whether the button was pressed since the last check.
     */
    public boolean getRawStickButtonPressed(Hand hand) {
        if (hand == Hand.kLeft) {
            return getRawButtonPressed(stickLeft);
        } else {
            return getRawButtonPressed(stickRight);
        }
    }

    /**
     * Whether the stick button was released since the last check.
     *
     * @param hand Side of controller whose value should be returned.
     * @return Whether the button was released since the last check.
     */
    public boolean getRawStickButtonReleased(Hand hand) {
        if (hand == Hand.kLeft) {
            return getRawButtonReleased(stickLeft);
        } else {
            return getRawButtonReleased(stickRight);
        }
    }

    /**
     * Read the value of the button down on the controller.
     *
     * @return The state of the button.
     */
    public boolean getRawDownButton() {
        return getRawButton(buttonDown);
    }

    /**
     * Whether the button down was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getRawButtonDownPressed() {
        return getRawButtonPressed(buttonDown);
    }

    /**
     * Whether the button down was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getRawButtonDownReleased() {
        return getRawButtonReleased(buttonDown);
    }

    /**
     * Read the value of the button right on the controller.
     *
     * @return The state of the button.
     */
    public boolean getRawButtonRight() {
        return getRawButton(buttonRight);
    }

    /**
     * Whether the button right was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getRawButtonRightPressed() {
        return getRawButtonPressed(buttonRight);
    }

    /**
     * Whether the button right was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getRawButtonRightReleased() {
        return getRawButtonReleased(buttonRight);
    }

    /**
     * Read the value of the button left on the controller.
     *
     * @return The state of the button.
     */
    public boolean getRawButtonLeft() {
        return getRawButton(buttonLeft);
    }

    /**
     * Whether the button left was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getRawButtonLeftPressed() {
        return getRawButtonPressed(buttonLeft);
    }

    /**
     * Whether the button left was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getRawButtonLeftReleased() {
        return getRawButtonReleased(buttonLeft);
    }

    /**
     * Read the value of the button up on the controller.
     *
     * @return The state of the button.
     */
    public boolean getRawButtonUp() {
        return getRawButton(buttonUp);
    }

    /**
     * Whether the button up was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getRawButtonUpPressed() {
        return getRawButtonPressed(buttonUp);
    }

    /**
     * Whether the button up was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getRawButtonUpReleased() {
        return getRawButtonReleased(buttonUp);
    }

    /**
     * Read the value of the center left button on the controller.
     *
     * @return The state of the button.
     */
    public boolean getRawCenterLeftButton() {
        return getRawButton(centerLeft);
    }

    /**
     * Whether the center left button button was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getRawCenterLeftButtonPressed() {
        return getRawButtonPressed(centerLeft);
    }

    /**
     * Whether the center left button was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getRawCenterLeftButtonReleased() {
        return getRawButtonReleased(centerLeft);
    }

    /**
     * Read the value of the center right button on the controller.
     *
     * @return The state of the button.
     */
    public boolean getRawCenterRightButton() {
        return getRawButton(centerRight);
    }

    /**
     * Whether the center right button was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getRawCenterRightButtonPressed() {
        return getRawButtonPressed(centerRight);
    }

    /**
     * Whether the center right button was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getRawCenterRightButtonReleased() {
        return getRawButtonReleased(centerRight);
    }

    public int getButtonDown() {
        return buttonDown;
    }

    public int getButtonUp() {
        return buttonUp;
    }

    public int getButtonLeft() {
        return buttonLeft;
    }

    public int getButtonRight() {
        return buttonRight;
    }

    public int getCenterRight() {
        return centerRight;
    }

    public int getCenterLeft() {
        return centerLeft;
    }

    public int getStickLeft() {
        return stickLeft;
    }

    public int getStickRight() {
        return stickRight;
    }

    public int getAxisLeftX() {
        return axisLeftX;
    }

    public int getAxisLeftY() {
        return axisLeftY;
    }

    public int getAxisRightX() {
        return axisRightX;
    }

    public int getAxisRightY() {
        return axisRightY;
    }

    public int getLeftTrigger() {
        return leftTrigger;
    }

    public int getRightTrigger() {
        return rightTrigger;
    }

    public int getBumperRight() {
        return bumperRight;
    }

    public int getBumperLeft() {
        return bumperLeft;
    }
}
