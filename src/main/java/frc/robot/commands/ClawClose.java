// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Claw;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawClose extends CommandBase {
  private Claw claw;
  private Joystick cJoystick;

  /** Creates a new ClawUnOpen. */
  public ClawClose(Claw clawClose,Joystick joystick) {
    claw = clawClose;
    cJoystick = joystick;
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(claw);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(cJoystick.getRawAxis(3)<0) {
      claw.clawConeClose();
    }
    else{
      claw.clawCubeClose();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    claw.clawStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
