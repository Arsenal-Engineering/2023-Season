// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
//import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.TestSparkMax;

public class ClawWrist extends SubsystemBase {
  private TestSparkMax wristMotor;
  // Creates a new TwistyWrist. 
  public ClawWrist() {
    wristMotor = new TestSparkMax(Constants.WRIST_TWIST,MotorType.kBrushed,"Wrist motor");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void twist(double speed) {
    wristMotor.set(speed);
  }
} 
