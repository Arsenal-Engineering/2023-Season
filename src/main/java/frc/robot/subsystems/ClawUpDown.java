// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.TestSparkMax;


public class ClawUpDown extends SubsystemBase {
  private TestSparkMax clawWrist;
  // Creates a new ClawWrist. 
  public ClawUpDown(int clawWristID, MotorType motor) {
    clawWrist = new TestSparkMax(clawWristID, motor,"Claw wrist");
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setWristSpeed(double clawSpeed){
    clawWrist.set(clawSpeed);
  }
} 
