// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class DriveTrain extends SubsystemBase {
  private MecanumDrive mecanumDrive;
  private WPI_TalonFX L1;
  private WPI_TalonFX L2;
  private WPI_TalonFX R1;
  private WPI_TalonFX R2;
  private AHRS navX;
  private boolean brakeMode;
  private boolean isFieldOriented;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    L1 = new WPI_TalonFX(Constants.LEFT_FRONT_DRIVE);
    L2 = new WPI_TalonFX(Constants.LEFT_BACK_DRIVE);
    R1 = new WPI_TalonFX(Constants.RIGHT_FRONT_DRIVE);
    R2 = new WPI_TalonFX(Constants.RIGHT_BACK_DRIVE);
    navX = new AHRS(edu.wpi.first.wpilibj.SPI.Port.kMXP);
    brakeMode = false;
    isFieldOriented = true;
    navX.reset();

    R1.setInverted(true);
    R2.setInverted(true);
    updateBrakeMode();

    mecanumDrive = new MecanumDrive(L1, L2, R1, R2);
  }

  public void driveMecanum(XboxController controller) {
    double driveFactor = 1;
    if (brakeMode)
      driveFactor = 0.3;

    if (isFieldOriented) {
      // With XBox controller, need negative X, Z, and NavXangle
      mecanumDrive.driveCartesian(-controller.getLeftY() * driveFactor,
          controller.getLeftX() * driveFactor,
          controller.getRightX()  * driveFactor / 2,
          new Rotation2d(Math.toRadians(navX.getAngle())));
      } else {
        mecanumDrive.driveCartesian(controller.getLeftY() * driveFactor, 
          -controller.getLeftX() * driveFactor, controller.getRightX() * driveFactor / 2);
      }
      
      L1.feed();
      L2.feed();
      R1.feed();
      R2.feed();
    }
    
    public void driveMecanumField(double xSpeed, double ySpeed, double zRotation){
      mecanumDrive.driveCartesian(-xSpeed, ySpeed, zRotation, new Rotation2d(Math.toRadians(navX.getAngle())));
    }
    
    private void updateBrakeMode() {
      System.out.println("brakeMode is "+brakeMode);
      if (brakeMode){
        L1.setNeutralMode(NeutralMode.Brake);
        L2.setNeutralMode(NeutralMode.Brake);
        R1.setNeutralMode(NeutralMode.Brake);
        R2.setNeutralMode(NeutralMode.Brake);
      } else {
        L1.setNeutralMode(NeutralMode.Coast);
        L2.setNeutralMode(NeutralMode.Coast);
        R1.setNeutralMode(NeutralMode.Coast);
        R2.setNeutralMode(NeutralMode.Coast);
      }
    }

    public boolean getBrakeMode(){
      return brakeMode;
    }
    
    public void setBrakeMode(boolean brakeMode){
      this.brakeMode=brakeMode;
      updateBrakeMode();
    }

    public void setDriveMode(boolean fieldOriented){
      isFieldOriented=fieldOriented;
    }

  // allows for driving through set values rather than a controller
  public void driveMecanum(double xSpeed, double ySpeed, double zRotation) {
    mecanumDrive.driveCartesian(-xSpeed, ySpeed, zRotation);

    L1.feed();
    L2.feed();
    R1.feed();
    R2.feed();
  }

  public void driveTest(double x, double y, double z) {
    double driveFactor = 1;
    if (brakeMode)
      driveFactor = 1;

    if (isFieldOriented) {
      // With XBox controller, need negative X, Z, and NavXangle
      mecanumDrive.driveCartesian(-x * driveFactor,
          y * driveFactor,
          z  * driveFactor / 2,
          new Rotation2d(Math.toRadians(navX.getAngle())));
      } else {
        mecanumDrive.driveCartesian(x * driveFactor, 
          -y * driveFactor, z * driveFactor / 2);
      }

      
      L1.feed();
      L2.feed();
      R1.feed();
      R2.feed();
    
    // mecanumDrive.driveCartesian(-x, y, z);
  }

  public AHRS getNavX() {
    return navX;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
