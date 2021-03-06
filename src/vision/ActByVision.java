package vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import onyxTronix.SetPointCommand;

import vision.VisionSubsystem;
import vision.grip.VisionSensorGrip;

import java.util.function.Supplier;

/**
 *
 */
public class ActByVision extends CommandBase {
    protected VisionSubsystem visionSubsystem;
    private Supplier<Number> getVisionCalculationResult;
    private Supplier<Number> getTolerance;
    protected SetPointCommand setPointCommand;
    protected boolean isFinished = false;
    protected boolean hasRunOnce = false;
    protected double setpoint;
    protected double error;
    /**
     * Determines if the vision act should check the vision sensor
     * value each time the {@link SetPointCommand} finished.
     */
    protected boolean isContinues;

    public ActByVision(VisionSubsystem visionSubsystem, double setpoint, Supplier<Number> getVisionCalculationResult, SetPointCommand setPointCommand, Supplier<Number> getTolerance, boolean isContinuous) {
    	this.visionSubsystem = visionSubsystem;
        this.getVisionCalculationResult = getVisionCalculationResult;
        this.setpoint = setpoint;
        this.setPointCommand = setPointCommand;
        this.getTolerance = getTolerance;
        this.isContinues = isContinuous;
        addRequirements(visionSubsystem);
    }
 
    @Override
    public void initialize() {
    	isFinished = false;
        hasRunOnce = false;
    }
    
    @Override
    public void execute() {
        if (visionSubsystem.isVisionRunning() || !setPointCommand.isFinished()) {
            return;
        }

        if (!visionSubsystem.isVisionUpdated()) {
            System.out.println("Processing image...");
            visionSubsystem.updateVision();
        } else {
            error = setpoint - getVisionCalculationResult.get().doubleValue();
            if(error == VisionSensorGrip.DEFAULT_SENSOR_VALUE){
                isFinished = true;
                return;
            }
            if ((hasRunOnce && !isContinues) || isOnTarget(error)) { //vision calc is not accurate,keep checking until it's on target
                isFinished = true;
                System.out.println("Vision on target");
            } else {
                setPointCommand.setSetPoint(error);
                setPointCommand.schedule();
                hasRunOnce = true;
            }
        }
    }

    @Override
    public boolean isFinished() {
		return isFinished;
	}

  @Override
  public void end(boolean interrupted) {
    setPointCommand.cancel();
  }

	protected boolean isOnTarget(double error){
        return Math.abs(error) <= getTolerance.get().doubleValue();
    }
}
