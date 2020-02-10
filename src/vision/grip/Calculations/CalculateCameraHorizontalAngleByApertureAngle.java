package vision.grip.Calculations;

import vision.OnyxPipeline;
import vision.grip.GripConfiguration;


public class CalculateCameraHorizontalAngleByApertureAngle implements CalculateCameraHorizontalAngle {
    private GripConfiguration<OnyxPipeline> visionConfiguration;
    private CalculateCenterX calculateCenterX;

    public CalculateCameraHorizontalAngleByApertureAngle(
            final GripConfiguration<OnyxPipeline> visionConfiguration,
            CalculateCenterX calculateCenterX) {
        this.visionConfiguration = visionConfiguration;
        this.calculateCenterX = calculateCenterX;
    }


    @Override
    public double getCameraHorizontalAngle(OnyxPipeline pipeline) {
        double imageWidth = pipeline.hslThresholdOutput().width();
        //The -.5 compensates for the fact that if there are an even number of columns/rows in your image,
        // the center is actually on the border between two of them
        // (and we start counting rows/cols from 0 typically).
        double screenCenterX = imageWidth / 2.0 - .5f;
        double focal_length_pixels = (.5 * imageWidth) / Math.tan((Math.toRadians(visionConfiguration.getCameraConfiguration().getHorizontalApertureAngle())) / 2);
        return Math.toDegrees(Math.atan((calculateCenterX.getCenterX(pipeline) - screenCenterX) / focal_length_pixels)) + visionConfiguration.getCameraConfiguration().getHorizontalMountAngle();
    }
}
