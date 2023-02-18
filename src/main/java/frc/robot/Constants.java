// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    
  //TalonSRX/Falcon motor ID's
    public static final int LEFT_FRONT_DRIVE = 2;
    public static final int RIGHT_FRONT_DRIVE = 1;
    public static final int LEFT_BACK_DRIVE = 3;
    public static final int RIGHT_BACK_DRIVE = 4;
  //change ports
    public static final int EXTEND_LIMIT_SWITCH = 98;
    public static final int RETRACT_LIMIT_SWITCH = 99;
    public static final int FORWARD_LIMIT_SWITCH = 69;
    public static final int BACKWARD_LIMIT_SWITCH = 1;

  //constants for auto-aligning
  public static final double DEPOSIT_TAG_HEIGHT = 0.36;
  public static final double LIMELIGHT_HEIGHT = 0.16;
  public static final double CONE_DEPOSIT_OFFSET = 0.5;
  public static final int ARM_EXTENDER_MOTOR = 7;
  public static final int WRIST_TWIST = 8;
  public static final int JOYSTICK_PORT = 1;

  //angle offset
  public static final double NAVX_PITCH_OFFSET = 0;



  //SPARKMAX ID for claw (placeholder)
  public static final int CLAW = 10;

  //SPARKMAX ID for claw wrist
  public static final int CLAW_WRIST = 11;




  //SPARKMAX ARM BASE
  public static final int ARM_BASE = 1; //when running on 2022 robot

  public static final int ARM_CONTROLLER_PORT = 1;

  public static final boolean DOES_ARM_EXIST = true;
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kArmControllerPort = 0;
  }
}
