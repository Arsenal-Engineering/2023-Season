// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class ExtendArm extends CommandBase {
  private ArmExtension armExtender;
  
  /** Creates a new ExtendArm. */
  public ExtendArm(ArmExtension armExtender) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armExtender);
    this.armExtender = armExtender;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("extend initialized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      armExtender.extend();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    armExtender.stopExtendingAndRetractingToRethinkYourLifeChoices();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
