package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@Autonomous (name = "DrawbridgeUpTest", group = "Test")
public class DrawbridgeUpTest extends LinearOpMode {

    Robot prbot = new Robot();
    ElapsedTime elapsedTime;

    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.getDrivetrain().setTelemetry(telemetry);
        prbot.setMode(Constants.Status.AUTO);
        prbot.getWobbleGoal().setLiftPower(.3);
        prbot.getDrivetrain().setPower(.5);
        prbot.getDrawbridge().useEncoders();
        telemetry.addData("How to Use", "Should jump up and stay at a spot where drawbridge is not touching the ground");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            elapsedTime = new ElapsedTime();
            prbot.getDrawbridge().getPulleyBoi().setPower(0.5);
            telemetry.addData("power", prbot.getDrawbridge().getPulleyBoi().getPower());
            telemetry.addData("ms", elapsedTime.milliseconds());
            telemetry.update();
            while (elapsedTime.milliseconds() < 100) {
                telemetry.addData("power", prbot.getDrawbridge().getPulleyBoi().getPower());
                telemetry.addData("ms", elapsedTime.milliseconds());
                telemetry.update();
            }
            while (elapsedTime.milliseconds() < 10000) {
                prbot.getDrawbridge().getPulleyBoi().setPower(0.05);
                telemetry.addData("power", prbot.getDrawbridge().getPulleyBoi().getPower());
                telemetry.addData("ms", elapsedTime.milliseconds());
                telemetry.update();
                sleep(100);
                prbot.getDrawbridge().getPulleyBoi().setPower(0);
                telemetry.addData("power", prbot.getDrawbridge().getPulleyBoi().getPower());
                telemetry.addData("ms", elapsedTime.milliseconds());
                telemetry.update();
                sleep(20);
            }
            prbot.getDrawbridge().stop();
            break;
        }
    }
}
