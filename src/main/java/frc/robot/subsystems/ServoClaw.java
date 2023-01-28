// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Servo;

public class ServoClaw extends SubsystemBase {
  Servo leftServo;
  Servo rightServo;
  private double leftServoPosition;
  private double rightServoPosition;
  /** Creates a new ServoClaw. */
  public ServoClaw() {
    leftServo = new Servo(Constants.SERVO_LEFT);
    rightServo = new Servo(Constants.SERVO_RIGHT);
    leftServoPosition = 10;
    rightServoPosition = 160;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void testServoRotation(int angle){
    leftServo.setAngle(angle);
  }

  public void moveServo(double increment){
    if(leftServoPosition + increment <= 80 && leftServoPosition + increment >= 10){
      leftServoPosition += increment;
    }
    if(rightServoPosition - increment <= 160 && rightServoPosition - increment >= 90){
      rightServoPosition -= increment;
    }
    leftServo.setAngle(leftServoPosition);
    rightServo.setAngle(rightServoPosition);
  }
}
