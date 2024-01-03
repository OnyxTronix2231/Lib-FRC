package logging;

public class LogManager {

    private boolean isLoggingEnabled;

    private LogManager() {
        isLoggingEnabled = false;
    }

    public void enableLogging() {
        isLoggingEnabled = true;
    }

    public void disableLogging() {
        isLoggingEnabled = false;
    }

    public boolean isLoggingEnabled() {
        return isLoggingEnabled;
    }

    private static LogManager instance;

    public static LogManager getInstance() {
        if (instance == null)
            instance = new LogManager();
        return instance;
    }
}
