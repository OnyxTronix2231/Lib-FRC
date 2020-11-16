package pid.interfaces;

public interface PIDController extends Controller {
  double getProcessVariable();

  double getCurrentError();
}
