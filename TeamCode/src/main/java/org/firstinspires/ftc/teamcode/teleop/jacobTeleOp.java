package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "jacobTeleOp")
public class jacobTeleOp extends OpMode {

    // base motors
    DcMotor frontLeft, frontRight, backLeft, backRight;

    // drawbridge
    DcMotor pulleyBoi;

    // wobble goal thing
    DcMotor wobbleLift;
    Servo wobbleGrabber;

    // Intake
    DcMotor roller;

    // Outtake
    DcMotor shoot;
    Servo positioner;

    @Override
    public void init() {

        // motors
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        pulleyBoi = hardwareMap.dcMotor.get("pulleyBoi");
        wobbleLift = hardwareMap.dcMotor.get("wobbleLift");
        roller = hardwareMap.dcMotor.get("roller");
        shoot = hardwareMap.dcMotor.get("shoot");

        // servos
        wobbleGrabber = hardwareMap.servo.get("wobbleGrabber");
        positioner = hardwareMap.servo.get("positioner");

    }

    @Override
    public void loop() {

        // code for the base
        // left side
        if (Math.abs(gamepad1.left_stick_y) > 0.1) {
            frontLeft.setPower(gamepad1.left_stick_y);
            backLeft.setPower(gamepad1.left_stick_y);
        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }
        // right side
        if (Math.abs(gamepad1.right_stick_y) > 0.1) {
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);
        } else {
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        // drawbridge
        if (gamepad1.dpad_up) {
            pulleyBoi.setPower(1); // bring the box thing up
        } else if (gamepad1.dpad_down) {
            pulleyBoi.setPower(-1); // bring the box thing down
        } else {
            pulleyBoi.setPower(0);
        }

        // wobble goal mechanism
        // grabber
        if (gamepad1.y) {
            wobbleGrabber.setPosition(1); // grab the wobble goal; grab position
        } else {
            wobbleGrabber.setPosition(0); // neutral position
        }
        // lift
        if (gamepad1.a) {
            wobbleLift.setPower(0.5); // lowers wobble goal
        } else if (gamepad1.x) {
            wobbleLift.setPower(-0.5); // lifts wobble goal
        } else {
            wobbleLift.setPower(0);
        }

        // intake
        if (gamepad1.right_trigger > 0.1) {
            roller.setPower(gamepad1.right_trigger); // intakes the rings
        } else if (gamepad1.left_trigger > 0.1) {
            roller.setPower(-gamepad1.left_trigger); // spit out the rings
        } else {
            roller.setPower(0);
        }

        // outtake
        // positioner
        if (gamepad1.b) {
           positioner.setPosition(1); // Pushes the ring to prepare for shooting
        } else {
            positioner.setPosition(0); // Neutral position; ring falls down into place
        }
        // shoot
        if (gamepad1.right_bumper) {
            shoot.setPower(-1); // shoots the ring; fixed power
        } else {
            shoot.setPower(0);
        }



    }
}
