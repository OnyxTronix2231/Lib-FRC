package onyxTronix;

import edu.wpi.first.wpilibj.GenericHID;

public class UniqueAxisCache extends UniqueTriggerCache<JoystickAxis> {

  public UniqueAxisCache(final GenericHID joystick) {
    super(joystick);
  }

  @Override
  protected JoystickAxis getJoystickTrigger(int triggerNumber) {
    return new JoystickAxis(joystick, triggerNumber);
  }
}
