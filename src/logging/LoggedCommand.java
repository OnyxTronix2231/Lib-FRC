package logging;

import edu.wpi.first.wpilibj2.command.Command;

public class LoggedCommand extends Command {

    private String name = getClass().toString();
    private String requirements = getRequirements().toString();

    private void printLog(String state) {
        if (LogManager.getInstance().isLoggingEnabled()) {
            System.out.println("[" + System.currentTimeMillis() + "] - (" + requirements + ") " + name + " --" + state);
        }
        log();
    }

    @Override
    public void initialize() {
        printLog("start");
    }

    @Override
    public void end(boolean interrupted) {
        printLog("end");
    }

    public void log() {
    }
}
