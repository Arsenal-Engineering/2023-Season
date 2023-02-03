// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Claw;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawUnOpen extends CommandBase {
  private Claw clawUnOpen;
  /** Creates a new ClawUnOpen. */
  public ClawUnOpen(Claw clawUnOpen) {
    this.clawUnOpen = clawUnOpen;
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(clawUnOpen);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    clawUnOpen.clawGrip(-0.2);
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
