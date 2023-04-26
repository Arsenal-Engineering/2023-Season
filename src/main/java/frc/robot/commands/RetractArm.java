// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Timer;

public class RetractArm extends CommandBase {
  private ArmExtension armRetractor;
  private Timer timer;

  /** Creates a new RetractArm. */
  public RetractArm(ArmExtension armRetractor) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armRetractor);
    this.armRetractor = armRetractor;
    this.timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (timer.get() < 0.2)
      armRetractor.retractSlow();
    else
      armRetractor.retract();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    armRetractor.stopExtension();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
