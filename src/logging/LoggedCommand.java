package logging;

import edu.wpi.first.wpilibj2.command.Command;
import static logging.LogManager.isLoggingEnabled;

public class LoggedCommand extends Command {

    private String name = getClass().toString();
    private String requirements = getRequirements().toString();

    @Override
    public void initialize() {
        if (isLoggingEnabled) {
            System.out.println("[" + System.currentTimeMillis() + "] - (" + requirements + ") " + name);
            log();
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (isLoggingEnabled) {
            System.out.println("[" + System.currentTimeMillis() + "] - (" + requirements + ") " + name + " - end");
            log();
        }
    }

    public void log() {
    }
}
