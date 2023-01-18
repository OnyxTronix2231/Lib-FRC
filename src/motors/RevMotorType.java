package motors;

public enum RevMotorType {
    Neo550(42);
    private final int encoderUnitsPerRotation;

    private RevMotorType(int value){
        this.encoderUnitsPerRotation = value;
    }

    public int getEncoderUnitsPerRotation(){
        return encoderUnitsPerRotation;
    }
}
