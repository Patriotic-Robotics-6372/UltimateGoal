package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystem.Button;

@TeleOp (name = "CRServoTest", group = "Test")
public class CRServoTest extends LinearOpMode {

    CRServo servo;
    int pos;
    Button up, down, right;

    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.crservo.get("servo");
        telemetry.addData("How to Use", "Dpad up/down to increase/decrase pos. dpad right to toggle pos");
        telemetry.update();
        up = new Button();
        down = new Button();
        right = new Button();
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
            if (right.isPressed()) {
                servo.setPower(pos/100);
            }
            telemetry.addData("toggle", right.getToggle());
            telemetry.addData("servo pos", servo.getPower());
            telemetry.update();
        }
    }
}
