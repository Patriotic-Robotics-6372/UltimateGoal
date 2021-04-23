package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Belt {

    private DcMotor belt;
    private double power;

    public Belt(DcMotor b) {
        belt = b;
    }

    public void forward() {
        belt.setPower(power);
    }

    public void backward() {
        belt.setPower(-power);
    }

    public void stop() {
        belt.setPower(0);
    }

    public void setPower(double p) {
        power = p;
    }

    public void setBelt(double power) {
        belt.setPower(power);
    }

    public DcMotor getBelt() {
        return belt;
    }
}
