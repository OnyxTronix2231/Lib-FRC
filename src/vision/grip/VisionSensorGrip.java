package vision.grip;

import edu.wpi.first.cscore.VideoSource;
import edu.wpi.first.vision.VisionRunner;
import vision.*;
import vision.grip.Calculations.*;

import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;

public class VisionSensorGrip extends VisionSensor<GripConfiguration<OnyxPipeline>> {
	public static final int DEFAULT_SENSOR_VALUE = -999999;
	private final VideoSource video;
	private final CalculateCenterX calculateCenterX;
	private final CalculateCenterY calculateCenterY;
	private final CalculateCameraDistance calculateCameraDistance;
	private final CalculateRobotHorizontalAngle calculateRobotHorizontalAngle;
	private final CalculateNumberOfParticles calculateNumberOfParticles;
	private final ThreadFactory threadFactory;
	private final VisionThreadFactory visionThreadFactory;
    private final OnyxListener listener;
    private int minUpdateTime;
	private boolean isRunning;
    private long lastUpdateTime;
    private Thread visionThread;
    private OnyxPipeline output;
    private VisionRunner<OnyxPipeline> runner;

	public VisionSensorGrip(VideoSource video, GripConfiguration<OnyxPipeline> visionConfiguration, CalculateCenterX calculateCenterX,
							CalculateCenterY calculateCenterY, CalculateCameraDistance calculateCameraDistance,
							CalculateRobotHorizontalAngle calculateRobotHorizontalAngle, CalculateNumberOfParticles calculateNumberOfParticles, ThreadFactory threadFactory,
                            VisionThreadFactory visionThreadFactory, ListenerFactory listenerFactory, int minUpdateTime) {
		super(visionConfiguration);
		this.video = video;
		this.calculateCenterX = calculateCenterX;
		this.calculateCenterY = calculateCenterY;
		this.calculateCameraDistance = calculateCameraDistance;
		this.calculateRobotHorizontalAngle = calculateRobotHorizontalAngle;
		this.calculateNumberOfParticles = calculateNumberOfParticles;
		this.threadFactory = threadFactory;
		this.visionThreadFactory = visionThreadFactory;
		this.listener = listenerFactory.newListener(this);
        this.minUpdateTime = minUpdateTime;
        isRunning  = false;
        lastUpdateTime = 0;
		createVisionRunner();
	}

	public void updatePipeLine(OnyxPipeline pipeline) {
		output = pipeline;
		lastUpdateTime = System.nanoTime();
	}

	public double getCenterX() {
		return getValueFromParticles(() -> calculateCenterX.getCenterX(output)).doubleValue();
	}

	public double getCenterY() {
		return getValueFromParticles(() -> calculateCenterY.getCenterY(output)).doubleValue();
	}

	public double getCameraDistance() {
		return getValueFromParticles(() -> calculateCameraDistance.getCameraDistance(output)).doubleValue();
	}

	public double getRobotAngle() {
		return getValueFromParticles(() -> calculateRobotHorizontalAngle.getRobotHorizontalAngle(output)).doubleValue();
	}

	public int getNumberOfParticles() {
		return calculateNumberOfParticles.getNumberOfParticles(output);
	}

	private Number getValueFromParticles(Supplier<Number> getVisionCalculation){
		if(getNumberOfParticles() > 0) {
			return getVisionCalculation.get().doubleValue();
		}
		return DEFAULT_SENSOR_VALUE;
	}

	@Override
	public void setConfiguration(GripConfiguration<OnyxPipeline> visionConfiguration) {
		super.setConfiguration(visionConfiguration);
	    createVisionRunner();
    }

	private void createVisionRunner() {
		runner = new VisionRunner<>(video, visionConfiguration.getPipeline(), listener);
	}

	public void startVisionThread() {
		if (visionThread != null) {
			visionThread.interrupt();
		}

		visionThread = visionThreadFactory.newThread(runner);
		visionThread.start();
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void update() {
		isRunning = true;
		runOnceOnNewThread();
	}

	public boolean isUpdated() {
		return minUpdateTime >= System.nanoTime() / 1000000 - lastUpdateTime;
	}

	private synchronized void runOnceOnNewThread() {
		threadFactory.newThread(this::runOnce).start();
	}

	private void runOnce() {
		runner.runOnce();
		isRunning = false;
	}
}
