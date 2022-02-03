package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class Autonomous extends OpMode {

    public Robot r;
    float power;
    @Override
    public void init() {
        r = new Robot(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        r.forward(2.41, 0.5f);
    }
}
