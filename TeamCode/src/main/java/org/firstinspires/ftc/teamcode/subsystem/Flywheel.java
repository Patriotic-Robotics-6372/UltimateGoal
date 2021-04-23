package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Flywheel {

    private DcMotor flywheel;
    private double power;

    public Flywheel(DcMotor fW) {
        flywheel = fW;
    }

    public void shoot() {
        flywheel.setPower(power);
    }

    public void reverse() {
        flywheel.setPower(-power);
    }

    public void stop() {
        flywheel.setPower(0);
    }
    public void setFlywheel(double power) {
        flywheel.setPower(power);
    }

    public void setPower(double p) {
        power = p;
    }

    public DcMotor getFlywheel() {
        return flywheel;
    }
}
