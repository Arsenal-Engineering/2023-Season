
package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cscore.UsbCamera;

public class LifeCam extends SubsystemBase {
  UsbCamera lifeCamArm;

  public void startVision() {
    lifeCamArm = CameraServer.startAutomaticCapture(0);
    lifeCamArm.setResolution(160, 120);
    lifeCamArm.setFPS(10);
  }
}
