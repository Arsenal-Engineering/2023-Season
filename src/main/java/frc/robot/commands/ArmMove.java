// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmBase;
import edu.wpi.first.wpilibj.Joystick;

public class ArmMove extends CommandBase {

  private ArmBase armMove;
  private Joystick armcontrol;
  /** Creates a new ArmUp. */
  public ArmMove(ArmBase armMove, Joystick armcontroller) {
    this.armMove = armMove;
    armcontrol = armcontroller;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armMove);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    armMove.armMove(-armcontrol.getRawAxis(1)/2);
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
