// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  // Depends on whether the physical robot has the arm attached to it
  public static final boolean DOES_ARM_EXIST = true;
  public static final boolean DOES_ARM_EXTENSION_EXIST = true;
  public static final boolean DOES_DRIVETRAIN_EXIST = true;


  // TalonSRX/Falcon motor ID's
  public static final int RIGHT_FRONT_DRIVE = 1;
  public static final int LEFT_FRONT_DRIVE = 2;
  public static final int LEFT_BACK_DRIVE = 3;
  public static final int RIGHT_BACK_DRIVE = 4;

  // Limit switch ids
  public static final int RETRACT_LIMIT_SWITCH = 0; // top
  public static final int EXTEND_LIMIT_SWITCH = 1; // bottom
  public static final int FORWARD_LIMIT_SWITCH = 2; // not using currently
  public static final int BACKWARD_LIMIT_SWITCH = 3; // not using currently

  // Ports for arm
  public static final int ARM_BASE = 10;
  public static final int CLAW = 11;
  public static final int ARM_EXTENDER_MOTOR = 13;

  // Controller ports
  public static final int ARM_CONTROLLER_PORT = 1;

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 2;
  }

  // Constants for auto-aligning
  public static final double DEPOSIT_TAG_HEIGHT = 0.36;
  public static final double LIMELIGHT_HEIGHT = 0.16;
  public static final double CONE_DEPOSIT_OFFSET = 0.5;

  // angle offset
  public static final double NAVX_PITCH_OFFSET = 2;

  //autonomous constants
  public static final double AUTO_SPEED = 0.65;
  public static final double AUTO_ANGLE_TARGET = 10.0;
}
