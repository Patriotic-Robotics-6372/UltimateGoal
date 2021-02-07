package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.teamcode.subsystem.Button;
import org.firstinspires.ftc.teamcode.subsystem.Telem;
import org.firstinspires.ftc.teamcode.subsystem.WobbleGoal;

@TeleOp(name = "WobblegoalTest", group = "Test")
public class WobblegoalTest extends OpMode {
// Joseph Campos Date: 1/21/2021
    WobbleGoal wobbleGoal;
    Telem telem;
    Button y = new Button();
    boolean toggle = false;

    @Override
    public void init() {
        wobbleGoal = new WobbleGoal(hardwareMap.dcMotor.get("wobbleLift"),hardwareMap.crservo.get("wobbleGrabber"),hardwareMap.crservo.get("wobbleGrabber2"));
        telem = new Telem(wobbleGoal, telemetry);
        wobbleGoal.setLiftPower(1);
        wobbleGoal.setGrabberPower(.8);
        telemetry.addData("Desc", "This programs tests for the WobbleGoal class")
                .addData("How to Use", "x/a for up/down lift. y/b to grab/let go");
        telemetry.update();
    }
    /**
     *this is telling the robot to lower the wobble goal contraption
     */
    @Override
    public void loop() {
        y.previous();
        y.setState(gamepad1.y);
        /**
         *  this is telling the robot to grab the wobble goal
         */
        if (y.isPressed()) {
            toggle   = !toggle;
        }
        if (gamepad1.y) {
            wobbleGoal.grab();
        } else if (gamepad1.b) {
            wobbleGoal.letGo();
        } else {
            wobbleGoal.stopServos();
        }
        /**
         * this is telling the robot to lift the wobble goal contraption
         */
        if (gamepad1.x) {
            wobbleGoal.up();
        } else if (gamepad1.a) {
            wobbleGoal.down();
        } else {
            wobbleGoal.stopMotor();
        }
        telem.addWobbleGoal();
        telem.update();
    }
}
