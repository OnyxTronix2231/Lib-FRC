package pid;

public interface PIDRunner {
  void startPIDLoop();

  void stopPIDLoop(double speedAfterStop);
}
