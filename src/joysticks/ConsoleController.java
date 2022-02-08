package joysticks;

import edu.wpi.first.hal.HAL;
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

    public ConsoleController(int port, int resourceType, int buttonDown, int buttonUp, int buttonLeft, int buttonRight,
                             int centerRight, int centerLeft, int stickLeft, int stickRight, int axisLeftX,
                             int axisLeftY, int axisRightX, int axisRightY, int leftTrigger, int rightTrigger,
                             int bumperRight, int bumperLeft) {
        super(port);

        HAL.report(resourceType, port + 1);

        this.buttonDown = buttonDown;
        this.buttonUp = buttonUp;
        this.buttonLeft = buttonLeft;
        this.buttonRight = buttonRight;
        this.centerRight = centerRight;
        this.centerLeft = centerLeft;
        this.stickLeft = stickLeft;
        this.stickRight = stickRight;
        this.bumperRight = bumperRight;
        this.bumperLeft = bumperLeft;

        this.axisLeftX = axisLeftX;
        this.axisLeftY = axisLeftY;
        this.axisRightX = axisRightX;
        this.axisRightY = axisRightY;
        this.leftTrigger = leftTrigger;
        this.rightTrigger = rightTrigger;
    }

    /**
     * Get the X axis value of left side of the controller.
     *
     * @return The axis value.
     */
    public double getLeftX() {
        return getRawAxis(axisLeftX);
    }

    /**
     * Get the X axis value of right side of the controller.
     *
     * @return The axis value.
     */
    public double getRightX() {
        return getRawAxis(axisRightX);
    }

    /**
     * Get the Y axis value of left side of the controller.
     *
     * @return The axis value.
     */
    public double getLeftY() {
        return getRawAxis(axisLeftY);
    }

    /**
     * Get the Y axis value of right side of the controller.
     *
     * @return The axis value.
     */
    public double getRightY() {
        return getRawAxis(axisRightY);
    }

    /**
     * Get the left trigger (LT) axis value of the controller. Note that this axis is bound to the
     * range of [0, 1] as opposed to the usual [-1, 1].
     *
     * @return The axis value.
     */
    public double getLeftTriggerAxis() {
        return getRawAxis(leftTrigger);
    }

    /**
     * Get the right trigger (RT) axis value of the controller. Note that this axis is bound to the
     * range of [0, 1] as opposed to the usual [-1, 1].
     *
     * @return The axis value.
     */
    public double getRightTriggerAxis() {
        return getRawAxis(rightTrigger);
    }

    /**
     * Read the value of the left bumper (LB) button on the controller.
     *
     * @return The state of the button.
     */
    public boolean getLeftBumper() {
        return getRawButton(bumperLeft);
    }

    /**
     * Read the value of the right bumper (RB) button on the controller.
     *
     * @return The state of the button.
     */
    public boolean getRightBumper() {
        return getRawButton(bumperRight);
    }

    /**
     * Whether the left bumper (LB) was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getLeftBumperPressed() {
        return getRawButtonPressed(bumperLeft);
    }

    /**
     * Whether the right bumper (RB) was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getRightBumperPressed() {
        return getRawButtonPressed(bumperRight);
    }

    /**
     * Whether the left bumper (LB) was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getLeftBumperReleased() {
        return getRawButtonReleased(buttonLeft);
    }

    /**
     * Whether the right bumper (RB) was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getRightBumperReleased() {
        return getRawButtonReleased(bumperRight);
    }

    /**
     * Read the value of the left stick button (LSB) on the controller.
     *
     * @return The state of the button.
     */
    public boolean getLeftStickButton() {
        return getRawButton(stickLeft);
    }

    /**
     * Read the value of the right stick button (RSB) on the controller.
     *
     * @return The state of the button.
     */
    public boolean getRightStickButton() {
        return getRawButton(stickRight);
    }

    /**
     * Whether the left stick button (LSB) was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getLeftStickButtonPressed() {
        return getRawButtonPressed(stickLeft);
    }

    /**
     * Whether the right stick button (RSB) was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getRightStickButtonPressed() {
        return getRawButtonPressed(stickRight);
    }

    /**
     * Whether the left stick button (LSB) was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getLeftStickButtonReleased() {
        return getRawButtonReleased(stickLeft);
    }

    /**
     * Whether the right stick (RSB) button was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getRightStickButtonReleased() {
        return getRawButtonReleased(stickRight);
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

    public int getAxisLeftX() {
        return axisLeftX;
    }

    public int getAxisRightX() {
        return axisRightX;
    }

    public int getAxisLeftY() {
        return axisLeftY;
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

    public int getStickLeft() {
        return stickLeft;
    }

    public int getStickRight() {
        return stickRight;
    }

    public int getButtonDown() {
        return buttonDown;
    }

    public int getButtonRight() {
        return buttonRight;
    }

    public int getButtonLeft() {
        return buttonLeft;
    }

    public int getButtonUp() {
        return buttonUp;
    }

    public int getCenterLeft() {
        return centerLeft;
    }

    public int getCenterRight() {
        return centerRight;
    }
}
