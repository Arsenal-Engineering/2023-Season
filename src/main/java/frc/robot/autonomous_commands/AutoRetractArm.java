// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous_commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtension;

public class AutoRetractArm extends CommandBase {
  private ArmExtension armExtension;
  private Timer timer;
  private double timeLimit;
  /** Creates a new AutoRetractArm. */
  public AutoRetractArm(ArmExtension armExtension, double timeLimit) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armExtension);
    this.armExtension = armExtension;
    timer = new Timer();
    this.timeLimit = timeLimit;
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
    if (timer.get() < 0.3)
      armExtension.retractSlow();
    else
      armExtension.retractAutonomous();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    armExtension.stopExtension();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > timeLimit || armExtension.getTopLimSwitch();
  }
}
