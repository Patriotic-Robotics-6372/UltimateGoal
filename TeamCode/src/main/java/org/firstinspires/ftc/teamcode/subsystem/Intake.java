package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Date: 1/28/21
 * Author: Jacob Marinas
 * The intake subsystem
 */
public class Intake implements Constants {

    private DcMotor roller;
    private double power;

    /**
     * Defines the parts needed for the subsystem
     * @param r
     */
    public Intake(DcMotor r) {
        this.roller = r;
    }

    /**
     * Intakes the rings
     */
    public void rollIn(){
        roller.setPower(-power);
    }

    /**
     * Spits out the rings from the intake
     */
    public void rollOut(){
        roller.setPower(power);
    }

    /**
     * Stops the intake
     */
    public void stop(){
        roller.setPower(STOP);
    }

    /**
     * @param power that the intake will operate by
     */
    public void setPower(double power) {
        this.power = power;
    }

    /**
     * @return power that the intake operates by
     */
    public double getPower() {
        return power;
    }

    public DcMotor getRoller() {
        return roller;
    }
}
