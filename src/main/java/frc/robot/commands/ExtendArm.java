// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class ExtendArm extends CommandBase {
  private ArmExtension armExtender;
  private CommandXboxController controller;
  /** Creates a new ExtendArm. */
  public ExtendArm(ArmExtension armExtender, CommandXboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armExtender);
    this.armExtender = armExtender;
    this.controller = controller;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (controller.getRightTriggerAxis() > .2)
      armExtender.setSpeed(controller.getRightTriggerAxis() * 0.25);
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
