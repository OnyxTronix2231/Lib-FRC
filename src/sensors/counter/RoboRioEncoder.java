package sensors.counter;

import edu.wpi.first.wpilibj.Encoder;
import sensors.counter.interfaces.Counter;

public class RoboRioEncoder implements Counter {
  private final Encoder encoder;

  public RoboRioEncoder(final Encoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public int getCount() {
    return encoder.get();
  }

  @Override
  public double getRate() {
    return encoder.getRate();
  }

  @Override
  public void reset() {
    encoder.reset();
  }
}
