package data;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class OnyxGenericValue {

    private GenericEntry value;

    public OnyxGenericValue(String valueName, String tabName) {
        value = Shuffleboard.getTab(tabName).add(valueName, 0).getEntry();
    }

    public double getDouble() {
        return value.getDouble(0);
    }

    public long getLong() {
        return value.getInteger(0);
    }

    public boolean getBoolean() {
        return value.getString("false").equals("true");
    }
}
