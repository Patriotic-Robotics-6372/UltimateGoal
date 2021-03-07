package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Drawbridge;
import org.firstinspires.ftc.teamcode.subsystem.Telem;

@TeleOp (name = "DrawbridgeTest", group = "Test")
public class DrawbridgeTest extends LinearOpMode {

    Drawbridge drawbridge;
    Telem telem;

    @Override
    public void runOpMode() throws InterruptedException {
        drawbridge = new Drawbridge(hardwareMap.dcMotor.get("pulleyBoi"));
        telem = new Telem(drawbridge, telemetry);
        drawbridge.setPower(.70);
        telemetry.addData("Desc", "This programs tests for the Drawbridge class")
                .addData("How to Use", "Dpad Up to go up, Dpad Down to go down");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                drawbridge.up();
            } else if (gamepad1.dpad_down) {
                drawbridge.down();
            } else {
                drawbridge.stop();
            }
            telem.addDrawbridge();
            telem.update();
        }
    }
}
