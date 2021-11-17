package joysticks;

import edu.wpi.first.wpilibj.GenericHID;

public class Controller extends GenericHID {

    private final int BUTTON_DOWN;
    private final int BUTTON_UP;
    private final int BUTTON_LEFT;
    private final int BUTTON_RIGHT;
    private final int CENTER_RIGHT;
    private final int CENTER_LEFT;
    private final int STICK_LEFT;
    private final int STICK_RIGHT;
    private final int AXIS_LEFT_X;
    private final int AXIS_LEFT_Y;
    private final int AXIS_RIGHT_X;
    private final int AXIS_RIGHT_Y;
    private final int LEFT_TRIGGER;
    private final int RIGHT_TRIGGER;
    private final int BUMPER_RIGHT;
    private final int BUMPER_LEFT;
    private final int PORT;

    public Controller(int port, int button_down, int button_up, int button_left, int button_right, int center_right,
                      int center_left, int stick_left, int stick_right, int axis_left_x, int axis_left_y,
                      int axis_right_x, int axis_right_y, int left_trigger, int right_trigger, int bumper_right,
                      int bumper_left) {
        super(port);

        PORT = port;

        BUTTON_DOWN = button_down;
        BUTTON_UP = button_up;
        BUTTON_LEFT = button_left;
        BUTTON_RIGHT = button_right;
        CENTER_RIGHT = center_right;
        CENTER_LEFT = center_left;
        STICK_LEFT = stick_left;
        STICK_RIGHT = stick_right;
        AXIS_LEFT_X = axis_left_x;

        AXIS_LEFT_Y = axis_left_y;
        AXIS_RIGHT_X = axis_right_x;
        AXIS_RIGHT_Y = axis_right_y;
        LEFT_TRIGGER = left_trigger;
        RIGHT_TRIGGER = right_trigger;
        BUMPER_RIGHT = bumper_right;
        BUMPER_LEFT = bumper_left;
    }

    @Override
    public double getX(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawAxis(AXIS_LEFT_X);
        } else {
            return getRawAxis(AXIS_RIGHT_X);
        }
    }

    @Override
    public double getY(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawAxis(AXIS_LEFT_Y);
        } else {
            return getRawAxis(AXIS_RIGHT_Y);
        }
    }

    /**
     * Get the port of the controller.
     */
    public int getPort(){
        return PORT;
    }

    /**
     * Get the trigger axis value of the controller.
     *
     * @param hand Side of controller whose value should be returned.
     * @return The trigger axis value of the controller.
     */
    public double getTriggerAxis(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawAxis(LEFT_TRIGGER);
        } else {
            return getRawAxis(RIGHT_TRIGGER);
        }
    }

    /**
     * Read the value of the bumper button on the controller.
     *
     * @param hand Side of controller whose value should be returned.
     * @return The state of the button.
     */
    public boolean getBumper(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButton(BUMPER_LEFT);
        } else {
            return getRawButton(BUMPER_RIGHT);
        }
    }

    /**
     * Whether the bumper was pressed since the last check.
     *
     * @param hand Side of controller whose value should be returned.
     * @return Whether the button was pressed since the last check.
     */
    public boolean getBumperPressed(Hand hand) {
        if (hand == Hand.kLeft) {
            return getRawButtonPressed(BUMPER_LEFT);
        } else {
            return getRawButtonPressed(BUMPER_RIGHT);
        }
    }

    /**
     * Whether the bumper was released since the last check.
     *
     * @param hand Side of controller whose value should be returned.
     * @return Whether the button was released since the last check.
     */
    public boolean getBumperReleased(Hand hand) {
        if (hand == Hand.kLeft) {
            return getRawButtonReleased(BUMPER_LEFT);
        } else {
            return getRawButtonReleased(BUMPER_RIGHT);
        }
    }

    /**
     * Read the value of the stick button on the controller.
     *
     * @param hand Side of controller whose value should be returned.
     * @return The state of the button.
     */
    public boolean getStickButton(Hand hand) {
        if (hand.equals(Hand.kLeft)) {
            return getRawButton(STICK_LEFT);
        } else {
            return getRawButton(STICK_RIGHT);
        }
    }

    /**
     * Whether the stick button was pressed since the last check.
     *
     * @param hand Side of controller whose value should be returned.
     * @return Whether the button was pressed since the last check.
     */
    public boolean getStickButtonPressed(Hand hand) {
        if (hand == Hand.kLeft) {
            return getRawButtonPressed(STICK_LEFT);
        } else {
            return getRawButtonPressed(STICK_RIGHT);
        }
    }

    /**
     * Whether the stick button was released since the last check.
     *
     * @param hand Side of controller whose value should be returned.
     * @return Whether the button was released since the last check.
     */
    public boolean getStickButtonReleased(Hand hand) {
        if (hand == Hand.kLeft) {
            return getRawButtonReleased(STICK_LEFT);
        } else {
            return getRawButtonReleased(STICK_RIGHT);
        }
    }

    /**
     * Read the value of the button down on the controller.
     *
     * @return The state of the button.
     */
    public boolean getDownButton() {
        return getRawButton(BUTTON_DOWN);
    }

    /**
     * Whether the button down was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getButtonDownPressed() {
        return getRawButtonPressed(BUTTON_DOWN);
    }

    /**
     * Whether the button down was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getButtonDownReleased() {
        return getRawButtonReleased(BUTTON_DOWN);
    }

    /**
     * Read the value of the button right on the controller.
     *
     * @return The state of the button.
     */
    public boolean getButtonRight() {
        return getRawButton(BUTTON_RIGHT);
    }

    /**
     * Whether the button right was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getButtonRightPressed() {
        return getRawButtonPressed(BUTTON_RIGHT);
    }

    /**
     * Whether the button right was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getButtonRightReleased() {
        return getRawButtonReleased(BUTTON_RIGHT);
    }

    /**
     * Read the value of the button left on the controller.
     *
     * @return The state of the button.
     */
    public boolean getButtonLeft() {
        return getRawButton(BUTTON_LEFT);
    }

    /**
     * Whether the button left was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getButtonLeftPressed() {
        return getRawButtonPressed(BUTTON_LEFT);
    }

    /**
     * Whether the button left was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getButtonLeftReleased() {
        return getRawButtonReleased(BUTTON_LEFT);
    }

    /**
     * Read the value of the button up on the controller.
     *
     * @return The state of the button.
     */
    public boolean getButtonUp() {
        return getRawButton(BUTTON_UP);
    }

    /**
     * Whether the button up was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getButtonUpPressed() {
        return getRawButtonPressed(BUTTON_UP);
    }

    /**
     * Whether the button up was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getButtonUpReleased() {
        return getRawButtonReleased(BUTTON_UP);
    }

    /**
     * Read the value of the center left button on the controller.
     *
     * @return The state of the button.
     */
    public boolean getCenterLeftButton() {
        return getRawButton(CENTER_LEFT);
    }

    /**
     * Whether the center left button button was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getCenterLeftButtonPressed() {
        return getRawButtonPressed(CENTER_LEFT);
    }

    /**
     * Whether the center left button was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getCenterLeftButtonReleased() {
        return getRawButtonReleased(CENTER_LEFT);
    }

    /**
     * Read the value of the center right button on the controller.
     *
     * @return The state of the button.
     */
    public boolean getCenterRightButton() {
        return getRawButton(CENTER_RIGHT);
    }

    /**
     * Whether the center right button was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean getCenterRightButtonPressed() {
        return getRawButtonPressed(CENTER_RIGHT);
    }

    /**
     * Whether the center right button was released since the last check.
     *
     * @return Whether the button was released since the last check.
     */
    public boolean getCenterRightButtonReleased() {
        return getRawButtonReleased(CENTER_RIGHT);
    }

    public int getBUTTON_DOWN() {
        return BUTTON_DOWN;
    }

    public int getBUTTON_UP() {
        return BUTTON_UP;
    }

    public int getBUTTON_LEFT() {
        return BUTTON_LEFT;
    }

    public int getBUTTON_RIGHT() {
        return BUTTON_RIGHT;
    }

    public int getCENTER_RIGHT() {
        return CENTER_RIGHT;
    }

    public int getCENTER_LEFT() {
        return CENTER_LEFT;
    }

    public int getSTICK_LEFT() {
        return STICK_LEFT;
    }

    public int getSTICK_RIGHT() {
        return STICK_RIGHT;
    }

    public int getAXIS_LEFT_X() {
        return AXIS_LEFT_X;
    }

    public int getAXIS_LEFT_Y() {
        return AXIS_LEFT_Y;
    }

    public int getAXIS_RIGHT_X() {
        return AXIS_RIGHT_X;
    }

    public int getAXIS_RIGHT_Y() {
        return AXIS_RIGHT_Y;
    }

    public int getLEFT_TRIGGER() {
        return LEFT_TRIGGER;
    }

    public int getRIGHT_TRIGGER() {
        return RIGHT_TRIGGER;
    }

    public int getBUMPER_RIGHT() {
        return BUMPER_RIGHT;
    }

    public int getBUMPER_LEFT() {
        return BUMPER_LEFT;
    }
}
