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

  public static CommandBase initialMove(ArmBase armBase, Claw claw, DriveTrain driveTrain){
    //creates initial move command so robot can sense for hill
    Move initialMoveBack = new Move(driveTrain, 3.0, -1.0);
    //command sequence
    return Commands.sequence(new LowerArm(armBase),
                             new Drop(claw),
                             initialMoveBack,
                             //runs chargeStationRoute if the robot encounters a hill, otherwise leaves community
                             new ConditionalCommand(chargeStationRoute(driveTrain), 
                                                    new Move(driveTrain, 1.5, -1.0),
                                                    ()->initialMoveBack.isHill()));
  }

  //code for command path if robot runs over charge station during autonomous
  private static CommandBase chargeStationRoute(DriveTrain driveTrain){
    return Commands.sequence(new MoveOverChargeStation(driveTrain),
                             new MoveOverChargeStation(driveTrain), //moves until robot is flat
                             new Move(driveTrain, 1, -1.0), //leaves community
                             new Move(driveTrain, 2, 1.0), //drives back to charge station
                             new AutoBalance(driveTrain)); //balances on charge station
  }
  
  private Autos(){
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
