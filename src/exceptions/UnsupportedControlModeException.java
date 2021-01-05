package exceptions;

public class UnsupportedControlModeException extends RuntimeException {
  public UnsupportedControlModeException(String controlMode){
    super(controlMode + " control mode is not supported." +
        " Only supported control modes are position and velocity");
  }
}
