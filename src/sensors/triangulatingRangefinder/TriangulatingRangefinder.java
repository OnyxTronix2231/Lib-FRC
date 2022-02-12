package sensors.triangulatingRangefinder;

import edu.wpi.first.wpilibj.AnalogInput;

public class TriangulatingRangefinder extends AnalogInput {

    private final static double FORMULA_MULTIPLIER_CONSTANT = 29.988;
    private final static double FORMULA_POW_CONSTANT = -1.173;

    /**
     *this class work with GP2Y0A02YK0F IR sensor,
     * @param channel The channel number to represent. 0-3 are on-board 4-7 are on the MXP port.
     */
    public TriangulatingRangefinder(int channel) {
        super(channel);
    }

    /** returns the distance[cm] from object
     * sensor range: 10 - 80 [cm]
     */
    public double getDistance(){
        return FORMULA_MULTIPLIER_CONSTANT * Math.pow(this.getVoltage(), FORMULA_POW_CONSTANT);
    }
}
