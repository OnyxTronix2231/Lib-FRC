package onyxTronix;

import edu.wpi.first.wpilibj.command.Command;

public abstract class SetPointCommand extends Command {
	protected double setPoint;
	protected boolean isFinished = false;

	public SetPointCommand(double setPoint) {
		this.setPoint = setPoint;
	}
	
	public void setSetPoint(double setPoint) {
		isFinished = false;
		this.setPoint = setPoint;
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}
}
