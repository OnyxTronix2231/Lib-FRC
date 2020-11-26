package sensors.Switch;

public class InvertibleSwitch implements Switch {
  private final Switch aSwitch;
  private final boolean isInverted;

  public InvertibleSwitch(final Switch aSwitch, final boolean isInverted) {
    this.aSwitch = aSwitch;
    this.isInverted = isInverted;
  }

  @Override
  public boolean isOpen() {
    return aSwitch.isOpen() ^ isInverted;
  }
}
