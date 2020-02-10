package vision;

import edu.wpi.first.vision.VisionPipeline;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

import java.util.ArrayList;

public interface OnyxPipeline extends VisionPipeline {

	ArrayList<MatOfPoint> filterContoursOutput();

	ArrayList<MatOfPoint> convexHullsOutput();

	Mat hslThresholdOutput();
}
