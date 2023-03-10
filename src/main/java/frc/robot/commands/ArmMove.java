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

  // Creates a new ArmUp.
  public ArmMove(ArmBase armMove, Joystick armcontroller) {
    this.armMove = armMove;
    armcontrol = armcontroller;

    addRequirements(armMove);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double armmovement = armcontrol.getRawAxis(1);

    if (armmovement > .2) {
      armMove.armUp(armmovement);
    } else if (armmovement < -.2) {
      armMove.armDown(armmovement);
    } else {
      armMove.armStop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    armMove.armStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
