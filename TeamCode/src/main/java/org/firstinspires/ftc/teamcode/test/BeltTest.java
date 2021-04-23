package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Belt;

@TeleOp (name = "BeltTest", group = "Test")
public class BeltTest extends LinearOpMode {

    public Belt belt;

    @Override
    public void runOpMode() throws InterruptedException {
        belt = new Belt(hardwareMap.dcMotor.get("belt"));
        belt.setPower(1);
        telemetry.addData("Desc", "Test the belt")
                .addData("How to use", "x/a to go forwards/backwards");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.x) {
                belt.forward();
            } else if (gamepad1.a) {
                belt.backward();
            } else {
                belt.stop();
            }
            telemetry.addData("Belt Power", belt.getBelt().getPower());
            telemetry.update();
        }
    }
}
