// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous_commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Claw;

public class Drop extends CommandBase {
  /** Creates a new Drop. */
  private Timer timer;
  private Claw claw;
  public Drop(Claw claw) {
    // Use addRequirements() here to declare subsystem dependencies.
    timer = new Timer();
    this.claw = claw;
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
    claw.clawOpen();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    claw.clawStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > .7;
  }
}
