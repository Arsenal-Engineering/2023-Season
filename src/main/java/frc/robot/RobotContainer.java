// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.autonomous_commands.Autos;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import edu.wpi.first.wpilibj.Joystick;


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


 private  Joystick joystick;
 private Trigger button9;
 private Trigger button11;



  // The robot's subsystems and commands are defined here...

  private  ArmBase armBase;

  private  ArmMove aMove;

  private  Claw claw;

  private  ClawOpen cOpen;

  private  ClawClose cClose;

  private  ClawStop cStop;

  private  ClawUpDown clawWrist;

  private  WristStop wStop;

  private  WristUp wUp;

  private  WristDown wDown;

  private final DriveTrain driveTrain = new DriveTrain();
  private final DriveJoystick driveJoystick = new DriveJoystick(driveTrain, m_driverController.getHID());
  private final LifeCam lc = new LifeCam();
    
  private final LimelightCam limeLight = new LimelightCam();
  private final AutoAlign cubeAlign = new AutoAlign(driveTrain, limeLight, 0);
  private final AutoAlign leftConeAlign = new AutoAlign(driveTrain, limeLight, Constants.CONE_DEPOSIT_OFFSET);
  private final AutoAlign rightConeAlign = new AutoAlign(driveTrain, limeLight, -Constants.CONE_DEPOSIT_OFFSET);

  private  ArmExtension armExtender;

  private  ExtendArm extendArm;

  private  RetractArm retractArm;

  private  ClawWrist twistyWrist;

  private  TwistWrist twistWrist;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    lc.startVision();
    if(Constants.DOES_ARM_EXIST){
      armExtender = new ArmExtension();
      armBase = new ArmBase();
      joystick = new Joystick(Constants.ARM_CONTROLLER_PORT);
      aMove = new ArmMove(armBase, joystick, Constants.FORWARD_LIMIT_SWITCH, Constants.BACKWARD_LIMIT_SWITCH);
      claw = new Claw(Constants.CLAW,MotorType.kBrushed);
      clawWrist = new ClawUpDown(Constants.CLAW_WRIST,MotorType.kBrushed);
      cOpen = new ClawOpen(claw);
      cClose = new ClawClose(claw);
      cStop = new ClawStop(claw);
      wDown = new WristDown(clawWrist);
      wStop = new WristStop(clawWrist);
      wUp = new WristUp(clawWrist);
      extendArm = new ExtendArm(armExtender, joystick, Constants.EXTEND_LIMIT_SWITCH);
      retractArm = new RetractArm(armExtender, joystick, Constants.RETRACT_LIMIT_SWITCH);
      twistyWrist = new ClawWrist();
      twistWrist = new TwistWrist(twistyWrist, joystick);
      button9 = new Trigger(() -> joystick.getRawButton(9));
      button11 = new Trigger(() -> joystick.getRawButton(11));

    }
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

    // warning saying can't find button 1 on joystick....idk why....not working for any of the buttons
    // m_driverController.b().onTrue(new DriveTurnTowardsDirection(driveTrain)).onFalse(driveJoystick);
    // m_driverController.a().onTrue(new AutoBalance(driveTrain)).onFalse(driveJoystick);

    // m_driverController.start().onTrue(new SetDriveMode(driveTrain, true));
    // m_driverController.start().onFalse(new DriveJoystick(driveTrain, m_driverController.getHID()));
    // m_driverController.back().onTrue(new SetDriveMode(driveTrain, false));
    // m_driverController.back().onFalse(new DriveJoystick(driveTrain, m_driverController.getHID()));

    
    // m_driverController.povUp().onTrue(cubeAlign).onFalse(driveJoystick);
    // m_driverController.povLeft().onTrue(leftConeAlign).onFalse(driveJoystick);
    // m_driverController.povRight().onTrue(rightConeAlign).onFalse(driveJoystick);

    if (Constants.DOES_ARM_EXIST) {
      button9.whileTrue(extendArm);
      button11.whileTrue(retractArm);
    }

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
  public DriveJoystick getdriveJoystick() {
    return driveJoystick;
  }

  public DriveTrain getDriveTrain(){
    return driveTrain;
  }


  public Joystick getArmController(){
    return joystick;
  }

  public CommandXboxController getController() {
    return m_driverController;
  }

  public LimelightCam getLimelightCam(){
    return limeLight;
  }

  public TwistWrist getTwistWrist() {
    return twistWrist;
  }

  public ArmMove getArmMove(){
    return aMove;
  }

  public ClawClose getClawClose(){
    return cClose;
  }

  public ClawOpen getClawOpen(){
    return cOpen;
  }

  public ClawStop getClawStop(){
    return cStop;
  }

  public WristDown getWristDown(){
    return wDown;
  }

  public WristStop getWristStop(){
    return wStop;
  }

  public WristUp getWristUp(){
    return wUp;
  }

  public ExtendArm getExtendArm() {
    return extendArm;
  }

  public RetractArm getRetractArm() {
    return retractArm;
  }

  //Autonomous Commands
  public Command getInitialMoveAutonomous(){
    if(Constants.DOES_ARM_EXIST){
      return Autos.initialMove(armBase, claw, driveTrain);
    }
    return Autos.initialMove(driveTrain);
  }
}
