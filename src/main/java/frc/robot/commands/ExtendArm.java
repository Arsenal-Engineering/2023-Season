// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class ExtendArm extends CommandBase {
  private ArmExtension armExtender;
  private Joystick controller;
  private DigitalInput limitSwitch;
  private Timer timer;
  
  /** Creates a new ExtendArm. */
  public ExtendArm(ArmExtension armExtender, Joystick controller, int limitSwitchID) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armExtender);
    this.armExtender = armExtender;
    this.controller = controller;
    limitSwitch = new DigitalInput(limitSwitchID);

    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("extend initialized");

    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if (limitSwitch.get()){
    //   armExtender.setSpeed(0);
    //   System.out.println("limit switch extend top");

    //   timer.reset();
    //   timer.stop();
    // }else {
      armExtender.setSpeed(.5);
    // }

    // System.out.println("Arm is extended this amount of time: " + (ArmExtension.getCurrentTimeExtended() + timer.get()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    armExtender.setSpeed(0);
    ArmExtension.setCurrentTimeExtended(ArmExtension.getCurrentTimeExtended() + timer.get());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
