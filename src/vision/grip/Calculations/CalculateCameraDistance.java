package vision.grip.Calculations;

import vision.OnyxPipeline;
import vision.grip.GripConfiguration;

@FunctionalInterface
public interface CalculateCameraDistance {

    double getCameraDistance(OnyxPipeline pipeline);
}
