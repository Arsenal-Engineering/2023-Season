// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.autonomous_commands.*;

public final class PlaceCube {
  private static final double moveForwardTime = 2.0;
  private static final double raiseArmTime = 1.2;
  private static final double lowerArmTime = 1.5;
  /** Example static factory for an autonomous command. */

  public static CommandBase place(ArmBase armBase, ArmExtension armExtension, Claw claw, DriveTrain driveTrain){
    //creates initial move command so robot can sense for hill
    System.out.println("im doing stuff");
    //command sequence
    return Commands.sequence(new ParallelCommandGroup(new AutoExtendArm(armExtension, 2.0),
                                                      new LowerArm(armBase, lowerArmTime)), 
                             new Drop(claw),
                             new ParallelCommandGroup(new AutoRetractArm(armExtension, 2.0),
                                                      new RaiseArm(armBase, raiseArmTime)));
  }  
  
  private PlaceCube(){
    throw new UnsupportedOperationException("This is a utility class!");
  }

  public static CommandBase nothingExists(){
    return Commands.sequence(null);
  }
}
