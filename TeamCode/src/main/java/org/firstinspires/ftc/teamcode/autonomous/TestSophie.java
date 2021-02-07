package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

// This line of code makes it so that the program appears on the phone
@Autonomous (name = "TestSophie")
public class TestSophie extends LinearOpMode {
    // Name our hardware -> name our varibles

    // Motors for base
    DcMotor frontLeft, frontRight, backLeft, backRight;

    // Motor for pulling main compartment up
    DcMotor pulleyBoi;

    // Motor for lifting the wobble goal
    DcMotor wobbleLift;

    // Motor for spinning the roller
    DcMotor roller;

    // Motor for launching the ring
    DcMotor shoot;

    // Servo for grabbing the wobble goal
    Servo wobbleGrabber;

    // Servo for controlling when to launch the ring
    Servo positioner;

    @Override
    public void runOpMode() throws InterruptedException {
        // Defining our motors to our driver station / robot controller
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        waitForStart();

        frontLeft.setPower(-0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(-0.5);
        backRight.setPower(0.5);

        sleep(3500);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
