package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.Telem;

/**
 * Date: 1/29/21
 * Test program for Drivetrain subsystem
 * @author Jacob Marinas
 */
@TeleOp (name = "DrivetrainTest", group = "Test")
public class DrivetrainTest extends LinearOpMode {

    Drivetrain drive;
    //Telem telem = new Telem(drive, telemetry);

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap.dcMotor.get("frontLeft"),
                hardwareMap.dcMotor.get("frontRight"),
                hardwareMap.dcMotor.get("backLeft"),
                hardwareMap.dcMotor.get("backRight"));
        drive.useBrake(true);
        drive.useEncoders(true);
        drive.setPower(0.2);
        telemetry.addData("Desc", "This programs tests for the Drivetrain class")
                .addData("How to Use", "Tank drive. Sticks for basic movement. Buttons for encoder movement");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            if (Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_y) > .1) {
                drive.setLeftSide(-gamepad1.left_stick_y, -gamepad1.left_stick_y);
                drive.setRightSide(-gamepad1.right_stick_y, -gamepad1.right_stick_y);
            } else {
                drive.stop();
            }

            if (gamepad1.y) {
                drive.forward(5);
            }
            if (gamepad1.a) {
                drive.backward(5);
            }
            if (gamepad1.x) {
                drive.pointTurn(Drivetrain.Status.LEFT, 3);
            }
            if (gamepad1.y) {
                drive.pointTurn(Drivetrain.Status.RIGHT, 3);
            }

            telemetry.addLine()
                    .addData("fLPower", drive.getFrontLeft().getPower())
                    .addData("fLEnc", drive.getFrontLeft().getCurrentPosition());
            telemetry.addLine()
                    .addData("fRPower", drive.getFrontRight().getPower())
                    .addData("fREnc", drive.getFrontRight().getCurrentPosition());
            telemetry.addLine()
                    .addData("bLPower", drive.getBackLeft().getPower())
                    .addData("bLEnc", drive.getBackLeft().getCurrentPosition());
            telemetry.addLine()
                    .addData("bRPower", drive.getBackRight().getPower())
                    .addData("bREnc", drive.getBackRight().getCurrentPosition());
            telemetry.update();
        }
    }
}
