package commandControl;

import commandControl.CommandConsoleController;
import edu.wpi.first.wpilibj.DriverStation;

public class CommandPlaystation4Controller extends CommandConsoleController {

    public CommandPlaystation4Controller(int port) {
        super(port, 24, Button.X.value,
                Button.Circle.value, Button.Square.value,
                Button.Triangle.value, Button.Options.value,
                Button.Share.value, Button.LeftStick.value,
                Button.RightStick.value, Axis.LeftX.value,
                Axis.LeftY.value, Axis.RightX.value,
                Axis.RightY.value, Axis.LeftTrigger.value,
                Axis.RightTrigger.value, Button.RightBumper.value,
                Button.LeftBumper.value);
    }

    public double getRawAxis(int axis) {
        if (!DriverStation.isJoystickConnected(this.getHID().getPort())) {
            return 0.0;
        } else {
            return axis != Axis.LeftTrigger.value && axis != Axis.RightTrigger.value ?
                    super.getRawAxis(axis) : (super.getRawAxis(axis) + 1.0) / 2.0;
        }
    }

    private static enum Button {
        Square(1),
        X(2),
        Triangle(3),
        Circle(4),
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

        private Button(int value) {
            this.value = value;
        }
    }

    private static enum Axis {
        LeftX(0),
        LeftY(1),
        RightX(2),
        LeftTrigger(3),
        RightTrigger(4),
        RightY(5);

        public final int value;

        private Axis(int value) {
            this.value = value;
        }
    }
}
