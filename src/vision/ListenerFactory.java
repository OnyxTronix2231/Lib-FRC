package vision;

import vision.grip.VisionSensorGrip;

public interface ListenerFactory {
    OnyxListener newListener(VisionSensorGrip visionSensor);
}
