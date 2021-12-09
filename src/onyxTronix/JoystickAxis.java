package onyxTronix;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import joysticks.ConsoleController;
import joysticks.PlayStation5Controller;

public class JoystickAxis extends Trigger {

  /**
   * @deprecated Replaced by {@link edu.wpi.first.wpilibj.XboxController.Axis}.
   */
  @Deprecated
  public enum AxisMap {
    kLeftX(0),
    kLeftY(1),
    kLeftTrigger(2),
    kRightTrigger(3),
    kRightX(4),
    kRightY(5);

    public final int value;

    AxisMap(int value) {
      this.value = value;
    }
  }

  private final static double DEFAULT_DEAD_BAND = 0.08;
  private final ConsoleController joystick;
  private final int axisNumber;
  private final double deadBand;

  /**
   * Create a joystick axis for triggering commands.
   *
   * @param joystick   The GenericHID object that has the axis (e.g. Joystick, KinectStick,
   *                   etc)
   * @param axisNumber The axis number (see {@link}
   */
  public JoystickAxis(final ConsoleController joystick, final int axisNumber, final double deadBand) {
    this.joystick = joystick;
    this.axisNumber = axisNumber;
    this.deadBand = deadBand;
  }

  public JoystickAxis(final ConsoleController joystick, final int axisNumber) {
    this.joystick = joystick;
    this.axisNumber = axisNumber;
    this.deadBand = DEFAULT_DEAD_BAND;
  }

  /**
   * Gets the value of the joystick button.
   *
   * @return The value of the joystick button
   */
  @Override
  public boolean get() {
    return Math.abs(joystick.getRawAxis(axisNumber)) >= deadBand;
  }

  public double getRawAxis() {
    if (joystick instanceof PlayStation5Controller) {
      if (axisNumber == 3 || axisNumber == 4) {
        return (joystick.getRawAxis(axisNumber) + 1) / 2;
      }
    }
    return joystick.getRawAxis(axisNumber);
  }
}
