package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.IMU;
import org.firstinspires.ftc.teamcode.subsystem.Telem;

@TeleOp (name = "IMUTest", group = "Test")
public class IMUTest extends LinearOpMode {

    IMU imu;
    Telem telem;

    @Override
    public void runOpMode() throws InterruptedException {
        imu = new IMU(hardwareMap.get(BNO055IMU.class, "imu"));
        telem = new Telem(imu, telemetry);
        telemetry.addData("Desc", "This programs tests for the IMU class")
                .addData("How to Use", "Values update as the robot is moved/rotated");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            telem.addImu();
            telem.update();
        }
    }
}
