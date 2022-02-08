package joysticks;

import edu.wpi.first.hal.FRCNetComm;
import edu.wpi.first.hal.HAL;

public class OnyxXboxController extends ConsoleController {

    /**
     * Represents a digital button on an XboxController.
     */
    private enum Button {

        kA(1),
        kB(2),
        kX(3),
        kY(4),
        kBumperLeft(5),
        kBumperRight(6),
        kBack(7),
        kStart(8),
        kStickLeft(9),
        kStickRight(10);

        @SuppressWarnings({"MemberName", "PMD.SingularField"})
        public final int value;

        Button(int value) {
            this.value = value;
        }
    }

    /**
     * Represents an axis on an XboxController.
     */
    private enum Axis {

        kLeftX(0),
        kLeftY(1),
        kLeftTrigger(2),
        kRightTrigger(3),
        kRightX(4),
        kRightY(5);

        @SuppressWarnings({"MemberName", "PMD.SingularField"})
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
        super(port, Button.kA.value, Button.kY.value, Button.kX.value, Button.kB.value, Button.kStart.value,
            Button.kBack.value, Button.kStickLeft.value, Button.kStickRight.value, Axis.kLeftX.value, Axis.kLeftY.value,
            Axis.kRightX.value, Axis.kRightY.value, Axis.kLeftTrigger.value, Axis.kRightTrigger.value,
            Button.kBumperRight.value, Button.kBumperLeft.value);

        HAL.report(FRCNetComm.tResourceType.kResourceType_XboxController, port + 1);
    }
}
