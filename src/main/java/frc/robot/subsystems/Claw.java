// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Claw extends SubsystemBase {
  private WPI_TalonFX claw;
  private final double speed = 0.2;
  private final double holdOpenPosition = 0.06;
  private final double brake = 0.0;

  // Creates a new OpenCloseClaw.
  public Claw(int clawID) {
    claw = new WPI_TalonFX(clawID);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void clawClose() {
    claw.set(-speed);
  }

  public void clawOpen() {
    claw.set(speed / 2);
  }

  //We want to avoid opening the claw past where it's supposed to and causing the motor to scream
  //So once, the claw has opened, we must set it at a slight speed so it doesn't close while driver is picking up object
  public void holdOpenPosition() {
    claw.set(holdOpenPosition);
  }

  public void clawStop() {
    claw.set(brake);
  }
}
