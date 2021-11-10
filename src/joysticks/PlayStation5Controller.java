package joysticks;

import edu.wpi.first.wpilibj.GenericHID;

public class PlayStation5Controller extends GenericHID {

    public enum Button {
        kSquare(1),
        kX(2),
        kCircle(3),
        kTriangle(4),
        kL1(5),
        kR1(6),
        kL2(7),
        kR2(8),
        kShare(9),
        kOptions(10),
        kL3(11),
        kR3(12),
        kPS(13),
        kPadPress(14),
        kMute(15);

        public final int value;

        Button(int value) {
            this.value = value;
        }
    }

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

    public PlayStation5Controller(int port) {
        super(port);
    }

    @Override
    public double getX(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawAxis(Axis.kLeftX.value);
        } else {
            return getRawAxis(Axis.KRightX.value);
        }
    }

    @Override
    public double getY(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawAxis(Axis.kLeftY.value);
        } else {
            return getRawAxis(Axis.kRightY.value);
        }
    }

    public double getTriggerAxis(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawAxis(Axis.kLeftTrigger.value);
        } else {
            return getRawAxis(Axis.kRightTrigger.value);
        }
    }

    public boolean getBumper(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButton(Button.kL1.value);
        } else {
            return getRawButton(Button.kR1.value);
        }
    }

    public boolean getBumperPressed(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButtonPressed(Button.kL1.value);
        } else {
            return getRawButtonPressed(Button.kR1.value);
        }
    }

    public boolean getBumperReleased(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButtonReleased(Button.kL1.value);
        } else {
            return getRawButtonReleased(Button.kR1.value);
        }
    }

    public boolean getTrigger(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButton(Button.kL2.value);
        } else {
            return getRawButton(Button.kR2.value);
        }
    }

    public boolean getTriggerPressed(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButtonPressed(Button.kL2.value);
        } else {
            return getRawButtonPressed(Button.kR2.value);
        }
    }

    public boolean getTriggerReleased(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButtonReleased(Button.kL2.value);
        } else {
            return getRawButtonReleased(Button.kR2.value);
        }
    }

    public boolean getStickButton(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButton(Button.kL3.value);
        } else {
            return getRawButton(Button.kR3.value);
        }
    }

    public boolean getStickButtonPressed(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButtonPressed(Button.kL3.value);
        } else {
            return getRawButtonPressed(Button.kR3.value);
        }
    }

    public boolean getStickButtonReleased(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButtonReleased(Button.kL3.value);
        } else {
            return getRawButtonReleased(Button.kR3.value);
        }
    }

    public boolean getSquareButton() {
        return getRawButton(Button.kSquare.value);
    }

    public boolean getSquareButtonPressed() {
        return getRawButtonPressed(Button.kSquare.value);
    }

    public boolean getSquareButtonReleased() {
        return getRawButtonReleased(Button.kSquare.value);
    }

    public boolean getXButton() {
        return getRawButton(Button.kX.value);
    }

    public boolean getXButtonPressed() {
        return getRawButtonPressed(Button.kX.value);
    }

    public boolean getXButtonReleased() {
        return getRawButtonReleased(Button.kX.value);
    }

    public boolean getCircleButton() {
        return getRawButton(Button.kCircle.value);
    }

    public boolean getCircleButtonPressed() {
        return getRawButtonPressed(Button.kCircle.value);
    }

    public boolean getCircleButtonReleased() {
        return getRawButtonReleased(Button.kCircle.value);
    }

    public boolean getTriangleButton() {
        return getRawButton(Button.kTriangle.value);
    }

    public boolean getTriangleButtonPressed() {
        return getRawButtonPressed(Button.kTriangle.value);
    }

    public boolean getTriangleButtonReleased() {
        return getRawButtonReleased(Button.kTriangle.value);
    }

    public boolean getShareButton() {
        return getRawButton(Button.kShare.value);
    }

    public boolean getShareButtonPressed() {
        return getRawButtonPressed(Button.kShare.value);
    }

    public boolean getShareButtonReleased() {
        return getRawButtonReleased(Button.kShare.value);
    }

    public boolean getOptionsButton() {
        return getRawButton(Button.kOptions.value);
    }

    public boolean getOptionsButtonPressed() {
        return getRawButtonPressed(Button.kOptions.value);
    }

    public boolean getOptionsButtonReleased() {
        return getRawButtonReleased(Button.kOptions.value);
    }

    public boolean getPSButton() {
        return getRawButton(Button.kPS.value);
    }

    public boolean getPSButtonPressed() {
        return getRawButtonPressed(Button.kPS.value);
    }

    public boolean getPSButtonReleased() {
        return getRawButtonReleased(Button.kPS.value);
    }

    public boolean getPadPressButton() {
        return getRawButton(Button.kPadPress.value);
    }

    public boolean getPadPressButtonPressed() {
        return getRawButtonPressed(Button.kPadPress.value);
    }

    public boolean getPadPressButtonReleased() {
        return getRawButtonReleased(Button.kPadPress.value);
    }

    public boolean getMuteButton() {
        return getRawButton(Button.kMute.value);
    }

    public boolean getMuteButtonPressed() {
        return getRawButtonPressed(Button.kMute.value);
    }

    public boolean getMuteButtonReleased() {
        return getRawButtonReleased(Button.kMute.value);
    }
}
