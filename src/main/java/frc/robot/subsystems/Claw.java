// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Claw extends SubsystemBase {
  private WPI_TalonFX claw;
  private final double howFastToDoDaGrabbyGrabAndReleasyRelease = 0.8;
  private final double stopExistingAndNoMoreBreathing = 0.0;

  // Creates a new OpenCloseClaw.
  public Claw(int clawID) {
    claw = new WPI_TalonFX(clawID);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void clawClose() {
    claw.set(-howFastToDoDaGrabbyGrabAndReleasyRelease);
  }

  public void clawOpen() {
    claw.set(howFastToDoDaGrabbyGrabAndReleasyRelease);
  }

  public void clawStop() {
    claw.set(stopExistingAndNoMoreBreathing);
  }
}
