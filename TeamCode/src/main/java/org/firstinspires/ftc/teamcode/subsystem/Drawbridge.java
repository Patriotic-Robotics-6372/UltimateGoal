package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Date: 1/28/21
 * Author: Jacob Marinas
 * The drawbridge subsystem
 */
public class Drawbridge implements Constants {

    private DcMotor pulleyBoi;
    private double power;

    /**
     * Defines the parts needed for the subsystem
     * @param pB
     */
    public Drawbridge(DcMotor pB) {
        this.pulleyBoi = pB;
    }

    /**
     * Brings the drawbridge mechanism up
     */
    public void up() {
        pulleyBoi.setPower(-power);
    }

    /**
     * Brings the drawbridge mechanism down
     */
    public void down() {
        pulleyBoi.setPower(power);
    }

    /**
     * Stops the drawbridge mchanism
     */
    public void stop() {
        pulleyBoi.setPower(STOP);
    }

    /**
     * @param power that the drawbridge will operate by
     */
    public void setPower(double power) {
        this.power = power;
    }

    /**
     * @return power that the drawbridge operates by
     */
    public double getPower() {
        return power;
    }

    /**
     * Increase power variable
     */
    public void increasePower() {
        power += .1;
        checkPower();
    }

    /**
     * Decrease power variable
     */
    public void decreasePower() {
        power -= .1;
        checkPower();
    }

    /**
     * Helper method to keep power variable between 0 - 1
     */
    private void checkPower() {
        if (power > 1) {
            power = 1;
        } else if (power < 0) {
            power = 0;
        }
    }

    public DcMotor getPulleyBoi() {
        return pulleyBoi;
    }
}
