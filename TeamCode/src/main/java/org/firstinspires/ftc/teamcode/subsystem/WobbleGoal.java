package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Date: 1/28/2021
 * Author: Joseph Campos
 * Wobble Goal subsystem
 */
public class WobbleGoal implements Constants {
    // Characteristics
    private DcMotor wobbleLift;
    private CRServo wobbleGrabber,wobbleGrabber2;
    private double liftPower;
    private double grabberPower;
    // Constructor

    /**
     * Sets up the subsystem
     * @param WL
     * @param WG1
     * @param WG2
     */
    public WobbleGoal(DcMotor WL, CRServo WG1, CRServo WG2){
        wobbleLift = WL;
        wobbleGrabber = WG1;
        wobbleGrabber2 = WG2;
    }

    /**
     * By having a positive power the lift should pull the drawslide up
     */
    public void up(){
        wobbleLift.setPower(liftPower);
    }

    /**
     * By having a negative power the lift should make the drawslide go down
     */
    public void down(){
        wobbleLift.setPower(-liftPower);
    }

    /**
     * By setting the power to zero the lift will stop
     */
    public void stopMotor(){
        wobbleLift.setPower(STOP);
    }

    /**
     * Telling the grabbing mechanism to close onto the wobble goal
     */

    public void grab(){
        wobbleGrabber.setPower(-grabberPower);
        wobbleGrabber2.setPower(grabberPower);
    }

    /**
     * Telling the grabbing mechanism to let go of the wobble goal
     */

    public void letGo(){
        wobbleGrabber.setPower(grabberPower);
        wobbleGrabber2.setPower(-grabberPower);
    }

    /**
     * Stoping the servos
     */
    public void stopServos(){
        wobbleGrabber.setPower(STOP);
        wobbleGrabber2.setPower(STOP);
    }

    public void setGrabberPower(double power) {
        this.grabberPower = power;
    }

    public double getGrabberPower() {
        return grabberPower;
    }

    public void setLiftPower(double power) {
        this.liftPower = power;
    }

    public double getLiftPower() {
        return liftPower;
    }

    public DcMotor getWobbleLift() { return wobbleLift; }

    public CRServo getWobbleGrabber() { return wobbleGrabber; }

    public CRServo getWobbleGrabber2() { return wobbleGrabber2; }
}
