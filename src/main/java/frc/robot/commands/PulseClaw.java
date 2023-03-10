// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;

public class PulseClaw extends CommandBase {
  Claw claw;
  Timer timer;
  /** Creates a new PulseClaw. */
  public PulseClaw(Claw claw) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(claw);
    this.claw = claw;
    timer = new Timer();
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
    if((int)(timer.get() * 16) % 2 == 0){
      claw.clawClose();
    }
    else{
      claw.clawStop();
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
