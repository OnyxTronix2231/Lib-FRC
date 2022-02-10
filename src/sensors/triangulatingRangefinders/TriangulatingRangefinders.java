package sensors.triangulatingRangefinders;

import edu.wpi.first.wpilibj.AnalogInput;

public class TriangulatingRangefinders extends AnalogInput {

    private double FORMULA_MULTIPLIER_CONSTANT = 29.988;
    private double FORMULA_POW_CONSTANT = -1.173;

    /**
     *this class work with GP2Y0A02YK0F IR sensor,
     * @param channel The channel number to represent. 0-3 are on-board 4-7 are on the MXP port.
     */
    public TriangulatingRangefinders(int channel) {
        super(channel);
    }

    /** returns the distance[cm] from object */
    public double getDistance(){
        return FORMULA_MULTIPLIER_CONSTANT * Math.pow(this.getVoltage(), FORMULA_POW_CONSTANT);
    }

}
