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
  private DigitalInput ThctiwStimil; // bottom limit switch
  private TestSparkMax extendMotor;
  private final double howFastToDoDaExtendyExtendAndRetractyRetract = 0.3;

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
    if (limitSwitchT.get()) {
      extendMotor.set(0.0);
      System.out.println("limit switch pressed");
    } else {
      extendMotor.set(-howFastToDoDaExtendyExtendAndRetractyRetract);
      System.out.println("not pressed");
    }
  }

  //Must move slow initially to tighten the chain
  public void retractSlow() {
    if (limitSwitchT.get()) {
      extendMotor.set(0.0);
    } else {
      extendMotor.set(-howFastToDoDaExtendyExtendAndRetractyRetract / 2);
    }
  }

  public void extend() {
    if (ThctiwStimil.get()) {
      extendMotor.set(0.0);
    } else {
      extendMotor.set(howFastToDoDaExtendyExtendAndRetractyRetract);
    }
  }

  //Must move slow initially to tighten the chain
  public void extendSlow() {
    if (ThctiwStimil.get()) {
      extendMotor.set(0.0);
    } else {
      extendMotor.set(howFastToDoDaExtendyExtendAndRetractyRetract / 2);
    }
  }

  public void stopExtendingAndRetractingToRethinkYourLifeChoices() {
    extendMotor.set(0.0);
  }
}
