package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@Autonomous (name = "autonPark", group = "autonomous")
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
            prBot.getDrivetrain().forward(72);
            prBot.getDrivetrain().stop();
        }
    }
}
