package vision.configuration;

public class TargetConfiguration {
	private double targetHeight; // The height of the target center(m) from the floor

	public TargetConfiguration(double targetHeight) {
		this.targetHeight = targetHeight;
	}

	public double getTargetHeight() {
		return targetHeight;
	}

	public void setTargetHeight(double targetHeight) {
		this.targetHeight = targetHeight;
	}

}
