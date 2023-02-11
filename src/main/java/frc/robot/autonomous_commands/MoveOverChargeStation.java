// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous_commands;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class MoveOverChargeStation extends CommandBase {
  private DriveTrain driveTrain;
  private AHRS navX;
  /** Creates a new MoveOverChargeStation. */
  public MoveOverChargeStation(DriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    navX = driveTrain.getNavX();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.driveMecanum(-1.0, 0, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //finishes when robot is flat
    return (navX.getPitch() + Constants.NAVX_PITCH_OFFSET > -5 && navX.getPitch() + Constants.NAVX_PITCH_OFFSET < 5);
  }
}
