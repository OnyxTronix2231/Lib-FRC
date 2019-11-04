package vision.grip.Calculations;

import vision.OnyxPipeline;
import vision.configuration.VisionConfiguration;
import vision.grip.GripConfiguration;

public class CalculateRobotHorizontalAngleByDistance implements CalculateRobotHorizontalAngle {

    private GripConfiguration<OnyxPipeline> gripConfiguration;
    private CalculateCameraDistanceByTargetHeight cameraDistanceCalculation;
    private CalculateCameraHorizontalAngle calculateCameraHorizontalAngle;

    public CalculateRobotHorizontalAngleByDistance(GripConfiguration<OnyxPipeline> gripConfiguration,
                                                   CalculateCameraDistanceByTargetHeight cameraDistanceCalculation,
                                                   CalculateCameraHorizontalAngle calculateCameraHorizontalAngle) {
        this.gripConfiguration = gripConfiguration;
        this.cameraDistanceCalculation = cameraDistanceCalculation;
        this.calculateCameraHorizontalAngle = calculateCameraHorizontalAngle;
    }

    @Override
    public double getRobotHorizontalAngle(OnyxPipeline pipeline) {
        double horizontalCameraAngle = calculateCameraHorizontalAngle.getCameraHorizontalAngle(pipeline);
        double cameraDistance = cameraDistanceCalculation.getCameraDistance(pipeline);
        return getRobotAngleByCameraAngle(horizontalCameraAngle, cameraDistance,
                gripConfiguration.getCameraConfiguration().getCameraXOffset(),
                gripConfiguration.getCameraConfiguration().getCameraZOffset());
    }

    private double getRobotAngleByCameraAngle(double cameraAngle, double cameraDistance, double xOffset,
                                                double zOffset) {
        cameraAngle = Math.toRadians(cameraAngle);
        return Math.toDegrees((cameraDistance * Math.sin(cameraAngle) + xOffset)
                            / (cameraDistance * Math.cos(cameraAngle) + zOffset));
    }


}
