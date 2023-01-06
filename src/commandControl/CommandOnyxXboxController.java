package commandControl;

import edu.wpi.first.hal.FRCNetComm;

public class CommandOnyxXboxController extends CommandConsoleController {

    /**
     * Represents a digital button on an XboxController.
     */
    private enum Button {

        A(1),
        B(2),
        X(3),
        Y(4),
        BumperLeft(5),
        BumperRight(6),
        Back(7),
        Start(8),
        StickLeft(9),
        StickRight(10);

        public final int value;

        Button(int value) {
            this.value = value;
        }
    }

    /**
     * Represents an axis on an XboxController.
     */
    private enum Axis {

        LeftX(0),
        LeftY(1),
        LeftTrigger(2),
        RightTrigger(3),
        RightX(4),
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
    public CommandOnyxXboxController(final int port) {
        super(port, FRCNetComm.tResourceType.kResourceType_XboxController, CommandOnyxXboxController.Button.A.value, CommandOnyxXboxController.Button.Y.value,
                CommandOnyxXboxController.Button.X.value, CommandOnyxXboxController.Button.B.value, CommandOnyxXboxController.Button.Start.value, CommandOnyxXboxController.Button.Back.value, CommandOnyxXboxController.Button.StickLeft.value,
                CommandOnyxXboxController.Button.StickRight.value, CommandOnyxXboxController.Axis.LeftX.value, CommandOnyxXboxController.Axis.LeftY.value, CommandOnyxXboxController.Axis.RightX.value, CommandOnyxXboxController.Axis.RightY.value,
                CommandOnyxXboxController.Axis.LeftTrigger.value, CommandOnyxXboxController.Axis.RightTrigger.value, CommandOnyxXboxController.Button.BumperRight.value, CommandOnyxXboxController.Button.BumperLeft.value);
    }
}
