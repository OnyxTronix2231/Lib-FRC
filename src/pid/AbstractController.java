package pid;

import pid.interfaces.Controller;

public abstract class AbstractController implements Controller {

    protected PIDFTerms pidfTerms;
    protected double setpoint;

    public AbstractController(double kP, double kI, double kD, double kF) {
        this(new PIDFTerms(kP, kI, kD, kF));
    }

    public AbstractController(PIDFTerms pidfTerms) {
        this.pidfTerms = pidfTerms;
        this.setpoint = 0;
    }

    public PIDFTerms getPIDFTerms() {
        return this.pidfTerms;
    }

    public void setPIDFTerms(double kP, double kI, double kD, double kF) {
        this.setPIDFTerms(new PIDFTerms(kP, kI, kD, kF));
    }

    public void setPIDFTerms(PIDFTerms pidfTerms) {
        this.pidfTerms = pidfTerms;
    }

    public abstract double getProcessVariable();

    @Override
    public double getSetpoint() {
        return this.setpoint;
    }

    @Override
    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    @Override
    public boolean isOnTarget(double tolerance) {
        return Math.abs(this.getCurrentError()) < tolerance;
    }

    @Override
    public boolean isOnTarget(double belowTolerance, double aboveTolerance) {
        return this.getCurrentError() > belowTolerance && this.getCurrentError() < aboveTolerance;
    }
}
