// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoBalance extends CommandBase {
  private DriveTrain driveTrain;
  private AHRS navX;
  private double offset;
  /** Creates a new AutoBalance. */
  public AutoBalance(DriveTrain driveTrain) {
    //System.out.println("testprint");
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    navX = driveTrain.getNavX();

    //Determine offset
    offset = 5.9;
    System.out.println("offset " + offset);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("navX.getPitch()" + navX.getPitch());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println(navX.getPitch()-offset);
    if (navX.getPitch()-offset>4){
      driveTrain.driveMecanum(Math.sin((navX.getPitch()-offset)* 0.9 * (Math.PI / 180.0)+0.1)*-0.5, 0, 0);
    } else if (navX.getPitch()-offset<-4) { 
      driveTrain.driveMecanum(Math.sin((navX.getPitch()-offset)* 0.9 * (Math.PI / 180.0)-0.1)*-0.5, 0, 0);
    }//Stop when Balanced
    else {
      driveTrain.driveMecanum(0,0,0);
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
