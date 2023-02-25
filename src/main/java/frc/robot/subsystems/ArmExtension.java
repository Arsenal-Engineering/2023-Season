// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.TestSparkMax;

//import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class ArmExtension extends SubsystemBase {
  private TestSparkMax extendMotor;
  private static double currentTimeExtended = 0.0;

  // Creates a new ArmExtension.
  public ArmExtension() {
    extendMotor = new TestSparkMax(Constants.ARM_EXTENDER_MOTOR,MotorType.kBrushed,"Arm extender");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(double speed) {
    extendMotor.set(speed);
    // System.out.print(speed);
  }

  public static void setCurrentTimeExtended(double input) {
    currentTimeExtended = input;
  }

  public static double getCurrentTimeExtended() {
    return currentTimeExtended;
  }
} 
