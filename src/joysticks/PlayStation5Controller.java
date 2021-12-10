package joysticks;

import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;

public class PlayStation5Controller extends ConsoleController {

    /**
     * Represents a digital button on an PS5 Controller.
     */
    public enum Button {
        kSquare(1),
        kX(2),
        kCircle(3),
        kTriangle(4),
        kLeftBumper(5),
        kRightBumper(6),
        kShare(9),
        kOptions(10),
        kLeftStick(11),
        kRightStick(12),
        kPS(13),
        kPadPress(14),
        kMute(15);

        public final int value;

        Button(int value) {
            this.value = value;
        }
    }

    /**
     * Represents an axis on an PS5 Controller.
     */
    public enum Axis {
        kLeftX(0),
        kLeftY(1),
        KRightX(2),
        kLeftTrigger(3),
        kRightTrigger(4),
        kRightY(5);

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
        super(port, Button.kX.value, Button.kTriangle.value, Button.kSquare.value, Button.kCircle.value,
            Button.kOptions.value, Button.kShare.value, Button.kLeftStick.value, Button.kRightStick.value,
            Axis.kLeftX.value, Axis.kLeftY.value, Axis.KRightX.value, Axis.kRightY.value, Axis.kLeftTrigger.value,
            Axis.kRightTrigger.value, Button.kLeftBumper.value, Button.kRightBumper.value);

        HAL.report(tResourceType.kResourceType_Joystick, port + 1);
    }

    @Override
    public double getRawAxis(int axis) {
        if(axis == Axis.kLeftTrigger.value || axis == Axis.kRightTrigger.value) {
            return (super.getRawAxis(axis) + 1) / 2;
        }
        return super.getRawAxis(axis);
    }
}
