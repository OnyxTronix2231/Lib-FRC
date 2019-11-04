package vision.grip.Calculations;

import vision.OnyxPipeline;
import vision.grip.GripConfiguration;

@FunctionalInterface
public interface CalculateCenterX {

    double getCenterX(OnyxPipeline pipeline);
}
