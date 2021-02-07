package org.firstinspires.ftc.teamcode.subsystem;

public class PIDController {

    private double KP = 0;
    private double KI = 0;
    private double KD = 0;

    private double lastKnownTime = 0;
    private double lastKnownError = 0;

    private double error = 0;
    private double integral = 0;
    private double derivative = 0;

    private double tolerance;

    private double setpoint;
    private double measurement;

    public PIDController(double KP, double KI, double KD) {
        this.KP = KP;
        this.KI = KI;
        this.KD = KD;
    }

    public void init(double initialError) {
        lastKnownTime = System.nanoTime() / 1E9;
        lastKnownError = initialError;
        integral = 0;
    }

    public void setConstants(double KP, double KI, double KD) {
        this.KP = KP;
        this.KI = KI;
        this.KD = KD;
    }

    public void setSetpoint(double sp) {
        setpoint = sp;
    }

    public void setTolerance(double t) {
        tolerance = t;
    }

    public void calculate(double error) {
        this.error = error;

        //Integral & Derivative
        double currentTime = System.nanoTime() / 1E9;
        double deltaTime = currentTime - lastKnownTime;
        lastKnownTime = currentTime;

        //Integral
        integral += error * deltaTime;

        //Derivative
        double deltaError = error - lastKnownError;
        lastKnownError = error;
        derivative = deltaError / deltaTime;

    }

    public boolean atSetpoint() {
//        double positionError;
//        positionError = setpoint - measurement;
//        return Math.abs(positionError) < tolerance;
        return error < tolerance;
    }

    public double getOutput() {
        return KP * error + KI * integral + KD * derivative;

    }

    public double getIntegral(){
        return integral;
    }

    public double getDerivative(){
        return derivative;
    }

}

