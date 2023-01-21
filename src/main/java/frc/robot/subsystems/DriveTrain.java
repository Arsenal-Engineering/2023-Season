// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class DriveTrain extends SubsystemBase {
  private MecanumDrive mecanumDrive;
  private DifferentialDrive differentialDrive;
  private WPI_TalonFX L1;
  private WPI_TalonFX L2;
  private WPI_TalonFX R1;
  private WPI_TalonFX R2;
  private AHRS navX;
  private String driveDirectionalMode;
  private boolean driveSlow;
  private boolean funkyMode;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    L1 = new WPI_TalonFX(Constants.LEFT_FRONT_DRIVE);
    L2 = new WPI_TalonFX(Constants.LEFT_BACK_DRIVE);
    R1 = new WPI_TalonFX(Constants.RIGHT_FRONT_DRIVE);
    R2 = new WPI_TalonFX(Constants.RIGHT_BACK_DRIVE);
    navX = new AHRS(edu.wpi.first.wpilibj.SPI.Port.kMXP);
    driveDirectionalMode = "navX";
    driveSlow = true;
    funkyMode = false;
    navX.reset();
    
    L2.setInverted(true);
    R2.setInverted(true);

    L1.setNeutralMode(NeutralMode.Brake);
    L2.setNeutralMode(NeutralMode.Brake);
    R1.setNeutralMode(NeutralMode.Brake);
    R1.setNeutralMode(NeutralMode.Brake);

    mecanumDrive = new MecanumDrive(L1, L2, R1, R2);
    }
  
    public void driveMecanum(XboxController controller) {
      double driveFactor = 1;
      if (driveSlow) driveFactor = 0.8;
  
      // With XBox controller, need negative X, Z, and NavXangle
      if (funkyMode){
        mecanumDrive.driveCartesian(0, 0, 0.25);
      } else {
        mecanumDrive.driveCartesian(-controller.getLeftY() * driveFactor, 
          controller.getLeftX() * driveFactor,controller.getRightX()/2.0, new Rotation2d(Math.toRadians(navX.getAngle())));
      }
    }

    public void driveTest(double x, double y, double z) {
      mecanumDrive.driveCartesian(x, y, z);
    }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}