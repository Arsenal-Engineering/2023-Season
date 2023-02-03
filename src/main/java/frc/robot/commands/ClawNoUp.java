// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ClawWrist;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawNoUp extends CommandBase {
  private ClawWrist clawNoUp;
  /** Creates a new ClawDie. */
  public ClawNoUp(ClawWrist clawNoUp) {
    this.clawNoUp = clawNoUp;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(clawNoUp);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    clawNoUp.clawDoAFlip(0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
