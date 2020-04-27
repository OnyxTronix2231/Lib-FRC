
public class TalonSrxEncoder implements Counter {
    private final TalonSRX talonSRX;
    private final int pidSlot;

    public TalonSrxEncoder(final TalonSRX talonSRX, final int pidSlot) {
        this.talonSRX = talonSRX;
        this.pidSlot = pidSlot;
    }

    @Override
    public int getCount() {
        return talonSRX.getSelectedSensorPosition(pidSlot);
    }

    @Override
    public double getRate() {
        return talonSRX.getSelectedSensorVelocity(pidSlot);
    }

    @Override
    public void reset() {
        talonSRX.setSelectedSensorPosition(0, pidSlot, 100);
    }
}