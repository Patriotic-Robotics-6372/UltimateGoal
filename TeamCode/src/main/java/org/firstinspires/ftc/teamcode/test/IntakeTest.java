package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Telem;

@TeleOp (name = "IntakeTest", group = "Test")
public class IntakeTest extends LinearOpMode {

    Intake intake;
    Telem telem;

    @Override
    public void runOpMode() throws InterruptedException {
        intake = new Intake(hardwareMap.dcMotor.get("roller"));
        telem = new Telem(intake, telemetry);
        intake.setPower(.8);
        telemetry.addData("Desc", "This programs tests for the Intake class")
                .addData("How to Use", "right trigger -> inward, left trigger -> outward");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.right_trigger > .1) {
                intake.rollIn();
            } else if (gamepad1.left_trigger > .1) {
                intake.rollOut();
            } else {
                intake.stop();
            }
            telem.addIntake();
            telem.update();
        }
    }
}
