package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class FlywheelPID {

    private DcMotorEx flywheel;
    private double percent = 1.0;

    public FlywheelPID(DcMotorEx fW) {
        flywheel = fW;
    }

    public void init() {
        MotorConfigurationType motorConfigurationType = flywheel.getMotorType().clone();
        motorConfigurationType.setAchieveableMaxRPMFraction(percent);
        flywheel.setMotorType(motorConfigurationType);

        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void stop() {
        flywheel.setPower(0);
    }

    public void increaseMaxRPM() {
        percent += .05;
        if (percent > 1.0) {
            percent = 1.0;
        }
        setMaxRPM(percent);
    }

    public void decraseMaxRPM() {
        percent -= .05;
        if (percent < 0) {
            percent = 0;
        }
        setMaxRPM(percent);
    }

    public void setMaxRPM(double percent) {
        MotorConfigurationType motorConfigurationType = flywheel.getMotorType().clone();
        motorConfigurationType.setAchieveableMaxRPMFraction(percent);
        flywheel.setMotorType(motorConfigurationType);

        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public double getMaxRPM() {
        return flywheel.getMotorType().getAchieveableMaxRPMFraction();
    }

    public DcMotorEx getFlywheel() {
        return flywheel;
    }
}
