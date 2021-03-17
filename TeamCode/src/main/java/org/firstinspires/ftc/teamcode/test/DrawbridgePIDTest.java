package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Button;
import org.firstinspires.ftc.teamcode.subsystem.Drawbridge;
import org.firstinspires.ftc.teamcode.subsystem.PIDControllerAlt;
import org.firstinspires.ftc.teamcode.subsystem.Telem;

@Autonomous (name = "DrawbridgePIDTest", group = "Test")
public class DrawbridgePIDTest extends LinearOpMode {

    Drawbridge drawbridge = new Drawbridge(hardwareMap.dcMotor.get("pulleyBoi"));
    Telem telem;
    PIDControllerAlt movePID;
    double correction, power = 0.3;
    boolean toggle = false;
    Button y;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Desc", "This programs tests for the drawbridge PID")
                .addData("How to Use", "Run the code, pulleyBoi should try to stay in one spot");
        telemetry.update();
        telem = new Telem(drawbridge, telemetry);
        movePID = new PIDControllerAlt(0.01, 0, 0);
        y = new Button();
        waitForStart();
        movePID.setSetpoint(0);
        movePID.setOutputRange(0, power);
        movePID.setInputRange(-1000, 1000);
        movePID.enable();
        while (opModeIsActive()) {
            y.previous();
            y.setState(gamepad1.y);
            if (y.isPressed()) {
                toggle = !toggle;
            }
            if (toggle) {
                correction = movePID.performPID(drawbridge.getPulleyBoi().getCurrentPosition());
                drawbridge.setPower(power + correction);
            }
            telemetry.addData("correction", correction);
            telemetry.addData("power", power + correction);
            telemetry.addData("encoder", drawbridge.getPulleyBoi().getCurrentPosition());
            telemetry.update();
        }
    }
}
