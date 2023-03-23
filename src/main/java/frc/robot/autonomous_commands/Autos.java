// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous_commands;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;

public final class Autos {
  private static final double moveForwardTime = 2.0;
  private static final double raiseArmTime = 1.15;
  private static final double lowerArmTime = 1.5;
  /** Example static factory for an autonomous command. */

  public static CommandBase initialMove(DriveTrain driveTrain){
    //creates initial move command so robot can sense for hill

    //command sequence
    return Commands.sequence(new Move(driveTrain, 2.0, -Constants.AUTO_SPEED),
                             //runs chargeStationRoute if the robot encounters a hill, otherwise leaves community
                             new ConditionalCommand(new AutonomousBalance(driveTrain), 
                                                    new Move(driveTrain, moveForwardTime , Constants.AUTO_SPEED),
                                                    () -> isHill(driveTrain)));
  }

  public static CommandBase initialMove(ArmBase armBase, ArmExtension armExtension, Claw claw, DriveTrain driveTrain){
    //creates initial move command so robot can sense for hill

    //command sequence
    return Commands.sequence(/*new LowerArm(armBase),
                             new Drop(claw),*/
                             new ParallelCommandGroup(new AutoExtendArm(armExtension, 2.0),
                                                      new LowerArm(armBase, lowerArmTime)), 
                             new Drop(claw),
                             new ParallelCommandGroup(new AutoRetractArm(armExtension, 2.0),
                                                      new RaiseArm(armBase, raiseArmTime), 
                                                      new SequentialCommandGroup(new Delay(driveTrain, 0.5), 
                                                                                 new Move(driveTrain, 1, -Constants.AUTO_SPEED))),
                             //runs chargeStationRoute if the robot encounters a hill, otherwise leaves community
                             new ConditionalCommand(new AutonomousBalance(driveTrain), 
                                                    new Move(driveTrain, 1.5, -Constants.AUTO_SPEED),
                                                    () -> isHill(driveTrain)));
  }  
  public static CommandBase initialMove(ArmBase armBase, Claw claw, DriveTrain driveTrain){
    //creates initial move command so robot can sense for hill

    //command sequence
    return Commands.sequence(/*new LowerArm(armBase),
                             new Drop(claw),*/
                             new LowerArm(armBase, lowerArmTime),
                             new Drop(claw),
                             new RaiseArm(armBase, raiseArmTime),
                             new Move(driveTrain, moveForwardTime, -Constants.AUTO_SPEED),
                             //runs chargeStationRoute if the robot encounters a hill, otherwise leaves community
                             new ConditionalCommand(new AutonomousBalance(driveTrain), 
                                                    new Delay(driveTrain, 0),
                                                    () -> isHill(driveTrain)));
  }
  private Autos(){
    throw new UnsupportedOperationException("This is a utility class!");
  }

  public static boolean isHill(DriveTrain driveTrain){
    //if the robot isn't flat return true
    return (driveTrain.getNavX().getPitch() > 5 || driveTrain.getNavX().getPitch() < -5);
  }

  public static CommandBase nothingExists(){
    return Commands.sequence(null);
  }
}
