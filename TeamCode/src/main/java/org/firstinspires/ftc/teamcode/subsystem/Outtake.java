package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Date: 10/28/2020
 * Author: Joseph Campos
 * Outtake Subsystem
 */
public class Outtake implements Constants {

    private DcMotor leftFlywheel, rightFlywheel;
    private CRServo positioner;
    private double power;

    /**
     * sets up the subsystem
     * @param l
     * @param r
     * @param ps
     */
    public Outtake(DcMotor l, DcMotor r, CRServo ps){
        leftFlywheel = l;
        rightFlywheel = r;
        positioner = ps;
    }

    /**
     * A set power for the shooter motors
     */
    public void shoot() {
        leftFlywheel.setPower(power);
        rightFlywheel.setPower(-power);
    }

    /**
     * Reverese power for the shooter motors
     */
    public void reverse() {
        leftFlywheel.setPower(-power * 0.5);
        rightFlywheel.setPower(power * 0.5);
    }

    /**
     * Makes the shooter motor stop
     */
    public void stop(){
        leftFlywheel.setPower(STOP);
        rightFlywheel.setPower(STOP);
    }

    /**
     * sets the posistioner to the right place
     */
    public void positioner(){
        positioner.setPower(.4);
    }

    /**
     * Puts the positioner back to the beginning place for the next ring
     */

    public void posReset(){
        positioner.setPower(-.4);
    }

    public void setPower(double power) {
        this.power = power;
    }

    //public DcMotor getShoot() { return shoot; }

    public DcMotor getLeftFlywheel() { return leftFlywheel; }

    public DcMotor getRightFlywheel() { return rightFlywheel; }

    public CRServo getPositioner() { return positioner; }

    public double getPower() {
        return power;
    }
}
