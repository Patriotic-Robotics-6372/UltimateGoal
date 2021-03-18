package org.firstinspires.ftc.teamcode.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Date: 1/29/21
 * @author Jacob Marinas
 * The Telem telemetry helper class
 */
public class Telem {

    private Robot robot;
    private Drivetrain drivetrain;
    private Drawbridge drawbridge;
    private WobbleGoal wobbleGoal;
    private Intake intake;
    private Outtake outtake;
    private IMU imu;
    private Telemetry telem;

    public Telem(Robot robot, Telemetry telemetry) {
        this.robot = robot;
        drivetrain = robot.getDrivetrain();
        drawbridge = robot.getDrawbridge();
        wobbleGoal = robot.getWobbleGoal();
        intake = robot.getIntake();
        outtake = robot.getOuttake();
        imu = robot.getImu();
        telem = telemetry;
    }

    public Telem(Drivetrain drivetrain, Telemetry telemetry) {
        this.drivetrain = drivetrain;
        this.telem = telemetry;
    }

    public Telem(Drawbridge drawbridge, Telemetry telemetry) {
        this.drawbridge = drawbridge;
        this.telem = telemetry;
    }

    public Telem(WobbleGoal wobbleGoal, Telemetry telemetry) {
        this.wobbleGoal = wobbleGoal;
        this.telem = telemetry;
    }

    public Telem(Intake intake, Telemetry telemetry) {
        this.intake = intake;
        this.telem = telemetry;
    }

    public Telem(Outtake outtake, Telemetry telemetry) {
        this.outtake = outtake;
        this.telem = telemetry;
    }

    public Telem(IMU imu, Telemetry telemetry) {
        this.imu = imu;
        this.telem = telemetry;
    }

    public void addDrivetrain() {
        //telem.addData("drivetrainStatus", drivetrain.getStatus());
        //telem.addData("speedPercentage", drivetrain.getSpeedPercentage());
        telem.addData("frontLeft", drivetrain.getFrontLeft().getPower());
        telem.addData("frontRight", drivetrain.getFrontRight().getPower());
        telem.addData("backLeft", drivetrain.getBackLeft().getPower());
        telem.addData("backRight", drivetrain.getBackRight().getPower());
    }

    public void addDrawbridge() {
        telem.addData("pulleyBoi", drawbridge.getPulleyBoi().getPower());
        telem.addData("pB enc", drawbridge.getPulleyBoi().getCurrentPosition());
    }

    public void addWobbleGoal() {
        telem.addData("wobbleLift", wobbleGoal.getWobbleLift().getPower());
        telem.addData("wobbleGrabber1", wobbleGoal.getWobbleGrabber().getPower());
        telem.addData("wobbleGrabber2", wobbleGoal.getWobbleGrabber2().getPower());
    }

    public void addIntake() {
        telem.addData("roller", intake.getRoller().getPower());
    }

    public void addOuttake() {
        telem.addData("shoot", outtake.getShoot().getPower());
        telem.addData("positioner", outtake.getPower());
        //telem.addData("positioner", outtake.getPositioner().getPosition() * 180);
    }

    public void addImu() {
        imu.updateAngles();
        telem.addLine()
                .addData("Heading", imu.getFirstAngle())
                .addData("Roll", imu.getSecondAngle())
                .addData("Pitch", imu.getThirdAngle());
    }

    public void addRobot() {
        addDrivetrain();
        addDrawbridge();
        addWobbleGoal();
        addIntake();
        addOuttake();
    }

    public void update() {
        telem.update();
    }
}
