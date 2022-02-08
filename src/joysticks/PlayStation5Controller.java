package joysticks;

import edu.wpi.first.hal.FRCNetComm.tResourceType;

public class PlayStation5Controller extends ConsoleController {

    /**
     * Represents a digital button on an PS5 Controller.
     */
    private enum Button {

        Square(1),
        X(2),
        Circle(3),
        Triangle(4),
        LeftBumper(5),
        RightBumper(6),
        Share(9),
        Options(10),
        LeftStick(11),
        RightStick(12),
        PS(13),
        PadPress(14),
        Mute(15);

        public final int value;

        Button(int value) {
            this.value = value;
        }
    }

    /**
     * Represents an axis on an PS5 Controller.
     */
    private enum Axis {

        LeftX(0),
        LeftY(1),
        RightX(2),
        LeftTrigger(3),
        RightTrigger(4),
        RightY(5);

        public final int value;

        Axis(int value) {
            this.value = value;
        }
    }

    /**
     * Construct an instance of a joystick. The joystick index is the USB port on the drivers
     * station.
     *
     * @param port The port on the Driver Station that the joystick is plugged into.
     */
    public PlayStation5Controller(int port) {
        super(port, tResourceType.kResourceType_Joystick, Button.X.value, Button.Triangle.value, Button.Square.value,
                Button.Circle.value, Button.Options.value, Button.Share.value, Button.LeftStick.value,
                Button.RightStick.value, Axis.LeftX.value, Axis.LeftY.value, Axis.RightX.value, Axis.RightY.value,
                Axis.LeftTrigger.value, Axis.RightTrigger.value, Button.RightBumper.value, Button.LeftBumper.value);
    }

    @Override
    public double getRawAxis(int axis) {
        if (axis == Axis.LeftTrigger.value || axis == Axis.RightTrigger.value) {
            return (super.getRawAxis(axis) + 1) / 2;
        }
        return super.getRawAxis(axis);
    }
}
