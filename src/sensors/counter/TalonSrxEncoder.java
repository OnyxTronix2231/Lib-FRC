package sensors.counter;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSrxEncoder implements Counter {
    private final TalonSRX m_talonSRX;
    private final int m_pidSlot;

    public TalonSrxEncoder(final TalonSRX talonSRX, final int pidSlot) {
        m_talonSRX = talonSRX;
        m_pidSlot = pidSlot;
    }

    @Override
    public int getCount() {
        return m_talonSRX.getSelectedSensorPosition(m_pidSlot);
    }

    @Override
    public double getRate() {
        return m_talonSRX.getSelectedSensorVelocity(m_pidSlot);
    }

    @Override
    public void reset() {
        m_talonSRX.setSelectedSensorPosition(0, m_pidSlot, 100);
    }
}