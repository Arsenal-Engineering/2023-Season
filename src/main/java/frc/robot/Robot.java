// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.autonomous_commands.Move;
import edu.wpi.first.wpilibj.Timer;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command initialMoveAutonomous;
  private Command chargeStationRoute;
  private Command normalRoute;
  private Timer timer;
//jkjkhkhk
//kkjkj
  private RobotContainer robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    robotContainer = new RobotContainer();
    timer = new Timer();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    initialMoveAutonomous = robotContainer.getInitialMoveAutonomous();
    if(initialMoveAutonomous != null){
      initialMoveAutonomous.schedule();
    }

    
    
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (initialMoveAutonomous != null) {
      initialMoveAutonomous.cancel();
    }
    if (chargeStationRoute != null) {
      chargeStationRoute.cancel();
    }
    if (normalRoute != null) {
      normalRoute.cancel();
    }
    robotContainer.getdriveJoystick().schedule();
   // robotContainer.getArmMove().schedule();

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic(){
    if(Constants.DOES_ARM_EXIST){
      //Axis 3 refers to the slider on the joystick 
      //All the way up is -1
      //All the way down is +1
      
      // if (robotContainer.getArmController().getRawAxis(3) < -.9) {
      //   robotContainer.getExtendArm().schedule();
      // }
      // if (robotContainer.getArmController().getRawAxis(3) > .9) {
      //   robotContainer.getRetractArm().schedule();
      // }

      //Axis 2 refers to Z Rotate (Rotating the joystick)
      //-1 is rotate left
      //+1 is rotate right
      if (Math.abs(robotContainer.getArmController().getRawAxis(2)) > .2){
        robotContainer.getTwistWrist().schedule();
      }

      //Axis 1 refers to Y-Axis (Move Joystick front and back)
      //-1 is front
      //+1 is back
      if (Math.abs(robotContainer.getArmController().getRawAxis(1)) > .2){
        robotContainer.getArmMove().schedule();
      }

      //Buttons 5 & 6 are the buttons labeled 5 & 6
      //Returns True or False
      // if (robotContainer.getArmController().getRawButton(1)){
      //   robotContainer.getClawClose().schedule();
      // }
      // else if(robotContainer.getArmController().getRawButton(2)) {
      //   robotContainer.getClawOpen().schedule();
      // }
      // else{
      //   robotContainer.getClawStop().schedule();
      // }

      //getPOV refers to the D-Pad on the joystick
      //returns the angle(degree) of the dpad with 0 = top
      if (robotContainer.getArmController().getPOV() == 0) {
        robotContainer.getWristUp().schedule();
      }
      else if (robotContainer.getArmController().getPOV() == 180) {
        robotContainer.getWristDown().schedule();
      }
      else{
        robotContainer.getWristStop().schedule();
      }
    }
   }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();

    timer.reset();
    timer.start();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    if(timer.get() < 0.5)
      robotContainer.getDriveTrain().driveMecanum(0.5, 0, 0);
    else
      robotContainer.getDriveTrain().driveMecanum(0, 0, 0);
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
