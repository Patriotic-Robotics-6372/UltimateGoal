package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "GamepadTest", group = "Test")
public class GamepadTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("How to Use", "Tests gamepad inputs");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("atRest()", gamepad1.atRest());
            telemetry.addData("getGamepadId()", gamepad1.getGamepadId());
            telemetry.addData("toString()", gamepad1.toString());
            telemetry.update();
        }
    }
}
