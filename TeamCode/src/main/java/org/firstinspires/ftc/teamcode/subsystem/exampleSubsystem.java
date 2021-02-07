package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Date: 1/13/21
 * Author: Jacob Marinas
 * An example subsystem for other programmers. This example subsystem is for a basic drivetrain
 */

// It's generally good practice to write down the date, author, and description of your program.

public class exampleSubsystem {

    // Define your hardware here: Motors, servos
    // You can also define helper variables; anything that you think that you will reuse again and again
    // Remember that to make a variable statement, it's:
    // dataType variableName; // dataType can be DcMotor, Servo, or Java variables like int, double
    // Make sure to include the import statements to remove the red underline

    private DcMotor leftMotor, rightMotor;

    // For a subsystem, you want to include a constructor.
    // As the name implies, it is a special method that is used to construct objects
    // In the parameters, you want to include the hardware that you are using.
    // Following the same scheme: dataType variableName. variableName will be referenced in the body

    public exampleSubsystem(DcMotor lM, DcMotor rM) {
        leftMotor = lM;
        rightMotor = rM;
        // This code tells what motors from the main Robot.java class are the leftMotor and rightMotor.
        // Good practice is simplifying the parameter names to just the initials of the variable name used in the subsystem
    }

    // Now, think to yourself: What can this subsystem do?
    // For example, for this drivetrain, I know that a robot can drive
    // forwards, backwards, and turn. So lets start with that

    // You write methods like this:

    // scope returnType methodName(dataType parameterName <- This is optional) {}

    // and you can include to use parameters as well. It's pretty much a variable that substitutes
    // It's good practice to include a comment that describes what your method, your behavior does

    /**
     * Sets motors to go forward by specified power
     * @param power
     */
    public void forward(double power) {
        leftMotor.setPower(-power);
        rightMotor.setPower(power);
        // Remember, that a positive number rotates counter-clockwise when facing in the same direction as the motor.
    }

    /**
     * Sets motors to go backward by specified power
     * @param power
     */
    public void backward(double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(-power);
        // To go from forwards to backwards, you can simply invert the sign
    }

    /**
     * Sets motors to turn left by specified power
     * @param power
     */
    public void left(double power) {
        // To turn left, the right side has to go forward, and the left side has to go backwards
        // To help, look at the forward() and backward() method to see what sign to use for which motor
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    /**
     * Sets motors to turn right by specified power
     * @param power
     */
    public void right(double power) {
        // To turn right, simply invert the sign
        leftMotor.setPower(-power);
        rightMotor.setPower(-power);
    }

    /**
     * Stops the drivetrain
     */
    public void stop() {
        // You don't need to include a parameter in the parenthesis because you know you will stop the motors
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}
