package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

public class autonWobbleGoal extends LinearOpMode {

    Robot prBot = new Robot();
    enum ringPosition {
        NONE, ONE, FOUR
    }
    ringPosition autonState = ringPosition.NONE;

    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap, telemetry);
        prBot.setMode(Constants.Status.AUTO);
        telemetry.addData("Desc", "This program picks up the rings, puts them into the goal, and parks")
                .addData("How to Use", "Prop against the wall, where the webcam is facing the field, on the middle of the right line. The wobble goal should be on the robot, with the lift raised up to the marker");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            prBot.getWobbleGoal().grab();
            // use ringPositionPipeline, return the enumerator
            // program the three states
            prBot.getDrivetrain().forward(3);
            prBot.getDrivetrain().pivotTurn(Constants.Status.RIGHT, 5);
            if (autonState == ringPosition.NONE) {
                prBot.getDrivetrain().forward(90);
                prBot.getDrivetrain().pivotTurn(Constants.Status.RIGHT, 5);
                prBot.getWobbleGoal().down();
                sleep(1500);
                prBot.getWobbleGoal().letGo();
                sleep(2000);
                prBot.getWobbleGoal().up();
                sleep(2000);
                prBot.getDrivetrain().backward(10);
                prBot.getDrivetrain().pointTurn(Constants.Status.LEFT, 5);
            } else if (autonState == ringPosition.ONE) {
                prBot.getDrivetrain().forward(20);
                prBot.getDrivetrain().forward(94);
                //prBot.getDrivetrain().pivotTurn(Constants.Status.RIGHT, 5);
                //prBot.getDrivetrain().forward(24);
                prBot.getWobbleGoal().down();
                sleep(1500);
                prBot.getWobbleGoal().letGo();
                sleep(2000);
                prBot.getWobbleGoal().up();
                sleep(2000);
                prBot.getDrivetrain().backward(20);
                //prBot.getDrivetrain().pointTurn(Constants.Status.LEFT, 5);
                //prBot.getDrivetrain().backward(24);
            } else if (autonState == ringPosition.FOUR) {
                prBot.getDrivetrain().forward(20);
                prBot.getDrivetrain().forward(118);
                prBot.getDrivetrain().pivotTurn(Constants.Status.RIGHT, 5);
                prBot.getDrivetrain().forward(6);
                prBot.getWobbleGoal().down();
                sleep(1500);
                prBot.getWobbleGoal().letGo();
                sleep(2000);
                prBot.getWobbleGoal().up();
                sleep(2000);
                prBot.getDrivetrain().backward(16);
                prBot.getDrivetrain().pointTurn(Constants.Status.LEFT, 5);
                prBot.getDrivetrain().backward(48);
            }
        }
    }
}
