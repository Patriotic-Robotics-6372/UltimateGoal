package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Button;
import org.firstinspires.ftc.teamcode.subsystem.PIDController3;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

import static org.firstinspires.ftc.teamcode.subsystem.Constants.Status.NORMAL;

@Disabled
@TeleOp (name = "prTeleOp v1.3", group = "a")
public class prTeleOpv1_3 extends LinearOpMode {

    Robot prbot = new Robot();
    PIDController3 drawbridgePID;
    Button dpad_left, dpad_right;
    double setpoint = 0, rate = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(NORMAL);
        telemetry.addData("Desc", "This is the official teleOp class")
                .addData("Drivetrain", "Tank drive. Sticks for basic movement. Buttons for encoder movement")
                .addData("Drawbridge", "Dpad Up to go up, Dpad Down to go down")
                .addData("WobbleGoal", "x/a for up/down lift. y/b to grab/let go")
                .addData("Intake", "right trigger -> inward, left trigger -> outward")
                .addData("Outtake", "Right bumper to shoot. Left bumper to push ring out/let go leftBumper to let ring in");
        telemetry.update();
        drawbridgePID = new PIDController3(0.007, 0.000035, 0.0007, 20);
        dpad_left = new Button();
        dpad_right = new Button();
        waitForStart();
        while (opModeIsActive()) {
            dpad_left.previous();
            dpad_left.setState(gamepad1.dpad_left);
            dpad_right.previous();
            dpad_right.setState(gamepad1.dpad_right);
            // Drivetrain subsystem
            if (Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_y) > .1) {
                prbot.getDrivetrain().setLeftSide(-gamepad1.left_stick_y, -gamepad1.left_stick_y);
                prbot.getDrivetrain().setRightSide(-gamepad1.right_stick_y, -gamepad1.right_stick_y);
            } else {
                prbot.getDrivetrain().stop();
            }
            if (gamepad1.left_stick_button) {
                prbot.getDrivetrain().setPower(.5);
            } else if (gamepad1.right_stick_button) {
                prbot.getDrivetrain().setPower(1);
            }
            // Drawbridge subsystem
            if (gamepad1.dpad_up) {
                setpoint += rate;
            } else if (gamepad1.dpad_down) {
                setpoint -= rate;
            }
            prbot.getDrawbridge().getPulleyBoi().setPower(drawbridgePID.output(setpoint, prbot.getDrawbridge().getPulleyBoi().getCurrentPosition()));
            // WobbleGoal subsystem
            if (gamepad1.y) {
                prbot.getWobbleGoal().grab();
            } else if (gamepad1.b) {
                prbot.getWobbleGoal().letGo();
            } else {
                prbot.getWobbleGoal().stopServos();
            }
            if (gamepad1.x) {
                prbot.getWobbleGoal().up();
            } else if (gamepad1.a) {
                prbot.getWobbleGoal().down();
            } else {
                prbot.getWobbleGoal().stopMotor();
            }
            // Intake subsystem
//            if (gamepad1.right_trigger > .1) {
//                prbot.getIntake().rollIn();
//            } else if (gamepad1.left_trigger > .1) {
//                prbot.getIntake().rollOut();
//            } else {
//                prbot.getIntake().stop();
//            }
            // Outtake subsystem
            if (gamepad1.right_bumper) {
                prbot.getOuttake().shoot();
            } else if (gamepad1.left_bumper) {
                prbot.getOuttake().reverse();
            } else {
                prbot.getOuttake().stop();
            }
            telemetry.addData("Desc", "This is the official teleOp class")
                    .addData("Drivetrain", "Tank drive. Sticks for basic movement. Buttons for encoder movement")
                    .addData("Drawbridge", "Dpad Up to go up, Dpad Down to go down")
                    .addData("WobbleGoal", "x/a for up/down lift. y/b to grab/let go")
                    .addData("Intake", "right trigger -> inward, left trigger -> outward")
                    .addData("Outtake", "Right bumper to shoot. Left bumper to push ring out/let go leftBumper to let ring in");
            telemetry.update();
        }
    }
}
