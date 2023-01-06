package onyxTronix;

import humanControls.deprecatedHumanControls.ConsoleController;
import humanControls.deprecatedHumanControls.JoystickAxis;

@Deprecated
public class UniqueAxisCache extends UniqueTriggerCache<JoystickAxis> {

  public UniqueAxisCache(final ConsoleController joystick) {
    super(joystick);
  }

  @Override
  protected JoystickAxis getJoystickTrigger(int triggerNumber) {
    return new JoystickAxis(joystick, triggerNumber);
  }
}
