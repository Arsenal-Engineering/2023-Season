// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Timer;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command initialMoveAutonomous;
  private Command chargeStationRoute;
  private Command normalRoute;
  private Timer timer;
  private RobotContainer robotContainer;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    timer = new Timer();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    initialMoveAutonomous = robotContainer.getInitialMoveAutonomous();
    if (initialMoveAutonomous != null) {
      initialMoveAutonomous.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (initialMoveAutonomous != null) {
      initialMoveAutonomous.cancel();
    }
    if (chargeStationRoute != null) {
      chargeStationRoute.cancel();
    }
    if (normalRoute != null) {
      normalRoute.cancel();
    }

    if (Constants.DOES_DRIVETRAIN_EXIST) {
      // robotContainer.getDriveTrain().setBrakeMode(false);
      // robotContainer.getDriveJoystick().schedule();
      robotContainer.getRumble().schedule();
    }

    if (Constants.DOES_ARM_EXIST) {
      robotContainer.getArmMove().schedule();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
      //Axis 3 refers to the slider on the joystick 
      //All the way up is -1
      //All the way down is +1
      // if (robotContainer.getArmController().getRawAxis(3) < -.9) {
      //   robotContainer.getExtendArm().schedule();
      // }
      // if (robotContainer.getArmController().getRawAxis(3) > .9) {
      //   robotContainer.getRetractArm().schedule();
      // }

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();

    timer.reset();
    timer.start();

    // robotContainer.getController().a().whileTrue(new TestButtons());
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
    // if (initialMoveAutonomous != null) {
    //   initialMoveAutonomous.cancel();
    // }
    // if (chargeStationRoute != null) {
    //   chargeStationRoute.cancel();
    // }
    // if (normalRoute != null) {
    //   normalRoute.cancel();
    // }

    // if (Constants.DOES_DRIVETRAIN_EXIST) {
    //   robotContainer.getDriveTrain().setBrakeMode(false);
    //   robotContainer.getDriveJoystick().schedule();
    //   robotContainer.getRumble().schedule();
    // }

    // if (Constants.DOES_ARM_EXIST) {
    //   robotContainer.getArmMove().schedule();
    // }
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
}
