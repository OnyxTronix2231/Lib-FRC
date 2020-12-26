package exceptions;

public class UnsupportedControlModeException extends RuntimeException {
  public UnsupportedControlModeException(){
    super("This control mode is not supported." +
        " Only supported control modes are position and velocity");
  }
}
