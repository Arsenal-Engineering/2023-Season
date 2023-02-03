// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final XboxController m_driverController =
    new XboxController(OperatorConstants.kDriverControllerPort);

  private final XboxController m_armController =
    new XboxController(OperatorConstants.kArmControllerPort);



  // The robot's subsystems and commands are defined here...

  private final ArmBase armBase = new ArmBase();

  private final ArmMove aMove = new ArmMove(armBase, m_armController);

  private final Claw claw = new Claw(Constants.CLAW,MotorType.kBrushed);

  private final ClawOpen cOpen = new ClawOpen(claw);

  private final ClawUnOpen cUnOpen = new ClawUnOpen(claw);

  private final ClawNoOpen cNoOpen = new ClawNoOpen(claw);

  private final ClawWrist clawWrist = new ClawWrist(Constants.CLAW_WRIST,MotorType.kBrushed);

  private final ClawNoUp cNoUp = new ClawNoUp(clawWrist);

  private final ClawUp cUp = new ClawUp(clawWrist);

  private final ClawUnUp cUnUp = new ClawUnUp(clawWrist);












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
  // public DriveJoystick getdriveJoystick() {
  //   return driveJoystick;
  // }

  public XboxController getXboxController(){
    return m_driverController;
  }

  public XboxController getArmController(){
    return m_armController;
  }

  public ArmMove getArmMove(){
    return aMove;
  }
  
  public ClawOpen getClawOpen(){
    return cOpen;
  }
  public ClawUnOpen getClawUnOpen(){
    return cUnOpen;
  }
  public ClawUp getClawUp(){
    return cUp;
  }
  public ClawNoUp getClawNoUp(){
    return cNoUp;
  }
  public ClawUnUp getClawUnUp(){
    return cUnUp;
  }
  public ClawNoOpen getClawNoOpen(){
    return cNoOpen;
  }
}
