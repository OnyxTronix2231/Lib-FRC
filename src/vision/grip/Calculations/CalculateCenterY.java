package vision.grip.Calculations;

import vision.OnyxPipeline;
import vision.grip.GripConfiguration;

@FunctionalInterface
public interface CalculateCenterY {

    double getCenterY(OnyxPipeline pipeline);
}
