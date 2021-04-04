package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.PIDController3;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@Autonomous (name = "DrawbridgeGradualTest", group = "Test")
public class DrawbridgeGradualTest extends LinearOpMode {

    Robot prbot = new Robot();
    ElapsedTime elapsedTime;
    PIDController3 pid;
    int setpoint = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.getDrivetrain().setTelemetry(telemetry);
        prbot.setMode(Constants.Status.AUTO);
        prbot.getDrawbridge().useEncoders();
        telemetry.addData("How to Use", "Right tape, aligned with left wheels");
        telemetry.update();
        pid = new PIDController3(0.007, 0.000035, 0.0007, 20);
        waitForStart();
        while (opModeIsActive()) {
            elapsedTime = new ElapsedTime();
            while (elapsedTime.milliseconds() < 1000) {
                prbot.getDrawbridge().getPulleyBoi().setPower(pid.output(setpoint, prbot.getDrawbridge().getPulleyBoi().getCurrentPosition()));
            }
            while (prbot.getDrawbridge().getPulleyBoi().getCurrentPosition() < 600) {
                if (setpoint < 600) {
                    setpoint += 2;
                }
                telemetry.addData("setpoint", setpoint);
                telemetry.addData("enc", prbot.getDrawbridge().getPulleyBoi().getCurrentPosition());
                telemetry.update();
                prbot.getDrawbridge().getPulleyBoi().setPower(pid.output(setpoint, prbot.getDrawbridge().getPulleyBoi().getCurrentPosition()));
            }
            while (elapsedTime.milliseconds() < 10000) {
                prbot.getDrawbridge().getPulleyBoi().setPower(pid.output(setpoint, prbot.getDrawbridge().getPulleyBoi().getCurrentPosition()));
            }
            while (prbot.getDrawbridge().getPulleyBoi().getCurrentPosition() > 0) {
                if (setpoint > 0) {
                    setpoint--;
                }
                telemetry.addData("setpoint", setpoint);
                telemetry.addData("enc", prbot.getDrawbridge().getPulleyBoi().getCurrentPosition());
                telemetry.update();
                prbot.getDrawbridge().getPulleyBoi().setPower(pid.output(setpoint, prbot.getDrawbridge().getPulleyBoi().getCurrentPosition()));
            }
            break;
        }
    }
}
