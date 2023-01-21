package sensors.counter;

public enum CounterType {
    Neo550(42),
    MagEncoder(4096);

    private final int encoderUnitsPerRotation;

    CounterType(int value) {
        this.encoderUnitsPerRotation = value;
    }

    public int getEncoderUnitsPerRotation() {
        return encoderUnitsPerRotation;
    }
}
