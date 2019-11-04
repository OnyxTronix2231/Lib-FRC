package onyxTronix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;

public class OnyxTronixPIDController extends PIDController implements PIDOutput{
	protected Field m_prevError;
	protected Field m_totalError;
	protected Field m_F;
	protected double tolerance;
	protected Timer timer;
	protected AtomicInteger runCount;
	protected boolean isWriting = false;
	protected double f;

	public OnyxTronixPIDController(double Kp, double Ki, double Kd, double Kf,
			PIDSource source, PIDOutput output, double tolerance) {
		super(Kp, Ki, Kd, Kf, source, output);
		timer = new Timer();
		runCount = new AtomicInteger();
		this.tolerance = tolerance;
		this.f = 0;
		try {
			Class<?> controllerClass = this.getClass().getSuperclass();
			m_prevError = controllerClass.getDeclaredField("m_prevError");
			m_prevError.setAccessible(true);
			m_totalError = controllerClass.getDeclaredField("m_totalError");
			m_totalError.setAccessible(true);
			m_F = controllerClass.getDeclaredField("m_F");
			m_F.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

	double prevError = 0;
	double totalError = 0;

	@Override
	public boolean onTarget() {
		try {
			prevError = this.m_prevError.getDouble(this);
			totalError = this.m_totalError.getDouble(this);
			if (this.m_pidInput.getPIDSourceType() == PIDSourceType.kDisplacement && getError() * totalError <= 0) {
				m_totalError.setDouble(this, 0);
			}
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		if (isWriting) {
			String s = runCount.get() + "\t" + timer.get() + "\t" + getError()
					+ "\t" + totalError + "\t" + (getError() - prevError)
					+ "\t" + this.getP() * getError() + "\t" + this.getI()
					* totalError + "\t" + this.getD()
					* (getError() - prevError) + "\n";
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(
					"/home/lvuser/pidCurve.txt", true))) {
				writer.append(s);
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			return Math.abs(getError() - prevError) < tolerance / 5
					&& Math.abs(getError()) < tolerance;
		} catch (IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void enableWriting() {
		timer.reset();
		timer.start();
		runCount.incrementAndGet();
		isWriting = true;
	}

	public void disableWriting() {
		timer.stop();
		isWriting = false;
	}

	@Override
	protected double calculateFeedForward() {
		if (m_pidInput.getPIDSourceType().equals(PIDSourceType.kDisplacement) && onTarget()) {
			return 0;
		}
		
		try {
			double f = m_F.getDouble(this) + this.f;
			if (getError() > 0)
				return f;
			return -f;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean init(double setPoint, double tolerance) {
		this.setSetpoint(setPoint);
		this.setTolerance(tolerance);
//		if (onTarget()) {
//			return false;
//		}
		this.enable();
		return true;
	}

	public void stop() {
		disable();
		reset();
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

	@Override
	public void pidWrite(double output) {
		f = output;
	}
}
