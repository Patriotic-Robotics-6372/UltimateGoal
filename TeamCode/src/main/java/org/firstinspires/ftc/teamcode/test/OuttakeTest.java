package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.Telem;

@TeleOp (name = "OuttakeTest", group = "Test")
public class OuttakeTest extends LinearOpMode {

    Outtake outtake;
    Telem telem;

    @Override
    public void runOpMode() throws InterruptedException {
        outtake = new Outtake(hardwareMap.dcMotor.get("shoot"), hardwareMap.crservo.get("positioner"));
        telem = new Telem(outtake, telemetry);
        outtake.setPower(.5);
        telemetry.addData("Desc", "This programs tests for the Outtake class")
                .addData("How to Use", "Right bumper to shoot. Left bumper to push ring out/let go B to let ring in");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.right_bumper) {
                outtake.shoot();
            } else {
                outtake.stop();
            }
            if (gamepad1.left_bumper) {
                outtake.positioner();
            } else {
                outtake.posReset();
            }
            telem.addOuttake();
            telem.update();
        }
    }
}
