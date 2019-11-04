package vision.grip.Calculations;

import vision.OnyxPipeline;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import vision.grip.GripConfiguration;

public class CalculateRectangularCenterX implements CalculateCenterX {

    @Override
    public double getCenterX(OnyxPipeline pipeline) {
        Rect r =  Imgproc.boundingRect(pipeline.convexHullsOutput().get(0));
        return r.x + r.width / 2.0;
    }
}
