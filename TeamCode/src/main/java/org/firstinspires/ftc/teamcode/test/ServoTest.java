package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystem.Button;

@TeleOp (name = "ServoTest", group = "Test")
public class ServoTest extends LinearOpMode {

    Servo servo;
    int pos;
    Button up, down, right;

    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.servo.get("servo");
        telemetry.addData("How to Use", "Dpad up/down to increase/decrase pos. dpad right to toggle pos");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            up.previous();
            up.setState(gamepad1.dpad_up);
            down.previous();
            down.setState(gamepad1.dpad_down);
            right.previous();
            right.setState(gamepad1.dpad_right);
            if (up.isPressed()) {
                pos++;
            } else if (down.isPressed()) {
                pos--;
            }
            telemetry.addData("pos", pos);
            if (right.getToggle()) {
                servo.setPosition(pos);
            }
            telemetry.addData("toggle", right.getToggle());
            telemetry.addData("servo pos", servo.getPosition());
            telemetry.update();
        }
    }
}
