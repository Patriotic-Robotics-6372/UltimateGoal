package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "JosephTeleop")
// Name that shows up on the phone
public class JosephTeleop extends OpMode {
    // State hardware here. This is the base motors
    DcMotor frontRight, frontLeft, backRight, backLeft;
    //pulleyBoi is the drawbrigde thing
    DcMotor pulleyBoi;
    //Wobble lift stuff
    DcMotor wobbleLift;
    Servo wobbleGrabber;
    //Roller is the intake motor
    DcMotor roller;
    //shoot is the motor for the outake and positioner is the servo that helps the outake shoot
    DcMotor shoot;
    Servo positioner;

    @Override
    public void init() {
        // Motors
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        pulleyBoi = hardwareMap.dcMotor.get("pulleyBoi");
        wobbleLift = hardwareMap.dcMotor.get("wobbleLift");
        roller = hardwareMap.dcMotor.get("roller");
        shoot = hardwareMap.dcMotor.get("shoot");

        // Servos
        wobbleGrabber = hardwareMap.servo.get("wobbleGrabber");
        positioner = hardwareMap.servo.get("positioner");

    }

    @Override
    public void loop() {
        if (Math.abs(gamepad1.left_stick_y)>0.1){
            //Left side base motors
        frontLeft.setPower(gamepad1.left_stick_y);
        backLeft.setPower(gamepad1.left_stick_y);
        }
        //this just tells the motors to stop if not in use
        else{
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }
             //Right side base motors
        if (Math.abs(gamepad1.right_stick_y)>0.1){
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);
        }
        else{
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        //drawbrigde
        if (gamepad1.dpad_up){
           pulleyBoi.setPower(-1);
        }
        else if (gamepad1.dpad_down) {
           pulleyBoi.setPower(1);
        }
        else {
            pulleyBoi.setPower(0);
        }
        
        //Wobble lift
            if (gamepad1.a){
            wobbleLift.setPower(.5);
        }
        if (gamepad1.y) {
            wobbleGrabber.setPosition(1);
        } else {
            wobbleGrabber.setPosition(0);
        }
        // lift
        if (gamepad1.a) {
            wobbleLift.setPower(0.5);
        } else if (gamepad1.x) {
            wobbleLift.setPower(-0.5); // lifts wobble goal
        } else {
            wobbleLift.setPower(0);
        }

        // intake
        if (gamepad1.right_trigger > 0.1) {
            roller.setPower(gamepad1.right_trigger);
        } else if (gamepad1.left_trigger > 0.1) {
            roller.setPower(-gamepad1.left_trigger);
        } else {
            roller.setPower(0);
        }

        // outtake
        // positioner
        if (gamepad1.b) {
            positioner.setPosition(1);
        } else {
            positioner.setPosition(0);
        }
        // shoot
        if (gamepad1.right_bumper) {
            shoot.setPower(-1);
        } else {
            shoot.setPower(0);
        }















    }
}