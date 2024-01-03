package logging;

import edu.wpi.first.wpilibj2.command.Command;

public class LoggedCommand extends Command {

    private String name = getClass().toString();
    private String requirements = getRequirements().toString();

    @Override
    public void initialize() {
        if (LogManager.getInstance().isLoggingEnabled()) {
            System.out.println("[" + System.currentTimeMillis() + "] - (" + requirements + ") " + name);
            log();
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (LogManager.getInstance().isLoggingEnabled()) {
            System.out.println("[" + System.currentTimeMillis() + "] - (" + requirements + ") " + name + " - end");
            log();
        }
    }

    public void log() {
    }
}
