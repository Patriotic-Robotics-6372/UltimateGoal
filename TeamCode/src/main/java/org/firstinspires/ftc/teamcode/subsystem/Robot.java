package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Date: 1/28/21
 * Author: Jacob Marinas
 * The robot container class
 */
public class Robot implements Constants {

    private Drivetrain drivetrain;
    private Drawbridge drawbridge;
    private WobbleGoal wobbleGoal;
    private Intake intake;
    private Outtake outtake;
    private IMU imu;
    private Telem telem;

    public void init(HardwareMap hwMap, Telemetry telemetry) {
        drivetrain = new Drivetrain(hwMap.dcMotor.get("frontLeft"), hwMap.dcMotor.get("frontRight"), hwMap.dcMotor.get("backLeft"), hwMap.dcMotor.get("backRight"));
        drawbridge = new Drawbridge(hwMap.dcMotor.get("pulleyBoi"));
        wobbleGoal = new WobbleGoal(hwMap.dcMotor.get("wobbleLift"), hwMap.crservo.get("wobbleGrabber"), hwMap.crservo.get("wobbleGrabber2"));
        //intake = new Intake(hwMap.dcMotor.get("roller"));
        outtake = new Outtake(hwMap.dcMotor.get("leftFlywheel"), hwMap.dcMotor.get("rightFlywheel"), hwMap.crservo.get("positioner"));
        imu = new IMU(hwMap.get(BNO055IMU.class, "imu 1"));
        telem = new Telem(this, telemetry);
        drivetrain.setTelemetry(telemetry);
    }

    public void setMode(Status mode) {
        switch (mode) {
            case NORMAL:
                drivetrain.setPower(1);
                drivetrain.useBrake(true);
                drivetrain.useEncoders(true);
                drawbridge.setPower(.8);
                wobbleGoal.setLiftPower(.8);
                wobbleGoal.setGrabberPower(1);
                //intake.setPower(.5);
                outtake.setPower(1);
                break;
            case AUTO:
                drivetrain.setPower(1);
                drivetrain.useBrake(true);
                drivetrain.useEncoders(true);
                drawbridge.setPower(.8);
                wobbleGoal.setLiftPower(.8);
                wobbleGoal.setGrabberPower(1);
                intake.setPower(.5);
                outtake.setPower(1);
                break;
        }
    }

    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    public Drawbridge getDrawbridge() {
        return drawbridge;
    }

    public WobbleGoal getWobbleGoal() {
        return wobbleGoal;
    }

    public Intake getIntake() {
        return intake;
    }

    public Outtake getOuttake() {
        return outtake;
    }

    public IMU getImu() {
        return imu;
    }

    public Telem getTelem() {
        return telem;
    }
}
