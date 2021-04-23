package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;

public class ScoopCR {

    private CRServo leftScoop, rightScoop;
    private double power;

    public ScoopCR(CRServo lS, CRServo rS) {
        leftScoop = lS;
        rightScoop = rS;
    }

    public void scoopUp() {
        leftScoop.setPower(power);
        rightScoop.setPower(-power);
    }

    public void scoopDown() {
        leftScoop.setPower(-power);
        rightScoop.setPower(power);
    }

    public void stop() {
        leftScoop.setPower(0);
        rightScoop.setPower(0);
    }

    public void setPower(double p) {
        power = p;
    }

    public CRServo getLeftScoop() {
        return leftScoop;
    }

    public CRServo getRightScoop() {
        return rightScoop;
    }

    public double getPower() {
        return power;
    }
}
