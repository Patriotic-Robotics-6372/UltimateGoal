package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.ScoopCR;

@TeleOp (name = "ScoopCRTest", group = "Test")
public class ScoopCRTest extends LinearOpMode {

    public ScoopCR scoop;

    @Override
    public void runOpMode() throws InterruptedException {
        scoop = new ScoopCR(hardwareMap.crservo.get("leftScoop"), hardwareMap.crservo.get("rightScoop"));
        telemetry.addData("Desc", "Test the scoop with continuous servos")
                .addData("How to Use", "y/b to go scoop up/down, dpad up/down to increase/decreaes pos");
        telemetry.update();
        scoop.setPower(1);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.y) {
                scoop.scoopUp();
                sleep(1500);
                scoop.scoopDown();
                sleep(300);
            } else if (gamepad1.b) {
                scoop.scoopDown();
                sleep(1800);
            } else {
                scoop.stop();
            }
            telemetry.setMsTransmissionInterval(20);
            telemetry.addData("scoop power", scoop.getPower())
                    .addData("leftScoop power", scoop.getLeftScoop().getPower())
                    .addData("rightScoop power", scoop.getRightScoop().getPower());
            telemetry.addData("gamepad", gamepad1.toString());
            telemetry.update();
//            scoop.scoopUp();
//            sleep(3000);
//            scoop.scoopDown();
//            sleep(3000);
//            scoop.stop();
//            sleep(3000);
        }
    }
}
