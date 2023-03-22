// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.TestSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmExtension extends SubsystemBase {
  private DigitalInput limitSwitchT;
  private DigitalInput limitSwitchB; // bottom limit switch
  private TestSparkMax extendMotor;
  private final double speed = 0.5;

  // Creates a new ArmExtension.
  public ArmExtension() {
    extendMotor = new TestSparkMax(Constants.ARM_EXTENDER_MOTOR, MotorType.kBrushed, "Arm extender");
    limitSwitchT = new DigitalInput(Constants.RETRACT_LIMIT_SWITCH);
    limitSwitchB = new DigitalInput(Constants.EXTEND_LIMIT_SWITCH); // also bottom limit switch
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void retract() {
    if (limitSwitchT.get()) {
      extendMotor.set(0.0);
    } else {
      extendMotor.set(-speed);
    }
  }

  //Must move slow initially to tighten the chain
  public void retractSlow() {
    if (limitSwitchT.get()) {
      extendMotor.set(0.0);
    } else {
      extendMotor.set(-speed / 2);
    }
  }

  public void extend() {
    if (limitSwitchB.get()) {
      extendMotor.set(0.0);
    } else {
      extendMotor.set(speed);
    }
  }

  //Must move slow initially to tighten the chain
  public void extendSlow() {
    if (limitSwitchB.get()) {
      extendMotor.set(0.0);
    } else {
      extendMotor.set(speed / 2);
    }
  }

  public void stopExtension() {
    extendMotor.set(0.0);
  }

  public boolean getTopLimSwitch(){
    return limitSwitchT.get();
  }

  public boolean getBotLimSwitch(){
    return limitSwitchB.get();
  }
}
