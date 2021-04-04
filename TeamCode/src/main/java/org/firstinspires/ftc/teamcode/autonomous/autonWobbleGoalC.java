package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.PIDController3;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@Autonomous (name = "autonWobbleGoalC", group = "primary")
public class autonWobbleGoalC extends LinearOpMode {

    Robot prbot = new Robot();
    ElapsedTime elapsedTime;
    PIDController3 pid;
    int setpoint;

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
        pid = new PIDController3(0.007, 0.000035, 0.0007, 20);
        waitForStart();
        while (opModeIsActive()) {
            elapsedTime = new ElapsedTime();
            prbot.getDrivetrain().backward(100);
            prbot.getWobbleGoal().letGo();
            sleep(200);
            prbot.getWobbleGoal().stopServos();
            prbot.getWobbleGoal().down();
            sleep(700);
            prbot.getWobbleGoal().stopMotor();
            prbot.getDrivetrain().forward(3);
            prbot.getDrivetrain().stop();
            sleep(500);
            prbot.getDrivetrain().pointTurnRight();
            prbot.getDrivetrain().stop();
            sleep(500);
            prbot.getDrivetrain().forward(24);
            prbot.getDrivetrain().pointTurnLeft();
            prbot.getDrivetrain().stop();
            sleep(500);
            prbot.getDrivetrain().forward(58);
            break;
        }
    }
}
