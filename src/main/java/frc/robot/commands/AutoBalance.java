// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoBalance extends CommandBase {
  private DriveTrain driveTrain;
  private AHRS navX;
  private Timer timer;
  /** Creates a new AutoBalance. */
  public AutoBalance(DriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    navX = new AHRS(edu.wpi.first.wpilibj.SPI.Port.kMXP);
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
    if (timer.get()<2){
      if (navX.getYaw()<-5){
        driveTrain.driveTest(0,0,0.5);
      } else if (navX.getYaw()>5){
        driveTrain.driveTest(0,0,-0.5);
      } else{
        driveTrain.driveTest(0,0,0);
      }
    } else if (timer.get()<3){
      driveTrain.driveTest(0.5,0,0);
    } else {
      if (navX.getPitch()>12){
        driveTrain.driveTest(0.5,0,0);
      } else if (navX.getPitch()<-12){
        driveTrain.driveTest(-0.5,0,0);
      } else {
        driveTrain.driveTest(0,0,0);
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
