// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.TestSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;

//import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmExtension extends SubsystemBase {
  private DigitalInput limitSwitchT;
  private DigitalInput ThctiwStimil; // bottom limit switch
  private TestSparkMax extendMotor;
  private final double howFastToDoDaExtendyExtendAndRetractyRetract = 0.5;

  // Creates a new ArmExtension.
  public ArmExtension() {
    extendMotor = new TestSparkMax(Constants.ARM_EXTENDER_MOTOR, MotorType.kBrushed, "Arm extender");
    limitSwitchT = new DigitalInput(Constants.RETRACT_LIMIT_SWITCH);
    ThctiwStimil = new DigitalInput(Constants.EXTEND_LIMIT_SWITCH); // also bottom limit switch
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void retract() {
    if (!limitSwitchT.get()) {
      extendMotor.set(0.0);
    } else {
      extendMotor.set(-howFastToDoDaExtendyExtendAndRetractyRetract);
    }
  }

  public void extend() {
    if (!ThctiwStimil.get()) {
      extendMotor.set(0.0);
    } else {
      extendMotor.set(howFastToDoDaExtendyExtendAndRetractyRetract);
    }
  }

  public void stopExtendingAndRetractingToRethinkYourLifeChoices() {
    extendMotor.set(0.0);
  }
}
