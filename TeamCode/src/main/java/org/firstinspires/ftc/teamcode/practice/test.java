package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous (name = "Test")
public class test extends LinearOpMode {

    private DcMotor frontLeft, frontRight, backLeft, backRight;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            move(0.3);
            updateStatus();
        }
    }

    public void move(double power) {
        move(power, power, power, power);
        updateStatus();
    }

    public void move(double fL, double fR, double bL, double bR) {
        frontLeft.setPower(fL);
        frontRight.setPower(fR);
        backLeft.setPower(bL);
        backRight.setPower(bR);
    }

    public void updateStatus() {
        telemetry.addLine()
                .addData("fL", frontLeft.getPower())
                .addData("fR", frontRight.getPower())
                .addData("bL", backLeft.getPower())
                .addData("bR", backRight.getPower());
        telemetry.addLine()
                .addData("fL", frontLeft.getCurrentPosition())
                .addData("fR", frontRight.getCurrentPosition())
                .addData("bL", backLeft.getCurrentPosition())
                .addData("bR", backRight.getCurrentPosition());
        telemetry.update();
    }
}
