package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp
public class OpModeFreePlay extends LinearOpMode {

    private Servo clawServo;
    private DcMotorSimple armMotor = null;
    private DcMotorSimple elevatorMotor = null;

    @Override

    public void runOpMode() {
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        armMotor = hardwareMap.get(DcMotorSimple.class, "armMotor");
        elevatorMotor = hardwareMap.get(DcMotorSimple.class, "elevatorMotor");
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            // arm functions //
            if (gamepad1.x) {
                armMotor.setPower(.50);

                //arm motion up//

            } else if (gamepad1.a) {
                armMotor.setPower(-.50);

                //arm motion down//
            } else {
                armMotor.setPower(0);

                //no motion//
            }

            //claw functions//
            if(gamepad1.y) {
                clawServo.setPosition(.825);
                //claw open//
            }

            else if(gamepad1.b) {
                clawServo.setPosition(.7);
                //claw close//
            }

            //elevator functions//

            if(gamepad1.right_bumper) {
                elevatorMotor.setPower(.50);
                //elevator up//

            } else if (gamepad1.left_bumper) {
                elevatorMotor.setPower(-.50);
                //elevator down//

            } else {
                elevatorMotor.setPower(0);
                //no motion//



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
