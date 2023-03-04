// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.autonomous_commands.AutoBalance;
import frc.robot.autonomous_commands.Autos;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import edu.wpi.first.wpilibj.Joystick;


/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  // CONTROLLERS AND BUTTONS
  private CommandXboxController m_driverController;

  private Joystick joystick;
  private Trigger button1;
  private Trigger button2;
  private Trigger button9;
  private Trigger button11;

  private Rumble rumble;

  // DRIVE TRAIN
  private DriveTrain driveTrain;
  private DriveJoystick driveJoystick;
  

  // ARM BASE
  private ArmBase armBase;
  private ArmMove aMove;

  // ARM EXTENSION
  private ArmExtension armExtender;
  private ExtendArm extendArm;
  private RetractArm retractArm;

  // CLAW
  private Claw claw;
  private ClawOpen cOpen;
  private ClawClose cClose;

  // VISION
  private LifeCam lc;
  private LimelightCam limeLight;

  private AutoAlign cubeAlign;
  private AutoAlign leftConeAlign;
  private AutoAlign rightConeAlign;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings

    if (Constants.DOES_ARM_EXIST) {
      joystick = new Joystick(Constants.ARM_CONTROLLER_PORT);
      button1 = new Trigger(() -> joystick.getRawButton(1));
      button2 = new Trigger(() -> joystick.getRawButton(2));
      button9 = new Trigger(() -> joystick.getRawButton(9));
      button11 = new Trigger(() -> joystick.getRawButton(11));

      armBase = new ArmBase();
      aMove = new ArmMove(armBase, joystick);

      armExtender = new ArmExtension();
      extendArm = new ExtendArm(armExtender);
      retractArm = new RetractArm(armExtender);

      claw = new Claw(Constants.CLAW, MotorType.kBrushed);
      cOpen = new ClawOpen(claw);
      cClose = new ClawClose(claw);
    }

    if (Constants.DOES_DRIVETRAIN_EXIST) {
      m_driverController =  new CommandXboxController(OperatorConstants.kDriverControllerPort);
      driveTrain = new DriveTrain();

      driveJoystick = new DriveJoystick(driveTrain, m_driverController.getHID());

      rumble = new Rumble(m_driverController, 1.0, 0.9);


      //lc = new LifeCam();
      //lc.startVision();

      //limeLight = new LimelightCam();
      cubeAlign = new AutoAlign(driveTrain, limeLight, 0);
      leftConeAlign = new AutoAlign(driveTrain, limeLight, Constants.CONE_DEPOSIT_OFFSET);
      rightConeAlign = new AutoAlign(driveTrain, limeLight, -Constants.CONE_DEPOSIT_OFFSET);
    }

    configureBindings();
  }

  private void configureBindings() {

    // warning saying can't find button 1 on joystick....idk why....not working for
    // any of the buttons

    if (Constants.DOES_DRIVETRAIN_EXIST) {
      //m_driverController.b().onTrue(new DriveTurnTowardsDirection(driveTrain)).onFalse(driveJoystick);
      //m_driverController.a().onTrue(new AutoBalance(driveTrain)).onFalse(driveJoystick);

      //m_driverController.start().onTrue(new SetDriveMode(driveTrain, true)).onFalse(driveJoystick);
      //m_driverController.back().onTrue(new SetDriveMode(driveTrain, false)).onFalse(driveJoystick);

      // m_driverController.povUp().onTrue(cubeAlign).onFalse(driveJoystick);
      // m_driverController.povLeft().onTrue(leftConeAlign).onFalse(driveJoystick);
      // m_driverController.povRight().onTrue(rightConeAlign).onFalse(driveJoystick);
    }

    if (Constants.DOES_ARM_EXIST) {
      button1.whileTrue(cClose);
      button2.whileTrue(cOpen);
      button9.whileTrue(extendArm);
      button11.whileTrue(retractArm);
    }

  }

  public CommandXboxController getXboxController() {
    return m_driverController;
  }

  public DriveJoystick getDriveJoystick() {
    return driveJoystick;
  }

  public DriveTrain getDriveTrain() {
      return driveTrain;
  }

  public Joystick getArmController() {
    return joystick;
  }

  public CommandXboxController getController() {
    return m_driverController;
  }

  public LimelightCam getLimelightCam() {
    return limeLight;
  }

  public ArmMove getArmMove() {
    return aMove;
  }

  public Rumble getRumble() {
    return rumble;
  }

  // Autonomous Commands
  public Command getInitialMoveAutonomous() {
    if (Constants.DOES_ARM_EXIST && Constants.DOES_DRIVETRAIN_EXIST) {
      return Autos.initialMove(armBase, claw, driveTrain);
    }
    if (Constants.DOES_DRIVETRAIN_EXIST) {
      return Autos.initialMove(driveTrain);
    }
    return Autos.nothingExists();
  }
}
