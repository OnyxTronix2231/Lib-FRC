package pid;

import pid.interfaces.Controller;

public abstract class AbstractController implements Controller {
  protected PIDFTerms pidfTerms;
  protected double setpoint;

  public AbstractController(double kP, double kI, double kD, double kF) {
    this.pidfTerms = new PIDFTerms(kP, kI, kD, kF);
    this.setpoint = 0;
  }

  public AbstractController(PIDFTerms pidfTerms) {
    this.pidfTerms = pidfTerms;
    this.setpoint = 0;
  }

  public AbstractController() {
    this.pidfTerms = new PIDFTerms(0, 0, 0, 0);
    this.setpoint = 0;
  }

  public PIDFTerms getPIDFTerms() {
    return this.pidfTerms;
  }

  @Override
  public void setPIDFTerms(PIDFTerms pidfTerms) {
    this.pidfTerms = pidfTerms;
  }

  public void setPIDFTerms(double kP, double kI, double kD, double kF) {
    this.pidfTerms.setP(kP);
    this.pidfTerms.setI(kI);
    this.pidfTerms.setD(kD);
    this.pidfTerms.setF(kF);
  }

  @Override
  public double getSetpoint() {
    return this.setpoint;
  }

  @Override
  public void setSetpoint(double setpoint) {
    this.setpoint = setpoint;
  }
}
