package onyxTronix;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import java.util.ArrayList;

public class UniqueTriggerCache {
  private final ArrayList<Integer> usedTriggerNumbers;
  private final GenericHID joystick;
  private final JoystickTriggerFactory joystickTriggerFactory;

  public UniqueTriggerCache(final GenericHID joystick, final JoystickTriggerFactory joystickTriggerFactory) {
    this.joystick = joystick;
    this.usedTriggerNumbers = new ArrayList<>();
    this.joystickTriggerFactory = joystickTriggerFactory;
  }

  public Trigger createJoystickTrigger(final int triggerNumber) {
    if (isTriggerUsed(triggerNumber)) {
      throw new IllegalArgumentException(
          String.format("The Trigger %d in Joystick %d is already used", triggerNumber, joystick.getPort()));
    }
    usedTriggerNumbers.add(triggerNumber);
    return joystickTriggerFactory.createJoystickTrigger(joystick, triggerNumber);
  }

  private boolean isTriggerUsed(final int triggerNumber) {
    return this.usedTriggerNumbers.contains(triggerNumber);
  }
}
