package onyxTronix;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import java.util.ArrayList;

public abstract class UniqueTriggerCache<T extends Trigger> {

  protected final GenericHID joystick;
  private final ArrayList<Integer> usedTriggerNumbers;

  public UniqueTriggerCache(final GenericHID joystick) {
    this.joystick = joystick;
    this.usedTriggerNumbers = new ArrayList<>();
  }

  public T cacheAndCreateJoystickTrigger(final int triggerNumber) {
    if (isTriggerUsed(triggerNumber)) {
      throw new IllegalArgumentException(
          String.format("The Trigger %d in Joystick %d is already used", triggerNumber, joystick.getPort()));
    }
    usedTriggerNumbers.add(triggerNumber);
    return createJoystickTrigger(triggerNumber);
  }

  protected abstract T createJoystickTrigger(final int triggerNumber);

  private boolean isTriggerUsed(final int triggerNumber) {
    return this.usedTriggerNumbers.contains(triggerNumber);
  }
}
