package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.Robot;

@TeleOp(name = "RobotTest", group = "Test")
public class RobotTest extends OpMode {

    Robot prBot = new Robot();

    @Override
    public void init() {
        prBot.init(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_up) {
            prBot.getDrawbridge().up();
        }
        prBot.getDrivetrain().forward(6);
        prBot.getDrivetrain().pointTurn(Constants.Status.RIGHT, 2.5);
        prBot.getDrivetrain().stop();
        //prBot.getOuttake().shoot(1);
    }


}
