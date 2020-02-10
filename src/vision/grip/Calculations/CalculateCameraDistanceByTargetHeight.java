package vision.grip.Calculations;

import vision.OnyxPipeline;
import vision.configuration.VisionConfiguration;
import vision.grip.GripConfiguration;

public class CalculateCameraDistanceByTargetHeight implements CalculateCameraDistance {

	private GripConfiguration<OnyxPipeline> visionConfiguration;
	private CalculateCameraVerticalAngle calculateCameraVerticalAngle;

	public CalculateCameraDistanceByTargetHeight(
			final GripConfiguration<OnyxPipeline> visionConfiguration,
			CalculateCameraVerticalAngle calculateCameraVerticalAngle) {
		this.visionConfiguration = visionConfiguration;
		this.calculateCameraVerticalAngle = calculateCameraVerticalAngle;
	}

	@Override
	public double getCameraDistance(OnyxPipeline pipeline) {
		double cameraVerticalAngle =  calculateCameraVerticalAngle.getCameraVerticalAngle(pipeline);
		return getCameraDistanceByAngle(cameraVerticalAngle,
				visionConfiguration.getTargetConfiguration().getTargetHeight(),
				visionConfiguration.getCameraConfiguration().getCameraHeight());
	}

	private double getCameraDistanceByAngle(double cameraVerticalAngle, double targetHeight, double cameraHeight)
	{
		return (Math.abs(targetHeight - cameraHeight)) / Math.tan(Math.toRadians(cameraVerticalAngle));
	}


}
