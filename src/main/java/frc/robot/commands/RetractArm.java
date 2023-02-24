// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class RetractArm extends CommandBase {
  private ArmExtension armRetractor;
  private Joystick controller;
  private DigitalInput limitSwitch;
  private Timer timer;

  /** Creates a new RetractArm. */
  public RetractArm(ArmExtension armRetractor, Joystick controller, int limitSwitchID) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armRetractor);
    this.armRetractor = armRetractor;
    this.controller = controller;
    limitSwitch = new DigitalInput(limitSwitchID);

    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // System.out.println("Retract initialized");

    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (limitSwitch.get()){
      armRetractor.setSpeed(0);
      ArmExtension.setCurrentTimeExtended(0);
      timer.reset();
      timer.stop();
      // System.out.println("limit switch bottom");
    }else
      armRetractor.setSpeed(-.5);

    // System.out.println("Arm is extended this amount of time: " + (ArmExtension.getCurrentTimeExtended() - timer.get()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    armRetractor.setSpeed(0);

    
    ArmExtension.setCurrentTimeExtended(ArmExtension.getCurrentTimeExtended() - timer.get());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
