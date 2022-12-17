package humanControls;

import edu.wpi.first.hal.FRCNetComm;

public class CommandPlaystation5Controller extends CommandConsoleController {

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
     * Construct an instance of a device.
     *
     * @param port         The port index on the Driver Station that the device is plugged into.
     */

    public CommandPlaystation5Controller(int port) {
        super(port, FRCNetComm.tResourceType.kResourceType_Joystick, CommandPlaystation5Controller.Button.X.value, CommandPlaystation5Controller.Button.Triangle.value, CommandPlaystation5Controller.Button.Square.value,
                CommandPlaystation5Controller.Button.Circle.value, CommandPlaystation5Controller.Button.Options.value, CommandPlaystation5Controller.Button.Share.value, CommandPlaystation5Controller.Button.LeftStick.value,
                CommandPlaystation5Controller.Button.RightStick.value, CommandPlaystation5Controller.Axis.LeftX.value, CommandPlaystation5Controller.Axis.LeftY.value, CommandPlaystation5Controller.Axis.RightX.value, CommandPlaystation5Controller.Axis.RightY.value,
                CommandPlaystation5Controller.Axis.LeftTrigger.value, CommandPlaystation5Controller.Axis.RightTrigger.value, CommandPlaystation5Controller.Button.RightBumper.value, CommandPlaystation5Controller.Button.LeftBumper.value);    }
}
