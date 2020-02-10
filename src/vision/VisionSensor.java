package vision;

import vision.configuration.VisionConfiguration;

public abstract class VisionSensor <T extends VisionConfiguration>{
	protected T visionConfiguration;

	public VisionSensor(T visionConfiguration) {
		this.visionConfiguration = visionConfiguration;
	}
	
	public void setConfiguration(T visionConfiguration) {
		this.visionConfiguration = visionConfiguration;		
	}
}
