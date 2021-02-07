package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "MotorTest", group = "Test")
public class MotorTest extends LinearOpMode {

    private DcMotor motor;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.dcMotor.get("motor");
        telemetry.addData("Desc", "This programs tests for a DcMotor")
                .addData("How to Use", "Values should update when motor is powered");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            motor.setPower(0.15);
            telemetry.addData("motorEnc", motor.getCurrentPosition());
            telemetry.update();
        }
    }
}
