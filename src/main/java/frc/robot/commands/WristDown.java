// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ClawUpDown;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class WristDown extends CommandBase {
  private ClawUpDown wristDown;
  /** Creates a new ClawDie. */
  public WristDown(ClawUpDown wristDown) {
    this.wristDown = wristDown;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(wristDown);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    wristDown.setWristSpeed(-.2);
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