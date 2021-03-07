package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@Autonomous (name = "autonScoreRings")
public class autonProbably extends LinearOpMode {

    Robot prBot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap, telemetry);
        prBot.setMode(Constants.Status.AUTO);
        telemetry.addData("Desc", "This program picks up the rings, puts them into the goal, and parks")
                .addData("How to Use", "Prop against the wall, where the webcam is facing the field, on the middle of the line, and play");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            // move forward 3 inches
            prBot.getDrivetrain().forward(3);
            // pivot turn right 90 degrees
            prBot.getDrivetrain().pivotTurn(Constants.Status.RIGHT, 5);
            // move the drawbridge down
            prBot.getDrawbridge().down();
            sleep(3000);
            // move 20 inches
            prBot.getDrivetrain().forward(20);
            // move the drawbridge up
            prBot.getDrawbridge().up();
            sleep(3000);
            // move forward 124 inches
            prBot.getDrivetrain().forward(124);
            // shoot the rings into the bottom goal
            prBot.getDrawbridge().down();
            sleep(1000);
            prBot.getOuttake().shoot();
            sleep(3000);
            // move backwards 48 inches
            prBot.getDrawbridge().down();
            sleep(2000);
            prBot.getDrivetrain().backward(48);
        }
    }
}
