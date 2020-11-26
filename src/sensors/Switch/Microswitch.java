package sensors.Switch;

import edu.wpi.first.wpilibj.DigitalInput;

public class Microswitch implements Switch {
  private final DigitalInput digitalInput;

  public Microswitch(final DigitalInput digitalInput) {
    this.digitalInput = digitalInput;
  }

  @Override
  public boolean isOpen() {
    return digitalInput.get();
  }
}
