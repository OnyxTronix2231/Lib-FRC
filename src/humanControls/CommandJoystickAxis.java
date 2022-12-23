package humanControls;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class CommandJoystickAxis extends Trigger {

    final static double DEFAULT_DEAD_BAND = 0.08;
    private final CommandConsoleController joystick;
    private final int axisNumber;

    /**
     * Create a joystick axis for triggering commands.
     *
     * @param joystick    The GenericHID object that has the axis (e.g. Joystick, KinectStick,
     *                    etc)
     * @param axisNumber  The axis number
     */
    public CommandJoystickAxis(final CommandConsoleController joystick, final int axisNumber, final double deadBand) {
        super(new Trigger(CommandScheduler.getInstance().getDefaultButtonLoop(), () -> Math.abs(joystick.getRawAxis(axisNumber)) >= deadBand));
        this.joystick = joystick;
        this.axisNumber = axisNumber;
    }

    public CommandJoystickAxis(final CommandConsoleController joystick, final int axisNumber) {
        this(joystick, axisNumber, DEFAULT_DEAD_BAND);
    }

    public double getRawAxis() {
        return joystick.getRawAxis(axisNumber);
    }
}
