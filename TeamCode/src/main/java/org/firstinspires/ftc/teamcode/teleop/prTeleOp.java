package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@TeleOp (name = "prTeleOp", group = "a")
public class prTeleOp extends LinearOpMode {

    Robot prbot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.NORMAL);
        telemetry.addData("Desc", "This is the official teleOp class")
                .addData("Drivetrain", "Tank drive. Sticks for basic movement. Buttons for encoder movement")
                .addData("Drawbridge", "Dpad Up to go up, Dpad Down to go down")
                .addData("WobbleGoal", "x/a for up/down lift. y/b to grab/let go")
                .addData("Intake", "right trigger -> inward, left trigger -> outward")
                .addData("Outtake", "Right bumper to shoot. Left bumper to push ring out/let go leftBumper to let ring in");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
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
                prbot.getDrawbridge().up();
            } else if (gamepad1.dpad_down) {
                prbot.getDrawbridge().down();
            } else {
                prbot.getDrawbridge().stop();
            }
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
            if (gamepad1.right_trigger > .1) {
                prbot.getIntake().rollIn();
            } else if (gamepad1.left_trigger > .1) {
                prbot.getIntake().rollOut();
            } else {
                prbot.getIntake().stop();
            }
            // Outtake subsystem
            if (gamepad1.right_bumper) {
                prbot.getOuttake().shoot();
            } else {
                prbot.getOuttake().stop();
            }
            if (gamepad1.left_bumper) {
                prbot.getOuttake().positioner();
            } else {
                prbot.getOuttake().posReset();
            }
        }
    }
}
