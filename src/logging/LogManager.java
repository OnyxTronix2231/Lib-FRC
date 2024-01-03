package logging;

public class LogManager {

    private boolean loggingEnabled;

    private LogManager() {
        loggingEnabled = false;
    }

    public void setLoggingEnabled(boolean enable) {
        loggingEnabled = enable;
    }

    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    private static LogManager instance;

    public static LogManager getInstance() {
        if (instance == null) {
            instance = new LogManager();
        }
        return instance;
    }
}
