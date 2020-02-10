package vision;

import edu.wpi.first.vision.VisionRunner;

public interface VisionThreadFactory {
    Thread newThread(VisionRunner<?> visionRunner);
}
