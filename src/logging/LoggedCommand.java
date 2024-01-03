package logging;

import edu.wpi.first.wpilibj2.command.Command;

public class LoggedCommand extends Command {

    private String name = getClass().toString();
    private String requirements = getRequirements().toString();

    private boolean isStart = true;

    private void printLog() {
        if (LogManager.getInstance().isLoggingEnabled()) {
            if (isStart) {
                System.out.println("[" + System.currentTimeMillis() + "] - (" + requirements + ") " + name);
                isStart = false;
            } else {
                System.out.println("[" + System.currentTimeMillis() + "] - (" + requirements + ") " + name + " - end");
                isStart = true;
            }
            log();
        }
    }

    @Override
    public void initialize() {
        printLog();
    }

    @Override
    public void end(boolean interrupted) {
        printLog();
    }

    public void log() {
    }
}
