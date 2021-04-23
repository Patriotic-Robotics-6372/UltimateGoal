package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Servo;

public class Scoop {

    private Servo leftScoop, rightScoop;
    private double pos = 90;

    public Scoop(Servo lS, Servo rS) {
        leftScoop = lS;
        rightScoop = rS;
    }

    public void scoopUp() {
        leftScoop.setPosition(pos / 180);
        rightScoop.setPosition(1.0 - (pos / 180));
    }

    public void scoopDown() {
        leftScoop.setPosition(1.0 - (pos / 180));
        rightScoop.setPosition(pos / 180);
    }

    public void setPos(double p) {
        pos = p;
    }

    public void increasePos() {
        pos++;
        if (pos > 180) {
            pos = 180;
        }
    }

    public void decreasePos() {
        pos--;
        if (pos < 0) {
            pos = 0;
        }
    }

    public Servo getLeftScoop() {
        return leftScoop;
    }

    public Servo getRightScoop() {
        return rightScoop;
    }

    public double getPos() {
        return pos;
    }
}
