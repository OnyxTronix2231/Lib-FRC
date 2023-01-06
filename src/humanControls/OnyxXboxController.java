package humanControls;

import edu.wpi.first.hal.FRCNetComm;

@Deprecated
public class OnyxXboxController extends ConsoleController {

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
    public OnyxXboxController(final int port) {
        super(port, FRCNetComm.tResourceType.kResourceType_XboxController, Button.A.value, Button.Y.value,
                Button.X.value, Button.B.value, Button.Start.value, Button.Back.value, Button.StickLeft.value,
                Button.StickRight.value, Axis.LeftX.value, Axis.LeftY.value, Axis.RightX.value, Axis.RightY.value,
                Axis.LeftTrigger.value, Axis.RightTrigger.value, Button.BumperRight.value, Button.BumperLeft.value);
    }
}
