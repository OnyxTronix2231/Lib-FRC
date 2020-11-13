package pid;

import pid.interfaces.Controller;

public abstract class AbstractController implements Controller {
  protected PIDFTerms pidfTerms;
  protected double setpoint;

  public AbstractController(double kP, double kI, double kD, double kF) {
    this.pidfTerms = new PIDFTerms(kP, kI, kD, kF);
  }

  public PIDFTerms getPIDFTerms() {
    return this.pidfTerms;
  }

  public void setPIDFTerms(double kP, double kI, double kD, double kF) {
    this.pidfTerms.setP(kP);
    this.pidfTerms.setI(kI);
    this.pidfTerms.setD(kD);
    this.pidfTerms.setF(kF);
    this.setpoint = 0;
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
