package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.subsystem.Button;
import org.firstinspires.ftc.teamcode.subsystem.FlywheelPID;

@TeleOp (name = "FlywheelPIDTest", group = "Test")
public class FlywheelPIDTest extends LinearOpMode {

    FlywheelPID flywheel;
    public Button dpad_up, dpad_down, dpad_left, dpad_right;
    public double power = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        flywheel = new FlywheelPID((DcMotorEx) hardwareMap.dcMotor.get("flywheel"));
        flywheel.init();
        telemetry.setMsTransmissionInterval(20);
        dpad_up = new Button();
        dpad_down = new Button();
        dpad_left = new Button();
        dpad_right = new Button();
        telemetry.addData("Desc", "Flywheel with PID")
                .addData("How to Use", "dpad up/down to increase/decrease rpm, r/l bumper to shoot/reverse");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            dpad_down.previous();
            dpad_down.setState(gamepad1.dpad_down);
            dpad_up.previous();
            dpad_up.setState(gamepad1.dpad_up);
            dpad_left.previous();
            dpad_left.setState(gamepad1.dpad_left);
            dpad_right.previous();
            dpad_right.setState(gamepad1.dpad_right);   
            if (dpad_down.isPressed()) {
                flywheel.decraseMaxRPM();
            } else if (dpad_up.isPressed()) {
                flywheel.increaseMaxRPM();
            }
            if (dpad_right.isPressed()) {
                power += 0.05;
                if (power > 1) {
                    power = 1;
                }
            }
            if (dpad_left.isPressed()) {
                power -= 0.05;
                if (power < -1) {
                    power = -1;
                }
            }
            flywheel.getFlywheel().setPower(power);
            telemetry.addData("power", power);
            telemetry.addData("flywheel power", flywheel.getFlywheel().getPower());
            telemetry.addData("flywheel pos", flywheel.getFlywheel().getCurrentPosition());
            telemetry.addData("flywheel max rpm", flywheel.getMaxRPM());
            telemetry.addData("flywheel rpm", flywheel.getFlywheel().getVelocity());
            telemetry.update();
        }
    }
}
