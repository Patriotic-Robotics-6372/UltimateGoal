package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystem.Button;
import org.firstinspires.ftc.teamcode.subsystem.Drawbridge;
import org.firstinspires.ftc.teamcode.subsystem.PIDController3;
import org.firstinspires.ftc.teamcode.subsystem.PIDControllerAlt;
import org.firstinspires.ftc.teamcode.subsystem.Telem;

@TeleOp (name = "DrawbridgePIDTest", group = "Test")
public class DrawbridgePIDTest2 extends LinearOpMode {

    Drawbridge drawbridge;
    Telem telem;
    PIDController3 movePID;
    double correction, power = 1, setpoint = 0, currentState, rate = 1;
    boolean toggle = false;
    Button y, dpad_up, dpad_down;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Desc", "This programs tests for the drawbridge PID")
                .addData("How to Use", "Run the code, pulleyBoi should try to stay in one spot");
        telemetry.update();
        drawbridge = new Drawbridge(hardwareMap.dcMotor.get("pulleyBoi"));
        telem = new Telem(drawbridge, telemetry);
        movePID = new PIDController3(0.007, 0.000035, 0.0007, 20);
        y = new Button();
        dpad_down = new Button();
        dpad_up = new Button();
        drawbridge.useEncoders();
        waitForStart();
        while (opModeIsActive()) {
            y.previous();
            y.setState(gamepad1.y);
            dpad_down.previous();
            dpad_down.setState(gamepad1.dpad_down);
            dpad_up.previous();
            dpad_up.setState(gamepad1.dpad_up);
            if (dpad_up.isPressed()) {
                rate += 1;
            }
            if (dpad_down.isPressed()) {
                rate -= 1;
            }
            if (gamepad1.dpad_left) {
                setpoint -= rate;
            } else if (gamepad1.dpad_right) {
                setpoint += rate;
            }
            if (y.isPressed()) {
                toggle = !toggle;
            }
            currentState = drawbridge.getPulleyBoi().getCurrentPosition();
            if (toggle) {
                correction = movePID.output(setpoint, currentState);
                //drawbridge.getPulleyBoi().setPower(power + correction);
                drawbridge.getPulleyBoi().setPower(correction);
            }
            telemetry.addData("correction", correction);
            //telemetry.addData("power", power + correction);
            telemetry.addData("motor power", drawbridge.getPulleyBoi().getPower());
            telemetry.addData("encoder", drawbridge.getPulleyBoi().getCurrentPosition());
            telemetry.addData("toggle", toggle);
            telemetry.addData("setpoint", setpoint);
            telemetry.addData("rate", rate);
            telemetry.update();
        }
    }
}
