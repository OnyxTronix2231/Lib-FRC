package commandControl;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.utils.GeomUtil;

public class SwerveAxis {
    double leftX;
    double leftY;
    double rightX;

    public SwerveAxis(double leftX, double leftY, double rightX) {
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;
    }

    public Translation2d calculate() {
        // Get direction and magnitude of linear axes
        double linearMagnitude = Math.hypot(leftX, leftY);
        Rotation2d linearDirection = new Rotation2d(leftX, leftY);

        // Apply deadband
        linearMagnitude = MathUtil.applyDeadband(linearMagnitude, 0.05);
        rightX = MathUtil.applyDeadband(rightX, 0.05);

        // Apply squaring
        linearMagnitude = Math.copySign(linearMagnitude * linearMagnitude, linearMagnitude);
        rightX = Math.copySign(rightX * rightX, rightX);

        Translation2d linearVelocity =
                new Pose2d(new Translation2d(), linearDirection)
                        .transformBy(GeomUtil.translationToTransform(linearMagnitude, 0.0))
                        .getTranslation();
        return linearVelocity;
    }
}


