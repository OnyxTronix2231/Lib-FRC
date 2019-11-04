package vision.grip.Calculations;

import vision.OnyxPipeline;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import vision.grip.GripConfiguration;

public class CalculateRectangularCenterY implements CalculateCenterY {

    @Override
    public double getCenterY(OnyxPipeline pipeline) {
        Rect r =  Imgproc.boundingRect(pipeline.convexHullsOutput().get(0));
        return r.y + r.height / 2.0;
    }
}
