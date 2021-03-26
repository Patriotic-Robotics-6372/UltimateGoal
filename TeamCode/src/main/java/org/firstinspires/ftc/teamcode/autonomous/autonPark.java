package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@Autonomous (name = "autonPark", group = "auton")
public class autonPark extends LinearOpMode {

    Robot prBot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap, telemetry);
        prBot.setMode(Constants.Status.AUTO);
        telemetry.addData("Desc", "This program parks the robot, as long as it is against the wall")
                .addData("How to Use", "Prop against the wall, where the webcame is facing the field, on the middle of the line,  and play");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            prBot.getDrivetrain().setPower(.2);
            prBot.getDrivetrain().backward(1);
            prBot.getDrivetrain().stop();
            sleep(500);
            //prBot.getDrivetrain().pivotTurn(Constants.Status.RIGHT, 4.5);
            prBot.getDrivetrain().pivotTurn(Constants.Status.RIGHT, 3);
            prBot.getDrivetrain().stop();
            break;
        }
    }
}
