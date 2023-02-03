// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
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
  private JoystickButton buttonA;

  private final DriveTrain driveTrain = new DriveTrain();

  private final DriveJoystick driveJoystick = new DriveJoystick(driveTrain, m_driverController.getHID());

  private final LimelightCam limeLight = new LimelightCam();
  private final AutoAlign cubeAlign = new AutoAlign(driveTrain, limeLight, m_driverController, 0);
  private final AutoAlign leftConeAlign = new AutoAlign(driveTrain, limeLight, m_driverController, Constants.CONE_DEPOSIT_OFFSET);
  private final AutoAlign rightConeAlign = new AutoAlign(driveTrain, limeLight, m_driverController, -Constants.CONE_DEPOSIT_OFFSET);



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
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

    //pravnav
    //the limit as iq aproaches infinity
    //I am here
    m_driverController.a().onTrue(cubeAlign).onFalse(driveJoystick);
    m_driverController.x().onTrue(leftConeAlign).onFalse(driveJoystick);
    m_driverController.b().onTrue(rightConeAlign).onFalse(driveJoystick);

   }

  public DriveJoystick getdriveJoystick() {
    return driveJoystick;
  }

  public DriveTrain getDriveTrain() {
    return driveTrain;
  }
  public LimelightCam getLimelightCam(){
    return limeLight;
  }
}
