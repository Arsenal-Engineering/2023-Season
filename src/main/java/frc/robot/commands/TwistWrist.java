// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.XboxController;

public class TwistWrist extends CommandBase {
  private TwistyWrist twist;
  private XboxController controller;
  /** Creates a new TwistWristL. */
  public TwistWrist(TwistyWrist twist, XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(twist);
    this.twist = twist;
    this.controller = controller;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (controller.getLeftBumper()){
      twist.twist(-0.25);
    }
    if (controller.getRightBumper()){
      twist.twist(0.25);
    }
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
