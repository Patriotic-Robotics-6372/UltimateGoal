package org.firstinspires.ftc.teamcode.subsystem;

public interface Constants {

    int STOP = 0;

    double posRing = 30 / 180;
    double posRing2 = 0 / 180;

    double TICKS_PER_IN = 537.6/(4*Math.PI);

    int LIFT_THRESHOLD = -500;

    int SHOOT_UP_START = 300;
    int SHOOT_UP_SETPOINT = 800;

    enum Status {
        FORWARDS, BACKWARDS,
        UP, DOWN,
        LEFT, RIGHT,
        OPEN, CLOSE,
        NEUTRAL,
        NORTH, SOUTH, EAST, WEST,
        RED, GREEN, BLUE,
        DARK, LIGHT,
        NORMAL, AUTO;
    }

}
