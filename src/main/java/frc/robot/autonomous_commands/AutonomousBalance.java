// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous_commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;

public class AutonomousBalance extends CommandBase {
  private DriveTrain driveTrain;
  private AHRS navX;
  private Timer timer = new Timer();
  /** Creates a new AutoBalance. */
  public AutonomousBalance(DriveTrain driveTrain){
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    navX = driveTrain.getNavX();
    // Use addRequirements() here to declare subsystem dependencies.
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.setBrakeMode(true);
    driveTrain.setDriveMode(true);
    timer.reset();
    timer.start();
    System.out.println("AutoBalancing in Auto");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double switchDir = 1;

    if (navX.getYaw() > -90 && navX.getYaw() < 90)
      switchDir = -1;

    if (navX.getPitch() - Constants.NAVX_PITCH_OFFSET > 2) {
      driveTrain.driveTest(
          Math.sin((navX.getPitch() - Constants.NAVX_PITCH_OFFSET) * 0.9 * (Math.PI / 180.0) + 0.1) * -(1.5 / (2 + (timer.get() / 2))) * switchDir, 0, 0);
    } else if (navX.getPitch() - Constants.NAVX_PITCH_OFFSET < -2) {
      driveTrain.driveTest(
          Math.sin((navX.getPitch() - Constants.NAVX_PITCH_OFFSET) * 0.9 * (Math.PI / 180.0) - 0.1) * -(1.5 / (2 + (timer.get() / 2))) * switchDir, 0, 0);
    } // Stop when Balanced
    else {
      driveTrain.driveTest(0, 0, 0);
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
