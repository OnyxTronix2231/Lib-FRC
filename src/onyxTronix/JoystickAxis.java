package onyxTronix;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class JoystickAxis extends Trigger {
  private final static double DEFAULT_DEAD_BAND = 0.08;
  private final GenericHID m_joystick;
  private final int m_axisNumber;
  private final double m_deadBand;

  /**
   * Create a joystick axis for triggering commands.
   *
   * @param joystick     The GenericHID object that has the axis (e.g. Joystick, KinectStick,
   *                     etc)
   * @param axisNumber The axis number (see {@link Axis}
   */
  public JoystickAxis(GenericHID joystick, int axisNumber, double deadBand) {
    m_joystick = joystick;
    m_axisNumber = axisNumber;
    m_deadBand = deadBand;
  }

  public JoystickAxis(GenericHID joystick, int axisNumber) {
    m_joystick = joystick;
    m_axisNumber = axisNumber;
    m_deadBand = DEFAULT_DEAD_BAND;
  }

  /**
   * Gets the value of the joystick button.
   *
   * @return The value of the joystick button
   */
  @Override
  public boolean get() {
    return Math.abs(m_joystick.getRawAxis(m_axisNumber)) >= m_deadBand;
  }
}
