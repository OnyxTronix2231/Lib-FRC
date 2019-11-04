package vision.grip.Calculations;

import vision.OnyxPipeline;
import vision.grip.GripConfiguration;

public class CalculateNumberOfParticlesConvexHulls implements CalculateNumberOfParticles {

	@Override
	public int getNumberOfParticles(OnyxPipeline pipeline) {
		return pipeline.convexHullsOutput().size();
	}
}
