package sensors.Switch;

import edu.wpi.first.wpilibj.DigitalInput;

public class Microswitch implements Switch {
    private final DigitalInput m_digitalInput;

    public Microswitch(final DigitalInput digitalInput) {
        m_digitalInput = digitalInput;
    }

    @Override
    public boolean isOpen() {
        return m_digitalInput.get();
    }
}