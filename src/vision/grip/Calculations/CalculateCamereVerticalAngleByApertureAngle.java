package vision.grip.Calculations;

import vision.OnyxPipeline;
import vision.grip.GripConfiguration;


public class CalculateCamereVerticalAngleByApertureAngle implements CalculateCameraVerticalAngle {
    private GripConfiguration<OnyxPipeline> visionConfiguration;
    private CalculateCenterY calculateCenterY;

    public CalculateCamereVerticalAngleByApertureAngle(
            final GripConfiguration<OnyxPipeline> visionConfiguration,
            CalculateCenterY calculateCenterY) {
        this.visionConfiguration = visionConfiguration;
        this.calculateCenterY = calculateCenterY;
    }


    @Override
    public double getCameraVerticalAngle(OnyxPipeline pipeline) {
        double imageHeight = pipeline.hslThresholdOutput().height();
        //The -.5 compensates for the fact that if there are an even number of columns/rows in your image,
        // the center is actually on the border between two of them
        // (and we start counting rows/cols from 0 typically).
        double screenCenterX = imageHeight / 2.0 - .5f;
        double focal_length_pixels = (.5 * imageHeight) / Math.tan((Math.toRadians(visionConfiguration.getCameraConfiguration().getVerticalApertureAngle())) / 2);
        return Math.toDegrees(Math.atan((calculateCenterY.getCenterY(pipeline) - screenCenterX) / focal_length_pixels)) + visionConfiguration.getCameraConfiguration().getVerticalMountAngle();
    }
}
