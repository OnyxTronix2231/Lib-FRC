package sensors.counter;

public enum RevCounterType {
    Neo550(42),
    MagEncoder(1024);

    private final int encoderUnitsPerRotation;

    RevCounterType(int value) {
        this.encoderUnitsPerRotation = value;
    }

    public int getEncoderUnitsPerRotation() {
        return encoderUnitsPerRotation;
    }
}
