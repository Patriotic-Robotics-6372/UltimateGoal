package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Button;
import org.firstinspires.ftc.teamcode.subsystem.Scoop;

@TeleOp (name = "ScoopTest", group = "Test")
public class ScoopTest extends LinearOpMode {

    Scoop scoop;
    int pos;
    Button dpad_up, dpad_down;

    @Override
    public void runOpMode() throws InterruptedException {
        scoop = new Scoop(hardwareMap.servo.get("leftScoop"), hardwareMap.servo.get("rightScoop"));
        scoop.setPos(90);
        dpad_up = new Button();
        dpad_down = new Button();
        telemetry.addData("Desc", "Test the scoop")
                .addData("How to Use", "y/b to scoop up/down");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            dpad_down.previous();
            dpad_down.setState(gamepad1.dpad_down);
            dpad_up.previous();
            dpad_up.setState(gamepad1.dpad_up);
            if (dpad_down.isPressed()) {
                scoop.decreasePos();
            } else if (dpad_up.isPressed()) {
                scoop.increasePos();
            }
            if (gamepad1.y) {
                scoop.scoopUp();
            } else if (gamepad1.b) {
                scoop.scoopDown();
            }

            telemetry.setMsTransmissionInterval(20);
            telemetry.addData("pos", scoop.getPos());
            telemetry.addData("leftScoop pos", scoop.getLeftScoop().getPosition());
            telemetry.addData("rightScoop pos", scoop.getRightScoop().getPosition());
            telemetry.update();
        }
    }
}
