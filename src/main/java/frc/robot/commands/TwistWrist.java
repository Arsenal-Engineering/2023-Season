// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Joystick;

public class TwistWrist extends CommandBase {
  private ClawWrist clawtwist;
  private Joystick controller;
  /** Creates a new TwistWristL. */
  public TwistWrist(ClawWrist twist, Joystick controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(twist);
    clawtwist = twist;
    this.controller = controller;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  //change the button triggers
  public void execute() {
    if(Math.abs(controller.getRawAxis(2)) > .2){
      clawtwist.twist(controller.getRawAxis(2)/2);
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
