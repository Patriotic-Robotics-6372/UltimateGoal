package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystem.Button;
import org.firstinspires.ftc.teamcode.subsystem.Drawbridge;
import org.firstinspires.ftc.teamcode.subsystem.PIDControllerAlt;
import org.firstinspires.ftc.teamcode.subsystem.Telem;

@Disabled
@TeleOp (name = "DrawbridgePIDTest_old", group = "Test")
public class DrawbridgePIDTest extends LinearOpMode {

    Drawbridge drawbridge;
    Telem telem;
    PIDControllerAlt movePID;
    double correction, power = 1, setpoint = 0;
    boolean toggle = false;
    Button y, dpad_up, dpad_down;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Desc", "This programs tests for the drawbridge PID")
                .addData("How to Use", "Run the code, pulleyBoi should try to stay in one spot");
        telemetry.update();
        drawbridge = new Drawbridge(hardwareMap.dcMotor.get("pulleyBoi"));
        telem = new Telem(drawbridge, telemetry);
        movePID = new PIDControllerAlt(0.5, 0, 0);
        y = new Button();
        dpad_down = new Button();
        dpad_up = new Button();
        drawbridge.getPulleyBoi().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drawbridge.getPulleyBoi().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        movePID.setSetpoint(setpoint);
        movePID.setOutputRange(-power, power);
        movePID.setInputRange(-1000, 1000);
        movePID.setContinuous(true);
        movePID.enable();
        while (opModeIsActive()) {
            y.previous();
            y.setState(gamepad1.y);
            dpad_down.previous();
            dpad_down.setState(gamepad1.dpad_down);
            dpad_up.previous();
            dpad_up.setState(gamepad1.dpad_up);
            if (dpad_up.isHeld()) {
                setpoint += 1;
            }
            if (dpad_down.isHeld()) {
                setpoint -= 1;
            }
            if (y.isPressed()) {
                toggle = !toggle;
            }
            movePID.setSetpoint(setpoint);
            if (toggle) {
                correction = movePID.performPID(drawbridge.getPulleyBoi().getCurrentPosition());
                drawbridge.getPulleyBoi().setPower(power + correction);
            }
            telemetry.addData("correction", correction);
            telemetry.addData("power", power + correction);
            telemetry.addData("motor power", drawbridge.getPulleyBoi().getPower());
            telemetry.addData("encoder", drawbridge.getPulleyBoi().getCurrentPosition());
            telemetry.addData("toggle", toggle);
            telemetry.addData("setpoint", setpoint);
            telemetry.update();
        }
    }
}
