package onyxTronix;

import edu.wpi.first.wpilibj2.command.CommandBase;

public abstract class SetPointCommand extends CommandBase {
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
