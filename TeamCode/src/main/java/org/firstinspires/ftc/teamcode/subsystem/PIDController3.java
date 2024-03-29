package org.firstinspires.ftc.teamcode.subsystem;

public class PIDController3 {

    private double kp;
    private double ki;
    private double kd;

    private double time_step_ms;
    private double time_step_seconds;


    private double last_error = 0;
    private double error = 0;
    private double k_error = 0;
    private double i_error = 0;
    private double d_error = 0;


    /**
     * set PID gain and time between loop updates in milliseconds
     * @param Kp
     * @param Ki
     * @param Kd
     * @param time_step_ms
     */
    public PIDController3(double Kp, double Ki, double Kd, double time_step_ms) {
        kp = Kp;
        ki = Ki;
        kd = Kd;
        this.time_step_ms = time_step_ms;
        this.time_step_seconds = time_step_ms / 1000;
    }



    public double output(double setPoint, double currentState) {
        // calculate the error term
        error = setPoint - currentState;
        // obtain the proportional error (total waste of memory lul)
        k_error = error;
        d_error = (error - last_error) / time_step_seconds;
        i_error = i_error + (error * time_step_seconds);
        last_error = error;

        return (k_error * kp) + (i_error * ki) + (d_error * kd);
    }
}