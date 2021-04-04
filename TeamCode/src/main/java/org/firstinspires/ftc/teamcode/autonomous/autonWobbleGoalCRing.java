package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.PIDController3;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@Autonomous (name = "autonWobbleGoalCRing", group = "primary")
public class autonWobbleGoalCRing extends LinearOpMode {

    Robot prbot = new Robot();
    ElapsedTime elapsedTime;
    PIDController3 pid;
    int setpoint;

    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.getDrivetrain().setTelemetry(telemetry);
        prbot.setMode(Constants.Status.AUTO);
        prbot.getWobbleGoal().setLiftPower(.3);
        prbot.getDrivetrain().setPower(.5);
        telemetry.addData("How to Use", "Right tape, aligned with left wheels");
        telemetry.update();
        prbot.getWobbleGoal().grab();
        pid = new PIDController3(0.007, 0.000035, 0.0007, 20);
        waitForStart();
        while (opModeIsActive()) {
            elapsedTime = new ElapsedTime();
            prbot.getDrawbridge().getPulleyBoi().setPower(0.5);
            sleep(200);
            prbot.getDrawbridge().getPulleyBoi().setPower(0.05);
            prbot.getDrivetrain().backward(100);
            prbot.getWobbleGoal().letGo();
            sleep(200);
            prbot.getWobbleGoal().stopServos();
            prbot.getWobbleGoal().down();
            sleep(700);
            prbot.getWobbleGoal().stopMotor();
            prbot.getDrivetrain().forward(3);
            prbot.getDrivetrain().stop();
            sleep(500);
            prbot.getDrivetrain().pointTurnRight();
            prbot.getDrivetrain().stop();
            sleep(500);
            prbot.getDrivetrain().forward(24);
            prbot.getDrivetrain().pointTurnRight();
            prbot.getDrivetrain().stop();
            sleep(500);
            prbot.getDrivetrain().forward(20);
            sleep(1000);
            while (prbot.getDrawbridge().getPulleyBoi().getCurrentPosition() < Constants.SHOOT_UP_SETPOINT) {
                if (setpoint < Constants.SHOOT_UP_SETPOINT) {
                    setpoint++;
                }
                telemetry.addData("setpoint", setpoint);
                telemetry.addData("enc", prbot.getDrawbridge().getPulleyBoi().getCurrentPosition());
                telemetry.update();
                prbot.getDrawbridge().getPulleyBoi().setPower(pid.output(setpoint, prbot.getDrawbridge().getPulleyBoi().getCurrentPosition()));
            }
//            while (prbot.getDrawbridge().getPulleyBoi().getCurrentPosition() < setpoint) {
//                prbot.getDrawbridge().getPulleyBoi().setPower(pid.output(setpoint + 100, prbot.getDrawbridge().getPulleyBoi().getCurrentPosition()));
//            }
            while (elapsedTime.milliseconds() < 15000) {
                prbot.getDrawbridge().getPulleyBoi().setPower(pid.output(setpoint, prbot.getDrawbridge().getPulleyBoi().getCurrentPosition()));
            }
            prbot.getOuttake().shoot();
            while (elapsedTime.milliseconds() < 20000) {
                prbot.getDrawbridge().getPulleyBoi().setPower(pid.output(setpoint, prbot.getDrawbridge().getPulleyBoi().getCurrentPosition()));
            }
            prbot.getOuttake().stop();
            while (elapsedTime.milliseconds() < 25000) {
                if (setpoint > 0) {
                    setpoint--;
                }
                telemetry.addData("setpoint", setpoint);
                telemetry.addData("enc", prbot.getDrawbridge().getPulleyBoi().getCurrentPosition());
                telemetry.update();
                prbot.getDrawbridge().getPulleyBoi().setPower(pid.output(setpoint, prbot.getDrawbridge().getPulleyBoi().getCurrentPosition()));
            }
            prbot.getDrivetrain().backward(40);
            prbot.getDrivetrain().stop();
            break;
        }
    }
}
