package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Button;
import org.firstinspires.ftc.teamcode.subsystem.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.PIDController;
import org.firstinspires.ftc.teamcode.subsystem.PIDControllerAlt;
import org.firstinspires.ftc.teamcode.subsystem.Telem;

//@Disabled
@TeleOp (name = "PIDTest", group = "Test")
public class PIDTest extends LinearOpMode {

    Drivetrain drive;
    Telem telem;
    PIDControllerAlt movePID;
    Button y, dpad_up, dpad_down;
    double calculatedMovePIDValue, correction, power = 0.8, setpoint = 0;
    boolean isMoving, toggle = false;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(hardwareMap.dcMotor.get("frontLeft"),
                hardwareMap.dcMotor.get("frontRight"),
                hardwareMap.dcMotor.get("backLeft"),
                hardwareMap.dcMotor.get("backRight"));
        telem = new Telem(drive, telemetry);
        movePID = new PIDControllerAlt(0.01, 0, 0);
        y = new Button();
        dpad_down = new Button();
        dpad_up = new Button();
        telemetry.addData("Desc", "This programs tests a basic PIDController for moving forward with the drivetrain")
                .addData("How to Use", "Y to toggle PID. X to stop. Try pushing the robot; it should be able to readjust itself");
        telemetry.update();
        waitForStart();
        movePID.setSetpoint(0);
        movePID.setOutputRange(0, power);
        movePID.setInputRange(-1000, 1000);
        movePID.enable();
        while (opModeIsActive()) {
//            if (gamepad1.x) {
//                isMoving = true;
//            }
//            if (isMoving) {
//                movePID.calculate(drive.getBackLeft().getCurrentPosition());
//                calculatedMovePIDValue = movePID.getOutput();
//                calculatedMovePIDValue = Math.max(-.5, Math.min(.5, calculatedMovePIDValue));
//                drive.setBase(calculatedMovePIDValue);
//
//                if (movePID.atSetpoint()) {
//                    isMoving = false;
//                }
            y.previous();
            y.setState(gamepad1.y);
            dpad_down.previous();
            dpad_down.setState(gamepad1.dpad_down);
            dpad_up.previous();
            dpad_up.setState(gamepad1.dpad_up);
            if (y.isPressed()) {
                toggle = !toggle;
            }
            if (dpad_up.isPressed()) {
                setpoint += 10;
            }
            if (dpad_down.isPressed()) {
                setpoint -= 10;
            }
            if (toggle) {
                correction = movePID.performPID(drive.getBackLeft().getCurrentPosition()+setpoint);
                drive.setBase(power + correction);
            }

            if (gamepad1.x) {
                drive.stop();
            }
            telemetry.addData("correction", correction);
            telemetry.addData("power", power + correction);
            telemetry.addData("encoder", drive.getBackLeft().getCurrentPosition());
            telemetry.addData("toggle", toggle);
            telemetry.addData("setpoint", setpoint);
            telemetry.update();

        }
    }
}
