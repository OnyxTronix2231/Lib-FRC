package  sensors.LIDAR_Luna;

import java.nio.ByteBuffer;

import edu.wpi.first.hal.I2CJNI;
import edu.wpi.first.wpilibj.I2C;

public class LIDAR_Luna {

    private static final byte DEVICE_ADDRESS = 0x10;
    private static final byte TFL_DIST_LO = 0x00;  //R Unit: cm
    private static final byte TFL_DIST_HI = 0x01;  //R
    private static final byte TFL_FLUX_LO = 0x02;  //R
    private static final byte TFL_FLUX_HI = 0x03;  //R
    private static final byte TFL_TEMP_LO = 0x04;  //R Unit: 0.01 Celsius
    private static final byte TFL_TEMP_HI = 0x05;  //R
    private static final byte TFL_TICK_LO = 0x06;  //R Timestamp
    private static final byte TFL_TICK_HI = 0x07;  //R
    private static final byte TFL_ERR_LO  = 0x08;  //R
    private static final byte TFL_ERR_HI  = 0x09;  //R
    private static final byte TFL_VER_REV = 0x0A;  //R
    private static final byte TFL_VER_MIN = 0x0B;  //R
    private static final byte TFL_VER_MAJ = 0x0C;  //R

    private final byte port;

    private final ByteBuffer buffer = ByteBuffer.allocateDirect(8);

    public LIDAR_Luna(I2C.Port port) {
        this.port = (byte) port.value;
        I2CJNI.i2CInitialize(this.port);
    }

    public void enable() { //TODO: need to check on robot
        writeRegister(0x25, 0x00);
    }

    public void disable() { //TODO: check
        writeRegister(0x25, 0x01);
    }

    public int getDistance() {
        ByteBuffer data = readAll();
        return data.get(0);
    }

    private int writeRegister(int address, int value) {
        buffer.put(0, (byte) address);
        buffer.put(1, (byte) value);

        return I2CJNI.i2CWrite(port, DEVICE_ADDRESS, buffer, (byte) 2);
    }

    private ByteBuffer readAll() {
        buffer.put(0, (byte) TFL_DIST_LO);
        buffer.put(1, (byte) TFL_DIST_HI);
        buffer.put(2, (byte) TFL_FLUX_LO);
        buffer.put(3, (byte) TFL_FLUX_HI);
        buffer.put(4, (byte) TFL_TEMP_LO);
        buffer.put(5, (byte) TFL_TEMP_HI);
        I2CJNI.i2CWrite(port, DEVICE_ADDRESS, buffer, (byte) 6);
        I2CJNI.i2CRead(port, DEVICE_ADDRESS, buffer, (byte) 7); //can be also 6
        return buffer;
    }
}
