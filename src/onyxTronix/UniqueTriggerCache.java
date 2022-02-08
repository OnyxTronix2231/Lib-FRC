package onyxTronix;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import humanControls.ConsoleController;

import java.util.ArrayList;

public abstract class UniqueTriggerCache<T extends Trigger> {

  protected final ConsoleController joystick;
  private final ArrayList<Integer> usedTriggerNumbers;

  public UniqueTriggerCache(final ConsoleController joystick) {
    this.joystick = joystick;
    this.usedTriggerNumbers = new ArrayList<>();
  }

  public T createJoystickTrigger(final int triggerNumber) {
    return createJoystickTrigger(triggerNumber, true);
  }

  public T createJoystickTrigger(final int triggerNumber, final boolean shouldBeCached) {
    if (shouldBeCached) {
      if (isTriggerUsed(triggerNumber)) {
        throw new IllegalArgumentException(
            String.format("The Trigger %d in Joystick %d is already used", triggerNumber, joystick.getPort()));
      }
      usedTriggerNumbers.add(triggerNumber);
    }
    return getJoystickTrigger(triggerNumber);
  }

  protected abstract T getJoystickTrigger(final int triggerNumber);

  private boolean isTriggerUsed(final int triggerNumber) {
    return this.usedTriggerNumbers.contains(triggerNumber);
  }
}
