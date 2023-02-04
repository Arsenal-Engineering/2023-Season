// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
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

  //private final XboxController joystick =
  //new XboxController(OperatorConstants.kArmControllerPort);

  private final Joystick joystick = new Joystick(Constants.ARM_CONTROLLER_PORT);

  private final JoystickButton joystickButton1 = new JoystickButton(joystick,1);

  private final JoystickButton joystickButton2 = new JoystickButton(joystick,2);

  private final JoystickButton joystickButton3 = new JoystickButton(joystick,3);

  private final JoystickButton joystickButton4 = new JoystickButton(joystick,4);
  
  private final JoystickButton joystickButton5 = new JoystickButton(joystick,5);

  private final JoystickButton joystickButton6 = new JoystickButton(joystick,6);

  private final JoystickButton joystickButton7 = new JoystickButton(joystick,7);

  private final JoystickButton joystickButton8 = new JoystickButton(joystick,8);



  // The robot's subsystems and commands are defined here...

  private final ArmBase armBase = new ArmBase();

  private final ArmMove aMove = new ArmMove(armBase, joystick);

  private final Claw claw = new Claw(Constants.CLAW,MotorType.kBrushed);

  private final ClawOpen cOpen = new ClawOpen(claw);

  private final ClawClose cClose = new ClawClose(claw);

  private final ClawStop cStop = new ClawStop(claw);

  private final ClawWrist clawWrist = new ClawWrist(Constants.CLAW_WRIST,MotorType.kBrushed);

  private final WristStop wStop = new WristStop(clawWrist);

  private final WristUp wUp = new WristUp(clawWrist);

  private final WristDown wDown = new WristDown(clawWrist);

  private final DriveTrain driveTrain = new DriveTrain();
  private final DriveJoystick driveJoystick = new DriveJoystick(driveTrain, m_driverController.getHID());
  private final LifeCam lc = new LifeCam();
  
  private JoystickButton buttonA;
  
  private final LimelightCam limeLight = new LimelightCam();
  private final AutoAlign cubeAlign = new AutoAlign(driveTrain, limeLight, m_driverController, 0);
  private final AutoAlign leftConeAlign = new AutoAlign(driveTrain, limeLight, m_driverController, Constants.CONE_DEPOSIT_OFFSET);
  private final AutoAlign rightConeAlign = new AutoAlign(driveTrain, limeLight, m_driverController, -Constants.CONE_DEPOSIT_OFFSET);

  private final ArmExtension armExtender = new ArmExtension();

  private final ExtendArm extendArm = new ExtendArm(armExtender, m_driverController);

  private final RetractArm retractArm = new RetractArm(armExtender, m_driverController);

  private final TwistyWrist twistyWrist = new TwistyWrist();

  private final TwistWrist twistWrist = new TwistWrist(twistyWrist, joystick);


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

    m_driverController.b().onTrue(new DriveTurnTowardsDirection(driveTrain, 0)).onFalse(driveJoystick);
    m_driverController.a().onTrue(new AutoBalance(driveTrain)).onFalse(driveJoystick);

    m_driverController.start().onTrue(new SetDriveMode(driveTrain, true));
    m_driverController.back().onTrue(new SetDriveMode(driveTrain, false));
    
    m_driverController.povUp().onTrue(cubeAlign).onFalse(driveJoystick);
    m_driverController.povLeft().onTrue(leftConeAlign).onFalse(driveJoystick);
    m_driverController.povRight().onTrue(rightConeAlign).onFalse(driveJoystick);

    joystickButton1.onTrue(twistWrist);
    joystickButton2.onTrue(cOpen);

  }

  public CommandXboxController getXboxController() {
    return m_driverController;

    //m_driverController.a().onTrue(new )
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


  public Joystick getArmController(){
    return joystick;
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
  
  public LimelightCam getLimelightCam(){
    return limeLight;
  }

  public ArmMove getArmMove(){
    return aMove;
  }
}
