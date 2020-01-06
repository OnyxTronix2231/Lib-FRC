package vision;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import vision.grip.VisionSensorGrip;

public abstract class VisionSubsystem extends SubsystemBase {

    protected final VisionSensorGrip m_visionSensor;

    public VisionSubsystem(final VisionSensorGrip visionSensor) {
        m_visionSensor = visionSensor;
    }

    public void updateVision() {
        m_visionSensor.update();
    }

    public boolean isVisionRunning() {
        return m_visionSensor.isRunning();
    }

    public boolean isVisionUpdated() {
        return m_visionSensor.isUpdated();
    }
}
