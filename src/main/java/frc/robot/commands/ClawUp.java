// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ClawWrist;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawUp extends CommandBase {
  private ClawWrist clawUp;
  /** Creates a new ClawDie. */
  public ClawUp(ClawWrist clawUp) {
    this.clawUp = clawUp;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(clawUp);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    clawUp.clawDoAFlip(.2);
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
