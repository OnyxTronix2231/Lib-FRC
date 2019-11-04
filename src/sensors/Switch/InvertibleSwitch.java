package sensors.Switch;

public class InvertibleSwitch implements Switch {
    private final Switch m_switch;
    private final boolean m_isInverted;

    public InvertibleSwitch(final Switch aSwitch, final boolean isInverted) {
        m_switch = aSwitch;
        m_isInverted = isInverted;
    }

    @Override
    public boolean isOpen() {
        return m_switch.isOpen() ^ m_isInverted;
    }
}
