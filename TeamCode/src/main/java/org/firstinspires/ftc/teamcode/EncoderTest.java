package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class EncoderTest extends OpMode {
    public Robot r;
    float power;
    @Override
    public void init() {
        r = new Robot(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
//        if(gamepad1.a){
//            power = 0.1f;
//        }
//        if(gamepad1.b){
//            power = 0;
//        }
//        r.setPower(-power, power);
//        r.printState();

        if(gamepad1.a){
            r.turn(90, 0.5f);
            r.printState();
        }
        if(gamepad1.b){
            r.turn(-360, 0.5f);
            r.printState();
        }

    }
}
