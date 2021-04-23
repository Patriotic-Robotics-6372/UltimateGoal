package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Robot 1 was so good that they made robot 2
 */
public class Robot2 {

    private Drivetrain drivetrain;
    private WobbleGoal wobbleGoal;
    //private Scoop scoop;
    private ScoopCR scoop;
    private Belt belt;
    private Flywheel flywheel;

    public void init(HardwareMap hwMap, Telemetry telem) {
        drivetrain = new Drivetrain(hwMap.dcMotor.get("frontLeft"), hwMap.dcMotor.get("frontRight"), hwMap.dcMotor.get("backLeft"), hwMap.dcMotor.get("backRight"));
        wobbleGoal = new WobbleGoal(hwMap.dcMotor.get("wobbleLift"), hwMap.crservo.get("wobbleGrabber"), hwMap.servo.get("wobbleGrabber2"));
        //scoop = new Scoop(hwMap.servo.get("leftScoop"), hwMap.servo.get("rightScoop"));
        scoop = new ScoopCR(hwMap.crservo.get("leftScoop"), hwMap.crservo.get("rightScoop"));
        belt = new Belt(hwMap.dcMotor.get("belt"));
        flywheel = new Flywheel(hwMap.dcMotor.get("flywheel"));
    }

    public void setMode(Constants.Status mode) {
        switch (mode) {
            case NORMAL:
                drivetrain.setPower(1);
                drivetrain.useBrake(true);
                drivetrain.useEncoders(true);
                wobbleGoal.setLiftPower(.8);
                wobbleGoal.setGrabberPower(1);
                //scoop.setPos(90);
                scoop.setPower(1);
                belt.setPower(1);
                flywheel.setPower(1);
                break;
            case AUTO:
                drivetrain.setPower(.5);
                drivetrain.useBrake(true);
                drivetrain.useEncoders(true);
                wobbleGoal.setLiftPower(.3);
                wobbleGoal.setGrabberPower(1);
                //scoop.setPos(90);
                scoop.setPower(1);
                belt.setPower(1);
                flywheel.setPower(1);
                break;
        }
    }

    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    public WobbleGoal getWobbleGoal() {
        return wobbleGoal;
    }

    /*public Scoop getScoop() {
        return scoop;
    }*/

    public ScoopCR getScoop() {
        return scoop;
    }

    public Belt getBelt() {
        return belt;
    }

    public Flywheel getFlywheel() {
        return flywheel;
    }
}
