package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@Autonomous (name = "autonWobbleGoalB", group = "primary")
public class autonWobbleGoalB extends LinearOpMode {

    Robot prbot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.getDrivetrain().setTelemetry(telemetry);
        prbot.setMode(Constants.Status.AUTO);
        prbot.getWobbleGoal().setLiftPower(.3);
        prbot.getDrivetrain().setPower(.5);
        telemetry.addData("How to Use", "Right tape, aligned with left wheels");
        telemetry.update();
        prbot.getWobbleGoal().grab();
        waitForStart();
        while (opModeIsActive()) {
            prbot.getDrivetrain().backward(84);
            prbot.getWobbleGoal().letGo();
            sleep(200);
            prbot.getWobbleGoal().stopServos();
            prbot.getWobbleGoal().down();
            sleep(700);
            prbot.getWobbleGoal().stopMotor();
            prbot.getDrivetrain().forward(42);
            prbot.getDrivetrain().stop();
            break;
        }
    }
}
