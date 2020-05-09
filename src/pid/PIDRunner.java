package pid;

public interface PIDRunner {
  void startPIDLoop();

  void stopPIDLoop(int remainOrStop);
}
