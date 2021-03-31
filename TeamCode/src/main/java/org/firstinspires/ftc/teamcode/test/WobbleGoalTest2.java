package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.WobbleGoal;

@Disabled
@TeleOp (name = "WobbleGoalTest2", group = "Test")
public class WobbleGoalTest2 extends LinearOpMode {

    WobbleGoal wobbleGoal;

    @Override
    public void runOpMode() throws InterruptedException {
        //wobbleGoal = new WobbleGoal(hardwareMap.dcMotor.get("wobbleLift"), hardwareMap.dcMotor.get("wobbleGrabber"), hardwareMap.dcMotor.get)
    }
}
