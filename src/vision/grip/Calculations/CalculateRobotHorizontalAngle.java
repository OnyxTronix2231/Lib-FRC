package vision.grip.Calculations;

import vision.OnyxPipeline;
import vision.grip.GripConfiguration;

@FunctionalInterface
public interface CalculateRobotHorizontalAngle {

    double getRobotHorizontalAngle(OnyxPipeline pipeline);
}
