package onyxTronix;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class UniqueAxisCache extends UniqueTriggerCache {

  public UniqueAxisCache(final GenericHID joystick) {
    super(joystick);
  }

  @Override
  protected JoystickAxis createJoystickTrigger(int triggerNumber) {
    return new JoystickAxis(joystick, triggerNumber);
  }
}