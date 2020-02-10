package vision.configuration;

import vision.grip.Calculations.CalculateHorizontalMountAngle;
import vision.grip.Calculations.CalculateVerticalMountAngle;

import java.util.function.Supplier;

public class CameraConfiguration {
	private CalculateVerticalMountAngle calculateVerticalMountAngle; //Down is > 0
	private CalculateHorizontalMountAngle calculateHorizontalMountAngle; //Right is > 0
	private double cameraHeight; // The height of the camera(m) from the floor
	private double verticalApertureAngle;
	private double horizontalApertureAngle;
	//Camera offset from rotation center of the robot
	private double cameraXOffset; //Right > 0
	private double cameraZOffset; //Forward > 0

	public CameraConfiguration(CalculateVerticalMountAngle calculateVerticalMountAngle, CalculateHorizontalMountAngle calculateHorizontalMountAngle, double cameraHeight,
							   double verticalApertureAngle, double horizontalApertureAngle, double cameraXOffset,
							   double cameraZOffset) {
		this.calculateVerticalMountAngle = calculateVerticalMountAngle;
		this.calculateHorizontalMountAngle = calculateHorizontalMountAngle;
		this.cameraHeight = cameraHeight;
		this.verticalApertureAngle = verticalApertureAngle;
		this.horizontalApertureAngle = horizontalApertureAngle;
		this.cameraXOffset = cameraXOffset;
		this.cameraZOffset = cameraZOffset;
	}
	
	public double getVerticalMountAngle() {
		return calculateVerticalMountAngle.getVerticalMountAngle();
	}

	public double getCameraHeight() {
		return cameraHeight;
	}
	
	public void setCameraHeight(double cameraHeight) {
		this.cameraHeight = cameraHeight;
	}
	
	public double getVerticalApertureAngle() {
		return verticalApertureAngle;
	}

	public void setVerticalApertureAngle(double verticalApertureAngle) {
		this.verticalApertureAngle = verticalApertureAngle;
	}

	public double getHorizontalApertureAngle() {
		return horizontalApertureAngle;
	}

	public void setHorizontalApertureAngle(double horizontalApertureAngle) {
		this.horizontalApertureAngle = horizontalApertureAngle;
	}

	public double getHorizontalMountAngle() {
		return calculateHorizontalMountAngle.getHorizontalMountAngle();
	}

	public double getCameraXOffset() {
		return cameraXOffset;
	}

	public void setCameraXOffset(double cameraXOffset) {
		this.cameraXOffset = cameraXOffset;
	}

	public double getCameraZOffset() {
		return cameraZOffset;
	}

	public void setCameraZOffset(double cameraZOffset) {
		this.cameraZOffset = cameraZOffset;
	}
}
