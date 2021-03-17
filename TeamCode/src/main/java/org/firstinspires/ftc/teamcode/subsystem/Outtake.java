package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Date: 10/28/2021
 * Author: Joseph Campos
 * Outtake Subsystem
 */
public class Outtake implements Constants {

    private DcMotor shoot;
    private CRServo positioner;
    private double power;

    /**
     * sets up the subsystem
     * @param sh
     * @param ps
     */
    public Outtake(DcMotor sh, CRServo ps){
        shoot = sh;
        positioner = ps;
    }

    /**
     * A set power for the shooter motor
     */
    public void shoot() {
        shoot.setPower(power);
    }

    public void reverse() {
        shoot.setPower(-power * 0.5);
    }

    /**
     * Makes the shooter motor stop
     */
    public void stop(){
        shoot.setPower(STOP);
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

    public DcMotor getShoot() { return shoot; }

    public CRServo getPositioner() { return positioner; }

    public double getPower() {
        return power;
    }
}
