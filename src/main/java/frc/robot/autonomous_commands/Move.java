// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous_commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
public class Move extends CommandBase {
    private Timer timer;
    private DriveTrain driveTrain;
    private double timeLimit;
    private double speed;
  /** Creates a new Move. */
  public Move(DriveTrain driveTrain, double timeLimit, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    timer = new Timer();
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    this.timeLimit = timeLimit;
    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.driveMecanum(speed, 0, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > timeLimit; //TODO: fix time
  }

  public boolean isHill(){
    return (driveTrain.getNavX().getPitch() > 10 || driveTrain.getNavX().getPitch() < -10);
  }
}
