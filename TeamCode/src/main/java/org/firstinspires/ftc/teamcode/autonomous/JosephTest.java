package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

// This is the line of code that makes it so that it appears on the phone
@Autonomous(name = "JosephTest")
public class JosephTest extends LinearOpMode {

    // this is stating the hardware for the base
    DcMotor frontLeft, frontRight, backLeft, backRight;
    // this is stating the hardware for the main lift
    DcMotor pulleyBoi;
    // this is stating the hardware for the wobble goal lift
    DcMotor wobblelift;
    // this is stating the hardware for intake motor
    DcMotor roller;
    // this is stating the hardware for the shooting motor
    DcMotor shooter;
    // This is stating the hardware for the wobble foal grabber servo
    Servo WobbleGrabber;
    // this is stating the hardware for the servo that is used for controling the launch ring
    Servo postitioner;
    @Override
    public void runOpMode() throws InterruptedException {
        //Defining our motors and servos to the phone
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        waitForStart();

        frontRight.setPower(.5);
        frontLeft.setPower(-.5);
        backRight.setPower(.5);
        backLeft.setPower(-.5);

        sleep(3500);

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        






    }
}
