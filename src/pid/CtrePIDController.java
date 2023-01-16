package pid;

import static pid.CtreConstants.CTRE_DEVICE_CALLS_TIMEOUT;
import static pid.CtreConstants.DEFAULT_PID_IDX;
import static pid.CtreConstants.DEFAULT_SLOT_IDX;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import exceptions.UnsupportedControlModeException;
import pid.interfaces.PIDController;
import sensors.counter.CtreEncoder;

public class CtrePIDController extends CtreController implements PIDController {

    protected final PIDControlMode pidControlMode;

    public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder,
                             PIDFTerms pidfTerms, PIDControlMode pidControlMode) {
        this(ctreMotorController, ctreEncoder, pidfTerms, DEFAULT_SLOT_IDX, DEFAULT_PID_IDX,
                CTRE_DEVICE_CALLS_TIMEOUT, pidControlMode);
    }

    public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder,
                             double kP, double kI, double kD, double kF, int slotIdx, int pidIdx, int timeoutMs,
                             PIDControlMode pidControlMode) {
        this(ctreMotorController, ctreEncoder, new PIDFTerms(kP, kI, kD, kF), slotIdx, pidIdx, timeoutMs, pidControlMode);
    }

    public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder,
                             double kP, double kI, double kD, double kF, PIDControlMode pidControlMode) {
        this(ctreMotorController, ctreEncoder, new PIDFTerms(kP, kI, kD, kF), DEFAULT_SLOT_IDX, DEFAULT_PID_IDX,
                CTRE_DEVICE_CALLS_TIMEOUT, pidControlMode);
    }

    public CtrePIDController(IMotorControllerEnhanced ctreMotorController, CtreEncoder ctreEncoder,
                             PIDFTerms pidfTerms, int slotIdx, int pidIdx, int timeoutMs, PIDControlMode pidControlMode) {
        super(ctreMotorController, ctreEncoder, pidfTerms, slotIdx, pidIdx, timeoutMs);
        this.pidControlMode = pidControlMode;
    }

    @Override
    public double getProcessVariable() {
        if (this.ctreMotorController.getControlMode() == ControlMode.Velocity) {
            return this.ctreMotorController.getSelectedSensorVelocity(pidIdx);
        } else if (this.ctreMotorController.getControlMode() == ControlMode.Position) {
            return this.ctreMotorController.getSelectedSensorPosition(pidIdx);
        }
        throw new UnsupportedControlModeException(this.ctreMotorController.getControlMode().name());
    }

    @Override
    public void restartControllerState() {
        this.ctreMotorController.setIntegralAccumulator(0, this.pidIdx, this.timeoutMs);
    }

    @Override
    public void enable() {
        super.enable();
        if (pidControlMode == PIDControlMode.Position) {
            this.ctreMotorController.set(ControlMode.Position, this.setpoint);
        } else {
            this.ctreMotorController.set(ControlMode.Velocity, this.setpoint);
        }
    }

    @Override
    public void enable(double feedForward) {
        super.enable(feedForward);
        if (pidControlMode == PIDControlMode.Position) {
            this.ctreMotorController.set(ControlMode.Position, this.setpoint, DemandType.ArbitraryFeedForward, feedForward);
        } else {
            this.ctreMotorController.set(ControlMode.Velocity, this.setpoint, DemandType.ArbitraryFeedForward, feedForward);
        }
    }

    @Override
    public void update(double setpoint) {
        this.setSetpoint(setpoint);
        if (pidControlMode == PIDControlMode.Position) {
            this.ctreMotorController.set(ControlMode.Position, this.setpoint);
        } else {
            this.ctreMotorController.set(ControlMode.Velocity, this.setpoint);
        }
    }

    @Override
    public void setIntegralZone(double iZone) {
        this.ctreMotorController.config_IntegralZone(this.slotIdx,iZone,this.timeoutMs);
    }

    @Override
    public void update(double setpoint, double feedForward) {
        this.setSetpoint(setpoint);
        if (pidControlMode == PIDControlMode.Position) {
            this.ctreMotorController.set(ControlMode.Position, this.setpoint, DemandType.ArbitraryFeedForward, feedForward);
        } else {
            this.ctreMotorController.set(ControlMode.Velocity, this.setpoint, DemandType.ArbitraryFeedForward, feedForward);
        }
    }

    @Override
    public boolean isOnTarget(double tolerance) {
        if (pidControlMode == PIDControlMode.Velocity) {
            if (isFirstRun()) {
                return false;
            }
        } else {
            if (isFirstRun()) {
                return Math.abs(firstError) < tolerance;
            }
            firstError = Integer.MIN_VALUE;
        }
        return super.isOnTarget(tolerance);
    }

    @Override
    public boolean isOnTarget(double belowTolerance, double aboveTolerance) {
        if (pidControlMode == PIDControlMode.Velocity) {
            if (isFirstRun()) {
                return false;
            }
        } else {
            if (isFirstRun()) {
                return firstError > belowTolerance && firstError < aboveTolerance;
            }
            firstError = Integer.MIN_VALUE;
        }
        return super.isOnTarget(belowTolerance, aboveTolerance);
    }

    @Override
    public double getCurrentError() {
        return this.ctreMotorController.getClosedLoopError(pidIdx);
    }


}
