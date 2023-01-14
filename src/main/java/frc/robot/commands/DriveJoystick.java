// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;

public class DriveJoystick extends CommandBase {
  private DriveTrain doggoDriveTrain;
  private XboxController humanController;

  /** Creates a new DriveJoystick. */
  public DriveJoystick(DriveTrain dog,XboxController human) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(dog);
    doggoDriveTrain = dog;
    humanController = human;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("test1");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    doggoDriveTrain.driveMecanum(humanController);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("test2");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
