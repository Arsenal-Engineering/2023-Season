// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.TestSparkMax;

//import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class ArmBase extends SubsystemBase {
  private final double upReducer = 0.5;
  private final double downReducer = 0.1;
  private TestSparkMax arm_base;
  private DigitalInput limitSwitchBottom;
  private DigitalInput limitSwitchTop;
  // creates a new arm base
  public ArmBase() {
    arm_base = new TestSparkMax(Constants.ARM_BASE,MotorType.kBrushed,"Arm base");
    limitSwitchBottom = new DigitalInput(Constants.FORWARD_LIMIT_SWITCH);
    limitSwitchTop = new DigitalInput(Constants.BACKWARD_LIMIT_SWITCH);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void armUp(double happyStickSpeed) {
    if (!limitSwitchTop.get()){
      arm_base.set(0.0);
    } else {
      arm_base.set(happyStickSpeed*upReducer);
    }
  }
  public void armDown(double happyStickSpeed){ 
    if (!limitSwitchBottom.get()){
      arm_base.set(0.0);
    } else {  
    arm_base.set(happyStickSpeed*downReducer);
    }
  }
  public void armStop() {
    arm_base.set(0.0);
  }
  public void armWhenTheAutonomousIsSusAndGoDownToVent(){
    arm_base.set(downReducer);
  }
}

