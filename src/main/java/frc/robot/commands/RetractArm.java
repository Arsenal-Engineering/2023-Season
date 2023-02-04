// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RetractArm extends CommandBase {
  private ArmExtension armRetractor;
  private CommandXboxController controller;
  private DigitalInput limitSwitch;
  /** Creates a new RetractArm. */
  public RetractArm(ArmExtension armRetractor, CommandXboxController controller, int limitSwitchID) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armRetractor);
    this.armRetractor = armRetractor;
    this.controller = controller;
    limitSwitch = new DigitalInput(limitSwitchID);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (limitSwitch.get()){
      armRetractor.setSpeed(0);
    }else if (controller.getLeftTriggerAxis() > .2)
      armRetractor.setSpeed(-controller.getLeftTriggerAxis() * 0.25);
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
