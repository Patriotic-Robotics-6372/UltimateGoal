package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@Autonomous (name = "EncForwardTestDrivetrain", group = "Encoder")
public class EncForwardTestDrivetrain extends LinearOpMode {

    //Robot prbot = new Robot();
    Drivetrain drive;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap.dcMotor.get("frontLeft"), hardwareMap.dcMotor.get("frontRight"), hardwareMap.dcMotor.get("backLeft"), hardwareMap.dcMotor.get("backRight"));
        drive.setTelemetry(telemetry);
        telemetry.addData("Desc", "Test program for moving forward with encoder");
        telemetry.update();
        drive.setPower(.2);
        waitForStart();
        while (opModeIsActive()) {
            drive.forward(12);
            sleep(5000);
            drive.backward(12);
            sleep(5000);
        }
    }
}
