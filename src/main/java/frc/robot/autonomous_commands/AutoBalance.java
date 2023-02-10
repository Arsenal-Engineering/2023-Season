// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous_commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;

public class AutoBalance extends CommandBase {
  private DriveTrain driveTrain;
  private AHRS navX;
  /** Creates a new AutoBalance. */
  public AutoBalance(DriveTrain driveTrain){
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    navX = driveTrain.getNavX();
    // Use addRequirements() here to declare subsystem dependencies.
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (navX.getPitch() - Constants.NAVX_PITCH_OFFSET > 4){
      driveTrain.driveTest(Math.sin((navX.getPitch() - Constants.NAVX_PITCH_OFFSET) * 0.9 * (Math.PI / 180.0) + 0.1) * -0.5, 0, 0);
    } else if (navX.getPitch() - Constants.NAVX_PITCH_OFFSET < -4) { 
      driveTrain.driveTest(Math.sin((navX.getPitch() - Constants.NAVX_PITCH_OFFSET) * 0.9 * (Math.PI / 180.0) -0.1)* -0.5, 0, 0);
    }//Stop when Balanced
    else {
      driveTrain.driveTest(0,0,0);
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
