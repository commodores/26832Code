package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class MyOpMode extends LinearOpMode {
    private Servo clawServo;
    private DcMotorSimple armMotor = null;



    @Override
    public void runOpMode() {
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        armMotor  = hardwareMap.get(DcMotorSimple.class, "armMotor");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            double armPower = .5;

            // check to see if we need to move the servo.

            if(gamepad1.y) {
                // move to 0 degrees.
                clawServo.setPosition(0);
            } else if (gamepad1.b) {
                // move to 90 degrees.
                clawServo.setPosition(1);
            }

            // check to see if we move the arm.
            if(gamepad1.x) {
                // move forward.
                armMotor.setPower(armPower);
            } else if (gamepad1.a) {
                // move reverse.
                armMotor.setPower(armPower);
            } else {
                armMotor.setPower(0);
            }

            telemetry.addData("Servo Position", clawServo.getPosition());

            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
