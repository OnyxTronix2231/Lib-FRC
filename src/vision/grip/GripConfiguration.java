package vision.grip;

import vision.OnyxPipeline;
import vision.configuration.CameraConfiguration;
import vision.configuration.TargetConfiguration;
import vision.configuration.VisionConfiguration;

public class GripConfiguration <P extends OnyxPipeline> extends VisionConfiguration{
	protected final P pipeline;
	
	public GripConfiguration(CameraConfiguration cameraConfiguration,
			TargetConfiguration targetConfiguration, P pipeline) {
		super(cameraConfiguration, targetConfiguration);
		this.pipeline = pipeline;
	}

	public P getPipeline() {
		return pipeline;
	}
}
