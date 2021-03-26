package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp (name = "GG: flyWheelTest", group = "GG")
public class flyWheelTest extends LinearOpMode {

    DcMotor rightFlywheel;
    DcMotor leftFlywheel;

    @Override
    public void runOpMode() throws InterruptedException {
        rightFlywheel = hardwareMap.dcMotor.get("rightFlywheel");
        leftFlywheel = hardwareMap.dcMotor.get("leftFlywheel");
        telemetry.addData("How to Use", "Use the right trigger to shoot. left trigger to reverse");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.right_trigger > 0.2) {
                rightFlywheel.setPower(gamepad1.right_trigger);
                leftFlywheel.setPower(-gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > 0.2) {
                rightFlywheel.setPower(-gamepad1.left_trigger);
                leftFlywheel.setPower(gamepad1.left_trigger);
            } else {
                rightFlywheel.setPower(0);
                leftFlywheel.setPower(0);
            }
        }
    }
}
