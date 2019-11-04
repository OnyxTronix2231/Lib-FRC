package onyxTronix;

public enum Axis {
  kLeftX(0),
  kLeftY(1),
  kLeftTrigger(2),
  kRightTrigger(3),
  kRightX(4),
  kRightY(5);

  public final int value;

  Axis(int value) {
    this.value = value;
  }
}
