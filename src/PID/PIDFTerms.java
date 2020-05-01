package PID;

public class PIDFTerms {
  private double kP;
  private double kI;
  private double kD;
  private double kF;

  public PIDFTerms(double kP, double kI, double kD, double kF) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
    this.kF = kF;
  }

  public double getKp() {
    return kP;
  }

  public double getKi() {
    return kI;
  }

  public double getKd() {
    return kD;
  }

  public double getKf() {
    return kF;
  }

  public void setP(double kP){
    this.kP = kP;
  }

  public void setI(double kI){
    this.kI = kI;
  }

  public void setD(double kD){
    this.kD = kD;
  }

  public void setF(double kF){
    this.kF = kF;
  }
}
