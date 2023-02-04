// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;

public class ArmExtension extends SubsystemBase {
  private TalonSRX extendMotor;
  /** Creates a new ArmExtension. */
  public ArmExtension() {
    extendMotor = new TalonSRX(Constants.ARM_EXTENDER_MOTOR);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(double speed) {
    extendMotor.set(ControlMode.Velocity,speed);
  }
}
