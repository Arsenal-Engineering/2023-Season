// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveTurnTowardsDirection extends CommandBase {
  private DriveTrain driveTrain;
  private AHRS navX;
  /** Creates a new AutoAlign. */
  public DriveTurnTowardsDirection(DriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    navX = driveTrain.getNavX();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Turn on E-breake
    driveTrain.setBrakeMode(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Align Yaw to 180
    if (driveTrain.getBrakeMode()) {
      if (navX.getYaw() < 0) {
        driveTrain.driveTest(0, 0, Math.sin((navX.getYaw()+180) * (Math.PI / 180.0))*-1);
      } else {
        driveTrain.driveTest(0, 0, Math.sin((navX.getYaw()-180) * (Math.PI / 180.0))*-1);
      }
    }
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
