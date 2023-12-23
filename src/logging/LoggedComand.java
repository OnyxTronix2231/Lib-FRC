package logging;

import edu.wpi.first.wpilibj2.command.Command;

public class LoggedComand extends Command {

    public static boolean IS_LOG = false;

    private String commandName = getClass().toString();
    private String requirements = getRequirements().toString();

    @Override
    public void initialize() {
        if (IS_LOG) {
            System.out.println("[" + System.currentTimeMillis() + "] - (" + requirements + ") " + commandName);
            log();
        }

    }

    @Override
    public void end(boolean interrupted) {
        if (IS_LOG) {
            System.out
                    .println("[" + System.currentTimeMillis() + "] - (" + requirements + ") " + commandName + " - end");
            log();
        }
    }

    public void log() {
    }
}
