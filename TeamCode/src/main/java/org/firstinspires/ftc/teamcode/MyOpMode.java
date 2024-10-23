package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp

public class MyOpMode extends LinearOpMode {
    private Servo clawServo;
    private DcMotorSimple armMotor = null;
    private DcMotorSimple elevatorMotor = null;



    @Override
    public void runOpMode() {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        armMotor  = hardwareMap.get(DcMotorSimple.class, "armMotor");
        elevatorMotor  = hardwareMap.get(DcMotorSimple.class, "elevatorMotor");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            double armPower = .5;
            double elevatorPower = .75;

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
                // move up.
                armMotor.setPower(-armPower);
            } else if (gamepad1.a) {
                // move down.
                armMotor.setPower(armPower);
            } else {
                armMotor.setPower(0);
            }

            // check to see if we move the elevator.
            if(gamepad1.left_bumper) {
                // move down.
                elevatorMotor.setPower(-elevatorPower);
            } else if (gamepad1.right_bumper) {
                // move up.
                elevatorMotor.setPower(elevatorPower);
            } else {
                elevatorMotor.setPower(0);
            }

            //run drivetrain
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );
            drive.update();

            telemetry.addData("Servo Position", clawServo.getPosition());

            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
