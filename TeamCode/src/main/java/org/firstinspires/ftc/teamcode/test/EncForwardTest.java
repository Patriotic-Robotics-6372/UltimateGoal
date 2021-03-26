package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@Autonomous (name = "EncForwardTest", group = "Encoder")
public class EncForwardTest extends LinearOpMode {

    Robot prbot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.AUTO);
        telemetry.addData("Desc", "Test program for moving forward with encoder");
        telemetry.update();
        prbot.getDrivetrain().setPower(.2);
        waitForStart();
        while (opModeIsActive()) {
            prbot.getDrivetrain().forward(12);
            sleep(5000);
            prbot.getDrivetrain().backward(12);
            sleep(5000);
        }
    }
}
