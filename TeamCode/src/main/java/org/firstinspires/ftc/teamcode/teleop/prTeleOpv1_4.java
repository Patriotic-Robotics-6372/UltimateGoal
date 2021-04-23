package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Button;
import org.firstinspires.ftc.teamcode.subsystem.PIDController3;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

import static org.firstinspires.ftc.teamcode.subsystem.Constants.Status.NORMAL;

@Disabled
@TeleOp (name = "prTeleOp v1.4", group = "a")
public class prTeleOpv1_4 extends LinearOpMode {

    Robot prbot = new Robot();
    PIDController3 drawbridgePID;
    Button dpad_left, dpad_right, left_stick_button, right_stick_button;
    double setpoint = 0, rate = 1;
    public double rightSlide, leftSlide, rightMove, leftMove;
    public double leftDriveSpeed = 0.5, rightDriveSpeed = 0.5;
    public boolean rightToggle, leftToggle;
    public int grabState = 2;

    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(NORMAL);
        telemetry.addData("Desc", "This is the official teleOp class")
                .addData("Drivetrain", "Tank drive. Sticks for basic movement. Buttons for encoder movement")
                .addData("Drawbridge", "Dpad Up to go up, Dpad Down to go down")
                .addData("WobbleGoal", "dpad up/down for up/down lift. right/left to grab/let go")
                .addData("Intake", "right trigger -> inward, left trigger -> outward")
                .addData("Outtake", "Right bumper to shoot. Left bumper to push ring out/let go leftBumper to let ring in");
        telemetry.update();
        prbot.getDrawbridge().useEncoders();
        drawbridgePID = new PIDController3(0.007, 0.000035, 0.0007, 20);
        dpad_left = new Button();
        dpad_right = new Button();
        left_stick_button = new Button();
        right_stick_button = new Button();
        waitForStart();
        while (opModeIsActive()) {
            dpad_left.previous();
            dpad_left.setState(gamepad1.dpad_left);
            dpad_right.previous();
            dpad_right.setState(gamepad1.dpad_right);
            left_stick_button.previous();
            left_stick_button.setState(gamepad1.left_stick_button);
            right_stick_button.previous();
            right_stick_button.setState(gamepad1.right_stick_button);
            if (right_stick_button.isPressed()) {
                rightToggle = !rightToggle;
            }
            if (left_stick_button.isPressed()) {
                leftToggle = !leftToggle;
            }
//            if (rightToggle) {
//                rightDriveSpeed = 0.5;
//                leftDriveSpeed = 0.5;
//            } else {
//                rightDriveSpeed = 1;
//                leftDriveSpeed = 1;
//            }
//            if (leftToggle) {
//                leftDriveSpeed = 0.5;
//            } else {
//                leftDriveSpeed = 1;
//            }
            // Drivetrain subsystem
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
            // Drawbridge subsystem
            if (gamepad1.y) {
                setpoint += rate;
            } else if (gamepad1.a) {
                setpoint -= rate;
            }
            prbot.getDrawbridge().getPulleyBoi().setPower(drawbridgePID.output(setpoint, prbot.getDrawbridge().getPulleyBoi().getCurrentPosition()));
            // WobbleGoal subsystem
            if (gamepad1.dpad_right) {
                grabState = 0;
            } else if (gamepad1.dpad_left) {
                grabState = 1;
            } else if (gamepad1.left_stick_button){
                grabState = 2;
            }
            if (grabState == 0) {
                prbot.getWobbleGoal().grab();
            } else if (grabState == 1) {
                prbot.getWobbleGoal().letGo();
            } else if (grabState == 2) {
                prbot.getWobbleGoal().stopServos();
            }
            if (gamepad1.dpad_up) {
                prbot.getWobbleGoal().up();
            } else if (gamepad1.dpad_down) {
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
            telemetry.addData("setpoint", setpoint)
                    .addData("drawbridge enc", prbot.getDrawbridge().getPulleyBoi().getCurrentPosition())
                    .addData("Desc", "This is the official teleOp class")
                    .addData("grabState", grabState);
//                    .addData("Drivetrain", "Tank drive. Sti cks for basic movement. Buttons for encoder movement")
//                    .addData("Drawbridge", "Dpad Up to go up, Dpad Down to go down")
//                    .addData("WobbleGoal", "x/a for up/down lift. y/b to grab/let go")
//                    .addData("Intake", "right trigger -> inward, left trigger -> outward")
//                    .addData("Outtake", "Right bumper to shoot. Left bumper to push ring out/let go leftBumper to let ring in");
            telemetry.addData("gamepad", gamepad1.toString());
            telemetry.update();
        }
    }
}
