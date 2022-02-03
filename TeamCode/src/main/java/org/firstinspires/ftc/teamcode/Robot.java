package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import java.util.ArrayList;
// 384.5 = 0.53m = 1 rev

public class Robot {
    private HardwareMap hw;
    private Telemetry t;

    private DcMotorEx frontR;
    private DcMotorEx frontL;
    private DcMotorEx backR;
    private DcMotorEx backL;
    private double goalEncoders;

    private ArrayList<DcMotorEx> motors;
    // frontL, frontR, backL, backR
   // private int encoderValues [] = {0,0,0,0};

    private final int FULL_TURN_ENCODERS = 900;

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
        t.addData("Encoders Goal: ", goalEncoders);
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

    public void turn(double degrees, float power){
        double frac = degrees/360.0;
        goalEncoders = frac * FULL_TURN_ENCODERS;

        resetEncoders();
        startEncoders();
        if(goalEncoders > 0) {
            while (frontL.getCurrentPosition() < goalEncoders) {
                t.addData("frac: ", frac);
                this.setPower(power, -power);
                this.printState();
            }
        } else {
            while (frontL.getCurrentPosition() > goalEncoders) {
                t.addData("frac: ", frac);
                this.setPower(-power, power);
                this.printState();
            }
        }
        setPower(0,0);
    }

    public void forward(double distance, float power){

        goalEncoders = distance / 0.53 * 384.5;

       // resetEncoders();
        startEncoders();
        while (frontL.getCurrentPosition() < goalEncoders) {
            this.setPower(power, power);
            t.addData("Goal Encoders: ", goalEncoders);
            this.printState();
        }

        setPower(0,0);
    }

    private void stopEncoders(){
        for(DcMotorEx motor: motors){
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }


}
