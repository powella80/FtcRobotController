package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    private HardwareMap hw;
    private Telemetry t;

    private DcMotorEx frontR;
    private DcMotorEx frontL;
    private DcMotorEx backR;
    private DcMotorEx backL;

//    private DcMotorEx turret;
//    private DcMotorEx slide;
//    private DcMotorEx intake;
//
//    private CRServo duckwheel;
//    private Servo gorail;
//    private Servo claw;


    public Robot(HardwareMap hw, Telemetry t){
        this.hw = hw;
        this.t = t;
        this.initHardware();
        startEncoders();
    }

    private void initHardware(){
        frontR = hw.get(DcMotorEx.class, "frontRight");
        backR = hw.get(DcMotorEx.class, "backRight");
        frontL = hw.get(DcMotorEx.class, "frontLeft");
        backL = hw.get(DcMotorEx.class, "backLeft");

//        turret = hw.get(DcMotorEx.class, "turret");
//        slide = hw.get(DcMotorEx.class, "slide");
//        intake = hw.get(DcMotorEx.class, "intake");
//
//        duckwheel = hw.get(CRServo.class, "duckwheel");
//        gorail = hw.get(Servo.class, "gorail");
//        claw = hw.get(Servo.class, "claw");

        frontR.setDirection(DcMotorSimple.Direction.REVERSE);
        backR.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontL.setDirection(DcMotorSimple.Direction.REVERSE);

        frontL.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        backL.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        frontR.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        backR.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void startEncoders(){
        frontL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        frontR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    public void setPower(float left, float right){
        frontR.setPower(right);
        backR.setPower(right);
        frontL.setPower(left);
        backL.setPower(left);
    }

    public void printEncoders(){
        t.addData("FRONT RIGHT: ", frontR.getCurrentPosition());
        t.addData("FRONT LEFT: ", frontL.getCurrentPosition());
        t.addData("BACK RIGHT: ", backR.getCurrentPosition());
        t.addData("BACK LEFT", backL.getCurrentPosition());
        t.update();
    }



}
