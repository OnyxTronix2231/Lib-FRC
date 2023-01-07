package commandControl;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public abstract class CommandConsoleController extends CommandGenericHID {

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

    /**
     * Construct an instance of a device.
     *
     * @param port The port index on the Driver Station that the device is plugged into.
     */
    public CommandConsoleController(int port,
                                    int resourceType,
                                    int buttonDown,
                                    int buttonUp, int buttonLeft,
                                    int buttonRight, int centerRight,
                                    int centerLeft, int stickLeft, int stickRight,
                                    int axisLeftX, int axisLeftY, int axisRightX, int axisRightY,
                                    int leftTrigger, int rightTrigger, int bumperRight, int bumperLeft) {
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
        this.axisLeftX = axisLeftX;
        this.axisLeftY = axisLeftY;
        this.axisRightX = axisRightX;
        this.axisRightY = axisRightY;
        this.leftTrigger = leftTrigger;
        this.rightTrigger = rightTrigger;
        this.bumperRight = bumperRight;
        this.bumperLeft = bumperLeft;
    }

    public Trigger buttonDown() {
        return this.button(buttonDown, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger buttonUp() {
        return this.button(buttonUp, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger buttonLeft() {
        return this.button(buttonLeft, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger buttonRight() {
        return this.button(buttonRight, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger centerRight() {
        return this.button(centerRight, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger centerLeft() {
        return this.button(centerLeft, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger stickLeft() {
        return this.button(stickLeft, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger stickRight() {
        return this.button(stickRight, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger bumperRight() {
        return this.button(bumperRight, CommandScheduler.getInstance().getDefaultButtonLoop());
    }

    public Trigger bumperLeft() {
        return this.button(bumperLeft, CommandScheduler.getInstance().getDefaultButtonLoop());
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

    public CommandJoystickAxis leftTrigger() {
        return this.axis(leftTrigger);
    }

    public CommandJoystickAxis leftTrigger(double deadBand) {
        return this.axis(leftTrigger, deadBand);
    }

    public CommandJoystickAxis rightTrigger() {
        return this.axis(rightTrigger);
    }

    public CommandJoystickAxis rightTrigger(double deadBand) {
        return this.axis(rightTrigger, deadBand);
    }

    public CommandJoystickAxis leftYAxis() {
        return this.axis(axisLeftY);
    }

    public CommandJoystickAxis leftYAxis(double deadBand) {
        return this.axis(axisLeftY, deadBand);
    }

    public CommandJoystickAxis leftXAxis() {
        return this.axis(axisLeftX);
    }

    public CommandJoystickAxis leftXAxis(double deadBand) {
        return this.axis(axisLeftX, deadBand);
    }

    public CommandJoystickAxis rightYAxis() {
        return this.axis(axisRightY);
    }

    public CommandJoystickAxis rightYAxis(double deadBand) {
        return this.axis(axisRightY, deadBand);
    }

    public CommandJoystickAxis rightXAxis() {
        return this.axis(axisRightX);
    }

    public CommandJoystickAxis rightXAxis(double deadBand) {
        return this.axis(axisRightX, deadBand);
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

    public CommandJoystickAxis axis(int axisNumber, double deadBand) {
        return new CommandJoystickAxis(this, axisNumber, deadBand);
    }

    public CommandJoystickAxis axis(int axisNumber) {
        return new CommandJoystickAxis(this, axisNumber);
    }
}
