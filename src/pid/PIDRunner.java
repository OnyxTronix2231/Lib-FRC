package pid;

public interface PIDRunner {
  void startPIDLoop();

  void terminatePIDLoop(int remainOrStop);
}
