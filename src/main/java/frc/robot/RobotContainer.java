// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
    new CommandXboxController(OperatorConstants.kDriverControllerPort);

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final DriveTrain driveTrain = new DriveTrain();

  private final DriveJoystick driveJoystick = new DriveJoystick(driveTrain, m_driverController.getHID());

  private final AutoBalance autoBalance = new AutoBalance(driveTrain);

  private final AutoAlign autoAlign = new AutoAlign(driveTrain, 0);

  private final LifeCam lc = new LifeCam();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    lc.startVision();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    /*new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));*/

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    m_driverController.start().onTrue(new AutoAlign(driveTrain, 0));
    m_driverController.a().onTrue(new AutoBalance(driveTrain));
    m_driverController.x().onTrue(new DriveJoystick(driveTrain, m_driverController.getHID()));
    /*RIGHT NOW TO TAKE BACK DRIVE ^^^, FIND BETTER WAY TO DO THIS; STILL WANT TO USE TRIGGERS THO*/

    m_driverController.start().onTrue(new SetDriveMode(driveTrain, true));
    m_driverController.back().onTrue(new SetDriveMode(driveTrain, false));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
  // 
  public CommandXboxController getXboxController() {
    return m_driverController;
  }

  public DriveJoystick getdriveJoystick() {
    return driveJoystick;
  }

  public DriveTrain getDriveTrain() {
    return driveTrain;
  }

  public AutoBalance getAutoBalance() {
    return autoBalance;
  }

  public AutoAlign getAutoAlign() {
    return autoAlign;
  }
}
