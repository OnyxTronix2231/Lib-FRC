package vision;

import edu.wpi.first.vision.VisionRunner;

import java.util.concurrent.ThreadFactory;

public class VisionThreadFactoryImpl implements VisionThreadFactory {

    private final ThreadFactory threadFactory;

    public VisionThreadFactoryImpl(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    @Override
    public Thread newThread(VisionRunner<?> visionRunner) {
        Thread thread = threadFactory.newThread(visionRunner::runForever);
        thread.setDaemon(true);
        return thread;
    }
}
