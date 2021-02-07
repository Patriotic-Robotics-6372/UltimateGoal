package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Button;

@TeleOp (name = "ButtonTest", group = "Test")
public class ButtonTest extends LinearOpMode {

    boolean press, hold, release;

    Button x = new Button();

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Desc", "This programs tests for the Button class, the toggles")
                .addData("How to Use", "Press x to toggle");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            x.previous();
            x.setState(gamepad1.x);
            if (x.isPressed()) {
                press = !press;
            }
            if (x.isHeld()) {
                hold = true;
            } else {
                hold = false;
            }
            if (x.isReleased()) {
                release = !release;
            }
            telemetry.addData("Press", press);
            telemetry.addData("Hold", hold);
            telemetry.addData("Release", release);
            telemetry.addData("CurrentState", x.getCurrentState());
            telemetry.addData("PreviousState", x.getPreviousState());
            telemetry.update();
        }
    }
}
