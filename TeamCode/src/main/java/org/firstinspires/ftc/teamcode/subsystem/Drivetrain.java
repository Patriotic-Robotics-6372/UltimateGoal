package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Date: 1/28/21
 * Author: Jacob Marinas
 * The drivetrain subsystem
 */
public class Drivetrain implements Constants {

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private double power;
    public int fLTickGoal, fRTickGoal, bLTickGoal, bRTickGoal;

    /**
     * Defines the parts needed for the subsystem
     * @param fL
     * @param fR
     * @param bL
     * @param bR
     */
    public Drivetrain(DcMotor fL, DcMotor fR, DcMotor bL, DcMotor bR) {
        this.frontLeft = fL;
        this.frontRight = fR;
        this.backLeft = bL;
        this.backRight = bR;

        init();
    }

    /**
     * Sets the direction of the motors and sets their power to 0
     */
    private void init() {
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        power = STOP;
    }

    /**
     * @param use RUN_USING_ENCODER
     */
    public void useEncoders(boolean use) {
        if (use) {
            setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    /**
     * @param use ZeroPowerBehavior.BRAKE
     */
    public void useBrake(boolean use) {
        if (use) {
            setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    /**
     * @param zpb for all motors
     */
    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zpb) {
        frontLeft.setZeroPowerBehavior(zpb);
        frontRight.setZeroPowerBehavior(zpb);
        backLeft.setZeroPowerBehavior(zpb);
        backRight.setZeroPowerBehavior(zpb);
    }

    /**
     * @param runMode for all motors
     */
    private void setRunMode(DcMotor.RunMode runMode) {
        frontLeft.setMode(runMode);
        frontRight.setMode(runMode);
        backLeft.setMode(runMode);
        backRight.setMode(runMode);
    }

    /**
     * @param fL target pos
     * @param fR target pos
     * @param bL target pos
     * @param bR target pos
     */
    private void setTargetPos(int fL, int fR, int bL, int bR) {
        frontLeft.setTargetPosition(fL);
        frontRight.setTargetPosition(fR);
        backLeft.setTargetPosition(bL);
        backRight.setTargetPosition(bR);
    }

    /**
     * @param power that the drivetrain will operate at
     */
    public void setPower(double power) { this.power = power; }

    /**
     * @param fL power
     * @param bL power
     */
    public void setLeftSide(double fL, double bL) {
        frontLeft.setPower(fL);
        backLeft.setPower(bL);
    }

    /**
     * @param fR power
     * @param bR power
     */
    public void setRightSide(double fR, double bR) {
        frontRight.setPower(fR);
        backRight.setPower(bR);
    }

    /**
     * @param fL power
     * @param fR power
     * @param bL power
     * @param bR power
     */
    public void setBase(double fL, double fR, double bL, double bR) {
        setLeftSide(fL, bL);
        setRightSide(fR, bR);
    }

    /**
     * @param power
     */
    public void setBase(double power) {
        setBase(power, power, power, power);
    }

    /**
     * Basic forward movement by power variable
     */
    public void forward() {
        setBase(-power, -power, -power, -power);
    }

    /**
     * Basic backward movement by power variable
     */
    public void backward() {
        setBase(power, power, power, power);
    }

    /**
     * Sets all motors to 0
     */
    public void stop() {
        setBase(STOP, STOP, STOP, STOP);
    }

    /**
     * Goes forward x inches by power variable
     * @param inches distance
     */
    public void forward(double inches) {
        drive(-inches, -inches, -inches, -inches, power, power, power, power);
    }

    /**
     * Goes backward x inches by power variable
     * @param inches distance
     */
    public void backward(double inches) {
        drive(inches, inches, inches, inches, power, power, power, power);
    }

    /**
     * Pivot turn in a certain distance by power variable
     * @param dir direction to turn
     * @param inches distance
     */
    public void pivotTurn(Status dir, double inches) {
        switch (dir) {
            case LEFT:
                drive(0, -inches, 0, -inches, power, power, power, power);
                break;
            case RIGHT:
                drive(-inches, 0, -inches, 0, power, power, power, power);
                break;
        }
    }

    /**
     * Point turn in a certain distance by power variable
     * @param dir direction to turn
     * @param inches distance
     */
    public void pointTurn(Status dir, double inches) {
        switch (dir) {
            case LEFT:
                drive(inches, -inches, inches, -inches, power, power, power, power);
                break;
            case RIGHT:
                drive(-inches, inches, -inches, inches, power, power, power, power);
                break;
        }
    }

    /**
     * Basic point turn by power variable
     * @param dir direction to turn
     */
    public void pointTurn(Status dir) {
        switch (dir) {
            case LEFT:
                setBase(0, -power, 0, -power);
                break;
            case RIGHT:
                setBase(-power, 0, -power, 0);
                break;
        }
    }

    /**
     * Basic pivot turn by power variable
     * @param dir direction to turn
     */
    public void pivotTurn(Constants.Status dir) {
        switch (dir) {
            case LEFT:
                setBase(power, -power, power, -power);
                break;
            case RIGHT:
                setBase(-power, power, -power, power);
                break;
        }
    }

    /**
     * Encoder method for movement. Inches determine the distance for each motor. Power determines how fast the motors will go
     * @param fLInches
     * @param fRInches
     * @param bLInches
     * @param bRInches
     * @param fLPower
     * @param fRPower
     * @param bLPower
     * @param bRPower
     */
    public void drive(double fLInches, double fRInches, double bLInches, double bRInches, double fLPower, double fRPower, double bLPower, double bRPower) {
        fLTickGoal = (int) (fLInches * TICKS_PER_IN);
        fRTickGoal = (int) (fRInches * TICKS_PER_IN);
        bLTickGoal = (int) (bLInches * TICKS_PER_IN);
        bRTickGoal = (int) (bRInches * TICKS_PER_IN);
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setTargetPos(fLTickGoal, fRTickGoal, bLTickGoal, bRTickGoal);
        setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
        setBase(fLPower, fRPower, bLPower, bRPower);
        while (allBusy()) {

        }
        stop();
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * @return true if all motors are busy, false otherwise
     */
    private boolean allBusy() { return frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy(); }

    /**
     * @return true if any motor is busy, false otherwise
     */
    private boolean anyBusy() { return frontLeft.isBusy() || frontRight.isBusy() || backLeft.isBusy() || backRight.isBusy();}

    /**
     * @return power variable that drivetrain moves by
     */
    public double getPower() { return power; }

    public DcMotor getFrontLeft() { return frontLeft; }

    public DcMotor getFrontRight() { return frontRight; }

    public DcMotor getBackLeft() { return backLeft; }

    public DcMotor getBackRight() { return backRight; }
}
