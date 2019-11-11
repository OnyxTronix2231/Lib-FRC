package onyxTronix;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class JoystickTriggerFactory {
  protected JoystickTriggerType joystickTriggerType;

  public JoystickTriggerFactory(JoystickTriggerType joystickTriggerType){
    this.joystickTriggerType=joystickTriggerType;
  }

  public Trigger createJoystickTrigger(final GenericHID joystick, final int triggerNumber){
    switch (joystickTriggerType) {
      case Button:
        return new JoystickButton(joystick, triggerNumber);
      case Axis:
        return new JoystickAxis(joystick, triggerNumber);
    }
    return null;
  }
}
