package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

public class Robot {
    private HardwareMap hw;
    private Telemetry t;

    private DcMotorEx frontR;
    private DcMotorEx frontL;
    private DcMotorEx backR;
    private DcMotorEx backL;

    private ArrayList<DcMotorEx> motors;
    // frontL, frontR, backL, backR
   // private int encoderValues [] = {0,0,0,0};

    private final int FULL_TURN_ENCODERS = 1150;

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

        initMotorList();
        resetEncoders();
        startEncoders();
    }

    private void startEncoders(){
        for(DcMotorEx motor : motors){
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    private void resetEncoders(){
        for(DcMotorEx motor : motors){
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void setPower(float left, float right){
        frontR.setPower(right);
        backR.setPower(right);
        frontL.setPower(left);
        backL.setPower(left);
    }

    public void printState(){
        t.addData("FRP: ", frontR.getPower());
        t.addData("FLP: ", frontL.getPower());
        t.addData("BRP: ", backR.getPower());
        t.addData("BLP: ", backL.getPower());
        t.addData("FRONT RIGHT: ", frontR.getCurrentPosition());
        t.addData("FRONT LEFT: ", frontL.getCurrentPosition());
        t.addData("BACK RIGHT: ", backR.getCurrentPosition());
        t.addData("BACK LEFT", backL.getCurrentPosition());
        t.update();
    }

    private void initMotorList(){
        motors = new ArrayList<DcMotorEx>();
        motors.add(frontR);
        motors.add(frontL);
        motors.add(backR);
        motors.add(backL);
    }

    public void turn(int degrees, float power){
//        int frac = degrees/360;
//        int encoderTicks = frac * FULL_TURN_ENCODERS;
        int encoderTicks = 5000;
        resetEncoders();
        startEncoders();
//        frontL.setTargetPosition(encoderTicks);
//        frontL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

     //   this.setPower(power, -power);
//        while(frontL.isBusy()){
//            this.setPower(power, 0);
//            this.printState();
//        }
        while(encoderTicks > 0 ? frontL.getCurrentPosition() < encoderTicks: frontL.getCurrentPosition() > encoderTicks){
            this.setPower(power, -power);
            this.printState();
        }

//        stopEncoders();
        resetEncoders();
       // setPower(0,0);
    }

    private void stopEncoders(){
        for(DcMotorEx motor: motors){
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }


}
