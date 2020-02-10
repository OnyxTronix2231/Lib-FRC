package vision.configuration;


public class VisionConfiguration {
	private final CameraConfiguration cameraConfiguration;
	private final TargetConfiguration targetConfiguration;
	
	public VisionConfiguration(CameraConfiguration cameraConfiguration,
							   TargetConfiguration targetConfiguration) {
		this.cameraConfiguration = cameraConfiguration;
		this.targetConfiguration = targetConfiguration;
	}

	public CameraConfiguration getCameraConfiguration() {
		return cameraConfiguration;
	}

	public TargetConfiguration getTargetConfiguration() {
		return targetConfiguration;
	}
}
