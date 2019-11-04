package vision;

import edu.wpi.first.vision.VisionRunner.Listener;
import vision.grip.VisionSensorGrip;

public class OnyxListener implements Listener<OnyxPipeline> {
	private final VisionSensorGrip visionSenor;

	public OnyxListener(VisionSensorGrip visionSenor) {
		this.visionSenor = visionSenor;
	}

	@Override
	public synchronized void copyPipelineOutputs(OnyxPipeline pipeline) {
		if (pipeline.filterContoursOutput().isEmpty()) {
			System.out.println("WARNING: No particles!");
		}
		visionSenor.updatePipeLine(pipeline);
	}

}
