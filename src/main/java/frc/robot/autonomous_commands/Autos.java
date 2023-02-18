// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous_commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import frc.robot.subsystems.*;

public final class Autos {
  /** Example static factory for an autonomous command. */

  public static CommandBase initialMove(DriveTrain driveTrain){
    //creates initial move command so robot can sense for hill

    //command sequence
    return Commands.sequence(new Move(driveTrain, 2.4, -0.75),
                             new Delay(driveTrain,.5),
                             //runs chargeStationRoute if the robot encounters a hill, otherwise leaves community
                             new ConditionalCommand(chargeStationRoute(driveTrain), 
                                                    new Move(driveTrain, 1.0 ,0.5),
                                                    () -> isHill(driveTrain)));
  }

  public static CommandBase initialMove(ArmBase armBase, Claw claw, DriveTrain driveTrain){
    //creates initial move command so robot can sense for hill

    //command sequence
    return Commands.sequence(new LowerArm(armBase),
                             new Drop(claw),
                             new Move(driveTrain, 2.4, -0.75),
                             new Delay(driveTrain,.5),
                             //runs chargeStationRoute if the robot encounters a hill, otherwise leaves community
                             new ConditionalCommand(chargeStationRoute(driveTrain), 
                                                    new Move(driveTrain, 1.0 ,0.5),
                                                    () -> isHill(driveTrain)));
  }

  //code for command path if robot runs over charge station during autonomous
  private static CommandBase chargeStationRoute(DriveTrain driveTrain){
    return Commands.sequence(new MoveOverChargeStation(driveTrain),
                             new Delay(driveTrain, 0.25),
                             new MoveOverChargeStation(driveTrain), //moves until robot is flat
                             new Delay(driveTrain, 0.5),
                             new Move(driveTrain, 0.5, 0.75), //drives back to charge station
                             new AutoBalance(driveTrain)); //balances on charge station
  }
  
  private Autos(){
    throw new UnsupportedOperationException("This is a utility class!");
  }

  public static boolean isHill(DriveTrain driveTrain){
    //if the robot isn't flat return true
    return (driveTrain.getNavX().getPitch() > 5 || driveTrain.getNavX().getPitch() < -5);
  }
}
