package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class TestTeleop extends OpMode {
    public Robot r;

    @Override
    public void init() {
        r = new Robot(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        float rightPower = -gamepad1.right_stick_y;
        float leftPower = -gamepad1.left_stick_y;
        r.setPower(leftPower, rightPower);
        r.printEncoders();
    }
}
