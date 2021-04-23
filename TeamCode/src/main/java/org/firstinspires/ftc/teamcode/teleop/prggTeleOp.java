package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Robot2;

@TeleOp(name = "ppggTeleOp", group = "a")
public class prggTeleOp extends LinearOpMode {

    public Robot2 prbot = new Robot2();
    public double rightSlide, leftSlide, rightMove, leftMove;
    public double leftDriveSpeed = 0.5, rightDriveSpeed = 0.5;
    public boolean rightToggle, leftToggle;
    public int grabState = 2;

    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.NORMAL);
        telemetry.addData("Desc", "Teleop")
                .addData("Drivetrain", "Tank drive. Sticks for basic movement. Buttons for encoder movement")
                .addData("WobbleGoal", "dpad up/down for up/down lift. right/left to grab/let go")
                .addData("Scoop", "y/b to scoop up/down")
                .addData("Belt", "x/a to go forwards/backwards")
                .addData("Flywheel", "r/l bumper to shoot/reverese");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            // Drivetrain
            rightSlide = -gamepad1.right_stick_x;
            leftSlide = -gamepad1.left_stick_x;
            rightMove = -gamepad1.right_stick_y;
            leftMove = -gamepad1.left_stick_y;
            prbot.getDrivetrain().setRightSide((rightSlide + rightMove) * rightDriveSpeed, (-rightSlide + rightMove) * rightDriveSpeed);
            prbot.getDrivetrain().setLeftSide((-leftSlide + leftMove) * leftDriveSpeed, (leftSlide + leftMove) * leftDriveSpeed);
            telemetry.addData("rightDriveSpeed", rightDriveSpeed);
            telemetry.addData("leftDriveSpeed", leftDriveSpeed);
            if (gamepad1.left_stick_button) {
                prbot.getDrivetrain().setPower(.5);
            } else if (gamepad1.right_stick_button) {
                prbot.getDrivetrain().setPower(1);
            }
            // WobbleGoal subsystem
            if (gamepad2.dpad_right) {
                grabState = 0;
            } else if (gamepad2.dpad_left) {
                grabState = 1;
            } else if (gamepad2.left_stick_button){
                grabState = 2;
            }
            if (grabState == 0) {
                prbot.getWobbleGoal().grab();
            } else if (grabState == 1) {
                prbot.getWobbleGoal().letGo();
            } else if (grabState == 2) {
                prbot.getWobbleGoal().stopServos();
            }
            if (gamepad2.dpad_up) {
                prbot.getWobbleGoal().up();
            } else if (gamepad2.dpad_down) {
                prbot.getWobbleGoal().down();
            } else {
                prbot.getWobbleGoal().stopMotor();
            }
            // Scoop
            if (gamepad1.y) {
                prbot.getScoop().scoopUp();
            } else if (gamepad1.b) {
                prbot.getScoop().scoopDown();
            } else {
                prbot.getScoop().stop();
            }
            // Belt
            if (gamepad2.x) {
                prbot.getBelt().forward();
            } else if (gamepad2.a) {
                prbot.getBelt().backward();
            } else {
                prbot.getBelt().stop();
            }
            // Flywheel
            if (gamepad2.right_bumper) {
                prbot.getFlywheel().shoot();
            } else if (gamepad2.left_bumper) {
                prbot.getFlywheel().reverse();
            } else {
                prbot.getFlywheel().stop();
            }
        }
    }
}
