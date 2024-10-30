package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp

public class Myopmode extends LinearOpMode {
    private Servo clawServo;
    private DcMotorSimple armMotor = null;
    private DcMotorSimple elevatorMotor = null;

    @Override

    public void runOpMode() {

        clawServo = hardwareMap.get(Servo.class,"clawServo");
        armMotor = hardwareMap.get(DcMotor.class,"armMotor");
        elevatorMotor = hardwareMap.get(DcMotor.class, "elevatorMotor");



        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {

            //elevator functions //
            if (gamepad1.y) {
                elevatorMotor.setPower(.50);
                // move elevator up //

            } else if (gamepad1.a) {
                elevatorMotor.setPower(-.50);
                //move elevator down//
            } else {
                elevatorMotor.setPower(0);
            }

            // claw functions//

            if (gamepad1.x) {
                clawServo.setPosition(0);
                // closes the claw //

            } else if (gamepad1.b) {
                clawServo.setPosition(1);
                //opens the claw//
            } else {
                clawServo.setPosition(0);
            }

            // arm functions //
            if (gamepad1.dpad_left) {
                armMotor.setPower(.50);
                //move arm up//

            } else if (gamepad1.dpad_right) {
                armMotor.setPower(-.50);
                //move arm down//

            } else armMotor.setPower(0);

        }

            telemetry.addData("Status", "Running");
            telemetry.update();
        }

    }

