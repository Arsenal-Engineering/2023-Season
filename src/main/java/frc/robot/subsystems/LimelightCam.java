// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
//import edu.wpi.first.apriltag.*;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import org.photonvision.*;
import org.photonvision.targeting.PhotonPipelineResult;

public class LimelightCam extends SubsystemBase {
  // creates a camera object, requires a camera with photonvision firmware
  // installed
  private PhotonCamera camera;
  private PhotonPipelineResult latestResult;

  /** Creates a new LimelightCam. */
  public LimelightCam() {
    // sets camera using the name given in the photonvision HUD
    camera = new PhotonCamera("Limelight6223");
    // scans for targets and stores it in a pipeline
    latestResult = camera.getLatestResult();
  }

  @Override
  public void periodic() {
  }

  // returns an x/y pair representing distance to target
  public Translation2d getTranslation() {
    latestResult = camera.getLatestResult();
    // checks if the camera sees any valid targets, then runs calculations
    if (latestResult.hasTargets()) {
      // gets distance from camera to closest target
      double distance = PhotonUtils.calculateDistanceToTargetMeters(Constants.LIMELIGHT_HEIGHT,
          Constants.DEPOSIT_TAG_HEIGHT,
          0,
          Units.degreesToRadians(latestResult.getBestTarget().getPitch()));
      // uses distance to create a translation2d object, represents a 2d vector
      Translation2d translation = PhotonUtils.estimateCameraToTargetTranslation(distance,
          Rotation2d.fromDegrees(-latestResult.getBestTarget().getYaw()));
      return translation;
    }
    return null;
  }

}
