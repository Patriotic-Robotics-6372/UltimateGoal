package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Flywheel;

@TeleOp (name = "FlywheelTest", group = "Test")
public class FlywheelTest extends LinearOpMode {

    Flywheel flywheel;

    @Override
    public void runOpMode() throws InterruptedException {
        flywheel = new Flywheel(hardwareMap.dcMotor.get("flywheel"));
        telemetry.addData("Desc", "Test the flywheel")
                .addData("How to Use", "r/l bumper to shoot/reverese");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.right_bumper) {
                flywheel.shoot();
            } else if (gamepad1.left_bumper) {
                flywheel.reverse();
            } else {
                flywheel.stop();
            }
            telemetry.setMsTransmissionInterval(20);
            telemetry.addData("flywheel power", flywheel.getFlywheel().getPower());
            telemetry.update();
        }
    }
}
