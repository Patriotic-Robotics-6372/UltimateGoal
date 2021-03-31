package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Button;
import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.Telem;

/**
 * Date: 1/29/21
 * Test program for Drivetrain subsystem
 * @author Jacob Marinas
 */
@TeleOp (name = "DrivetrainTurnTest", group = "Test")
public class DrivetrainTurnTest extends LinearOpMode {

    Drivetrain drive;
    //Telem telem = new Telem(drive, telemetry);
    Button y, x, a;
    double dist;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap.dcMotor.get("frontLeft"),
                hardwareMap.dcMotor.get("frontRight"),
                hardwareMap.dcMotor.get("backLeft"),
                hardwareMap.dcMotor.get("backRight"));
        y = new Button();
        x = new Button();
        a = new Button();
        drive.setTelemetry(telemetry);
        drive.useBrake(true);
        drive.useEncoders(true);
        drive.setPower(0.2);
        telemetry.addData("Desc", "This programs tests for turning using encoders in the Drivetrain class")
                .addData("How to Use", "y to increase dist, x  to decrease dist, a to enable a turn");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            //drive.pointTurn(Constants.Status.LEFT, 8);
//            telemetry.addLine()
//                    .addData("fLPower", drive.getFrontLeft().getPower())
//                    .addData("fLEnc", drive.getFrontLeft().getCurrentPosition());
//            telemetry.addLine()
//                    .addData("fRPower", drive.getFrontRight().getPower())
//                    .addData("fREnc", drive.getFrontRight().getCurrentPosition());
//            telemetry.addLine()
//                    .addData("bLPower", drive.getBackLeft().getPower())
//                    .addData("bLEnc", drive.getBackLeft().getCurrentPosition());
//            telemetry.addLine()
//                    .addData("bRPower", drive.getBackRight().getPower())
//                    .addData("bREnc", drive.getBackRight().getCurrentPosition());
//            telemetry.update();
//            sleep(2000);
//            drive.pointTurn(Constants.Status.RIGHT, 8);
//            sleep(2000);
//            drive.pointTurn(Constants.Status.LEFT, 10);
//            sleep(2000);
//            drive.pointTurn(Constants.Status.RIGHT, 10);
//            sleep(2000);
//            drive.pointTurn(Constants.Status.LEFT, 12);
//            sleep(2000);
//            drive.pointTurn(Constants.Status.RIGHT, 12);
//            sleep(2000);
//            drive.pointTurn(Constants.Status.LEFT, 14);
//            sleep(2000);
//            drive.pointTurn(Constants.Status.RIGHT, 14);
//            sleep(2000);
//            drive.pointTurn(Constants.Status.LEFT, 16);
//            sleep(2000);
//            drive.pointTurn(Constants.Status.RIGHT, 16);
//            sleep(2000);
              y.previous();
              y.setState(gamepad1.y);
              x.previous();
              x.setState(gamepad1.x);
              a.previous();
              a.setState(gamepad1.a);
              if (y.isPressed()) {
                  dist += .5;
              }
              if (x.isPressed()) {
                  dist -= .5;
              }
              if (a.isPressed()) {
                  drive.pointTurn(Constants.Status.LEFT, dist);
                  sleep(4000);
                  drive.pointTurn(Constants.Status.RIGHT, dist);
                  sleep(4000);
              }
        }
    }
}
