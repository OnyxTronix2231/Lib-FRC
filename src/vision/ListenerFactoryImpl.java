package vision;

import vision.grip.VisionSensorGrip;

public class ListenerFactoryImpl implements ListenerFactory {

    @Override
    public OnyxListener newListener(VisionSensorGrip visionSensor){
        return new OnyxListener(visionSensor);
    }
}
