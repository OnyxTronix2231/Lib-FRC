package humanControls;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Trigger;

@Deprecated(since = "2023", forRemoval = true)
public class JoystickAxis extends Trigger {

  private final static double DEFAULT_DEAD_BAND = 0.08;
  private final ConsoleController joystick;
  private final int axisNumber;
  private final double deadBand;

  /**
   * Create a joystick axis for triggering commands.
   *
   * @param joystick   The GenericHID object that has the axis (e.g. Joystick, KinectStick,
   *                   etc)
   * @param axisNumber The axis number
   */
  public JoystickAxis(final ConsoleController joystick, final int axisNumber, final double deadBand) {
    super(CommandScheduler.getInstance().getDefaultButtonLoop(), () -> Math.abs(joystick.getRawAxis(axisNumber)) >= deadBand);
    this.joystick = joystick;
    this.axisNumber = axisNumber;
    this.deadBand = deadBand;
  }

  public JoystickAxis(final ConsoleController joystick, final int axisNumber) {
    this(joystick, axisNumber, DEFAULT_DEAD_BAND);
  }

  /**
   * Gets the value of the joystick button.
   *
   * @return The value of the joystick button
   */

  public boolean get() {
    return Math.abs(getRawAxis()) >= deadBand;
  }

  public double getRawAxis() {
    return joystick.getRawAxis(axisNumber);
  }
}
