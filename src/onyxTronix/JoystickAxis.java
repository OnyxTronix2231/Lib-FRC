package onyxTronix;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class JoystickAxis extends Trigger {
  private final static double DEFAULT_DEAD_BAND = 0.08;
  private final GenericHID joystick;
  private final int axisNumber;
  private final double deadBand;

  /**
   * Create a joystick axis for triggering commands.
   *
   * @param joystick     The GenericHID object that has the axis (e.g. Joystick, KinectStick,
   *                     etc)
   * @param axisNumber The axis number (see {@link Axis}
   */
  public JoystickAxis(GenericHID joystick, int axisNumber, double deadBand) {
    this.joystick = joystick;
    this.axisNumber = axisNumber;
    this.deadBand = deadBand;
  }

  public JoystickAxis(GenericHID joystick, int axisNumber) {
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
}
