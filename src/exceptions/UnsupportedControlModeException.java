package exceptions;

public class UnsupportedControlModeException extends Exception {
  public UnsupportedControlModeException(){
    super("This control mode is not supported." +
        " Only supported control modes are position and velocity");
  }
}
