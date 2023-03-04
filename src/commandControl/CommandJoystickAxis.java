package commandControl;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class CommandJoystickAxis extends Trigger {

    private final static double DEFAULT_DEAD_BAND = 0.08;
    private final CommandConsoleController joystick;
    private final int axisNumber;
    private final double deadBand;

    /**
     * Create a joystick axis for triggering commands.
     *
     * @param joystick   The GenericHID object that has the axis (e.g. Joystick, KinectStick,
     *                   etc)
     * @param axisNumber The axis number
     */
    public CommandJoystickAxis(final CommandConsoleController joystick, final int axisNumber, final double deadBand) {
        super(CommandScheduler.getInstance().getDefaultButtonLoop(), () -> Math.abs(joystick.getRawAxis(axisNumber)) >= deadBand);
        this.joystick = joystick;
        this.deadBand = MathUtil.clamp(Math.abs(deadBand), 0, 0.99);
        this.axisNumber = axisNumber;
    }

    public CommandJoystickAxis(final CommandConsoleController joystick, final int axisNumber) {
        this(joystick, axisNumber, DEFAULT_DEAD_BAND);
    }

    public double getRawAxis() {
        double rawVal = joystick.getRawAxis(axisNumber);
        double result = 0;
        if (Math.abs(rawVal) > deadBand) {
            result = ((rawVal - Math.signum(rawVal) * deadBand) / (1 - deadBand));
        }
        return result;
    }
}
