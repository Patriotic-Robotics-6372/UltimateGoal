package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Drivetrain;

@TeleOp (name = "DrivetrainStrafeTest", group = "Test")
public class DrivetrainStrafeTest extends LinearOpMode {

    public Drivetrain drive;
    public double rightSlide, leftSlide, rightMove, leftMove;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap.dcMotor.get("frontLeft"), hardwareMap.dcMotor.get("frontRight"), hardwareMap.dcMotor.get("backLeft"), hardwareMap.dcMotor.get("backRight"));
        telemetry.addData("How to Use", "Tank Drive + Strafe. Use sticks to drive forwards/backwards, sideways");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            rightSlide = -gamepad1.right_stick_x;
            leftSlide = -gamepad1.left_stick_x;
            rightMove = -gamepad1.right_stick_y;
            leftMove = -gamepad1.left_stick_y;
            drive.setRightSide(rightSlide + rightMove, -rightSlide + rightMove);
            drive.setLeftSide(-leftSlide + leftMove, leftSlide + leftMove);
            telemetry.addData("frontLeft", drive.getFrontLeft().getPower())
                    .addData("frontRight", drive.getFrontRight().getPower())
                    .addData("backLeft", drive.getBackLeft().getPower())
                    .addData("backRight", drive.getBackRight().getPower())
                    .addData("frontLeftEnc", drive.getFrontLeft().getCurrentPosition())
                    .addData("frontRightEnc", drive.getFrontRight().getCurrentPosition())
                    .addData("backLeftEnc", drive.getBackLeft().getCurrentPosition())
                    .addData("backRightEnc", drive.getBackRight().getCurrentPosition());
            telemetry.update();
        }
    }
}
