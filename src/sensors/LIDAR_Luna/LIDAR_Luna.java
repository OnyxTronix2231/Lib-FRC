package  sensors.LIDAR_Luna;

import java.nio.ByteBuffer;

import edu.wpi.first.hal.I2CJNI;
import edu.wpi.first.wpilibj.I2C;

public class LIDAR_Luna {

    private static final byte deviceAddress = 0x10; //TODO: need to check on robot

    private final byte port;

    private final ByteBuffer buffer = ByteBuffer.allocateDirect(2);

    public LIDAR_Luna(I2C.Port port) {
        this.port = (byte) port.value;
        I2CJNI.i2CInitialize(this.port);
    }

    public void startMeasuring() { //TODO: need to check on robot
        writeRegister(0x04, 0x08 | 32); // default plus bit 5
        writeRegister(0x11, 0xff);
        writeRegister(0x00, 0x04);
    }

    public void stopMeasuring() { //TODO: check
        writeRegister(0x11, 0x00);
    }

    public int getDistance() { //TODO: CHECK
        return readShort(0x10);
    } //was 0x8f and can also be 0x00

    private int writeRegister(int address, int value) {
        buffer.put(0, (byte) address);
        buffer.put(1, (byte) value);

        return I2CJNI.i2CWrite(port, deviceAddress, buffer, (byte) 2);
    }

    private short readShort(int address) {
        buffer.put(0, (byte) address);
        I2CJNI.i2CWrite(port, deviceAddress, buffer, (byte) 1);
        I2CJNI.i2CRead(port, deviceAddress, buffer, (byte) 2);
        return buffer.getShort(0);
    }
}
