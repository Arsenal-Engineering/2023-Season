// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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

  private final Joystick joystick = new Joystick(Constants.JOYSTICK_PORT);

  private final JoystickButton joystickButton1 = new JoystickButton(joystick, 1);

  // The robot's subsystems and commands are defined here...

  private final DriveTrain driveTrain = new DriveTrain();

  private final DriveJoystick driveJoystick = new DriveJoystick(driveTrain, m_driverController.getHID());

  private final ArmExtension armExtender = new ArmExtension();

  private final ExtendArm extendArm = new ExtendArm(armExtender, m_driverController);

  private final RetractArm retractArm = new RetractArm(armExtender, m_driverController);

  private final TwistyWrist twistyWrist = new TwistyWrist();

  //private final TwistWrist twistWrist = new TwistWrist(twistyWrist, m_driverController.getHID());

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
    m_driverController.leftBumper().onTrue(new TwistWrist(twistyWrist, joystick));

    joystickButton1.onTrue(new TwistWrist(twistyWrist, joystick));
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
  public DriveJoystick getdriveJoystick() {
    return driveJoystick;
  }

  public ExtendArm getExtendArm() {
    return extendArm;
  }

  public RetractArm getRetractArm() {
    return retractArm;
  }

  public CommandXboxController getController() {
    return m_driverController;
  }

  public TwistWrist getTwistWrist() {
    return twistWrist;
  }
}
