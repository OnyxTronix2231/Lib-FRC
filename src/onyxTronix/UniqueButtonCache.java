package onyxTronix;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import joysticks.ConsoleController;

public class UniqueButtonCache extends UniqueTriggerCache<JoystickButton> {

  public UniqueButtonCache(final ConsoleController joystick) {
    super(joystick);
  }

  @Override
  protected JoystickButton getJoystickTrigger(final int triggerNumber) {
    return new JoystickButton(joystick, triggerNumber);
  }
}
