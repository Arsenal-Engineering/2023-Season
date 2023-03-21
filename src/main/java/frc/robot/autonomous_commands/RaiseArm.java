// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous_commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.ArmBase;

public class RaiseArm extends CommandBase {
  private Timer timer;
  private ArmBase arm;
  private double timeLimit;
  /** Creates a new LowerArm. */
  public RaiseArm(ArmBase arm, double timeLimit) {
    // Use addRequirements() here to declare subsystem dependencies.
    timer = new Timer();
    this.arm = arm;
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
    arm.autonomousArmUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.armStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > timeLimit;
  }
}
