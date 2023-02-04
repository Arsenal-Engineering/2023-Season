// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimelightCam;

public class AutoAlign extends CommandBase {
  //subsystems
  private DriveTrain driveTrain;
  private LimelightCam limelight;
  //controller
  private CommandXboxController xboxController;

  //PID values for X and Y motion
  private final double X_KP = 0.2;
  private final double X_KD = 0.0;

  private final double Y_KP = 0.2;
  private final double Y_KD = 0.0;

  //PID controllers for X and Y motion
  PIDController ControllerX;
  PIDController ControllerY;

  //left/right distance to AprilTag
  private double sideOffset;
  
  /** Creates a new AutoAlign. */
  public AutoAlign(DriveTrain driveTrain, LimelightCam limelight, CommandXboxController xboxController, double sideOffset) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    addRequirements(limelight);

    //subsystems
    this.driveTrain = driveTrain;
    this.limelight = limelight;
    //controller
    this.xboxController = xboxController;

    //PID controllers, used to find speed to get to target
    ControllerX = new PIDController(X_KP, 0, X_KD);
    ControllerY = new PIDController(Y_KP, 0, Y_KD);

    this.sideOffset = sideOffset;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //gets translation object from the limelight
    Translation2d translation = limelight.getTranslation();
    double forwardSpeed = 0;
    double sideSpeed = 0;
    //if the translation is valid, sets the speeds using PID controllers (scary calculus the computer does for us)
    if(translation != null){
      forwardSpeed = ControllerX.calculate(translation.getX(), 0.45);
      sideSpeed = -ControllerY.calculate(translation.getY(), sideOffset);
    }
    //drives using speed values
    driveTrain.driveMecanum(forwardSpeed, sideSpeed, 0);

    
  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
