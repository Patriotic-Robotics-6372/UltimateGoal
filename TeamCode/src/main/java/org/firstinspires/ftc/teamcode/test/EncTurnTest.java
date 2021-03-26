package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@Autonomous (name = "EncTurnTest", group = "Encoder")
public class EncTurnTest extends LinearOpMode {

    Robot prbot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.AUTO);
        telemetry.addData("Desc", "Test program for moving forward with encoder");
        telemetry.update();
        prbot.getDrivetrain().setPower(.4);
        waitForStart();
        while (opModeIsActive()) {
            prbot.getDrivetrain().pivotTurn(Constants.Status.RIGHT, 20);
            sleep(5000);
            prbot.getDrivetrain().pivotTurn(Constants.Status.LEFT, 20);
            sleep(5000);
        }
    }
}
