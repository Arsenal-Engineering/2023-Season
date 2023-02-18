// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous_commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
public class Delay extends CommandBase {
    private Timer timer;
    private DriveTrain driveTrain;
    private double timeLimit;
  /** Creates a new Move. */
  public Delay(DriveTrain driveTrain, double timeLimit) {
    // Use addRequirements() here to declare subsystem dependencies.
    timer = new Timer();
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    this.timeLimit = timeLimit;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    driveTrain.setBrakeMode(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > timeLimit;
  }
}
