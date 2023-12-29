package logging;

import edu.wpi.first.wpilibj2.command.Command;

public class LoggedCommand extends Command {

    public static boolean IS_LOG = false;

    private String name = getClass().toString();
    private String requirements = getRequirements().toString();

    @Override
    public void initialize() {
        if (IS_LOG) {
            System.out.println("[" + System.currentTimeMillis() + "] - (" + requirements + ") " + name);
            log();
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (IS_LOG) {
            System.out.println("[" + System.currentTimeMillis() + "] - (" + requirements + ") " + name + " - end");
            log();
        }
    }

    public void log() {
    }
}
