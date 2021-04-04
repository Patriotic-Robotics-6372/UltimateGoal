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
    private CRServo wobbleCRS;
    private Servo wobbleS;
    private double liftPower;
    private double grabberPower;
    // Constructor

    /**
     * Sets up the subsystem
     * @param WL
     * @param WG1
     * @param WG2
     */
    public WobbleGoal(DcMotor WL, CRServo WG1, Servo WG2){
        wobbleLift = WL;
        wobbleCRS = WG1;
        wobbleS = WG2;
    }

    public void useEncoder() {
        wobbleLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wobbleLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    /**
     * By having a positive power the lift should pull the drawslide up
     */
    public void up(){
        wobbleLift.setPower(-liftPower);
    }

    /**
     * By having a negative power the lift should make the drawslide go down
     */
    public void down(){
        if (wobbleLift.getCurrentPosition() > LIFT_THRESHOLD) {
            wobbleLift.setPower(liftPower);
        } else {
            wobbleLift.setPower(STOP);
        }
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
        wobbleCRS.setPower(-grabberPower);
        wobbleS.setPosition(0.1);
    }

    /**
     * Telling the grabbing mechanism to let go of the wobble goal
     */

    public void letGo(){
        wobbleCRS.setPower(grabberPower);
        wobbleS.setPosition(0.9);
    }

    /**
     * Stoping the servos
     */
    public void stopServos(){
        wobbleCRS.setPower(STOP);
        wobbleS.setPosition(0.5);
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

    public double getLiftPower() { return liftPower; }

    public DcMotor getWobbleLift() { return wobbleLift; }

    public CRServo getWobbleGrabber() { return wobbleCRS; }

    public Servo getWobbleGrabber2() { return wobbleS; }
}
