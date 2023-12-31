package logging;

public class LogManager {

    public static boolean isLoggingEnabled;

    public static void enableLogging(boolean enable) {
        isLoggingEnabled = enable;
    }

    public static boolean isLoggingEnabled() {
        return isLoggingEnabled;
    }
}
