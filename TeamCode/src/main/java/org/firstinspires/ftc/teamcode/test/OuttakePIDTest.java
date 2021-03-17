package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Outtake;

@Autonomous (name = "OuttakePIDTest", group = "Test")
public class OuttakePIDTest extends LinearOpMode {

    Outtake outtake = new Outtake(hardwareMap.dcMotor.get("shoot"), hardwareMap.crservo.get("positioner"));

    @Override
    public void runOpMode() throws InterruptedException {

    }
}
