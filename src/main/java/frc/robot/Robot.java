// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
*/ 
public class Robot extends TimedRobot {
  private Command initialMoveAutonomous;
  private Command chargeStationRoute;
  private Command normalRoute;
  private Timer timer;

  Thread m_visionThread;

  private RobotContainer robotContainer;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    timer = new Timer();

    m_visionThread =
        new Thread(
            () -> {
              // Get the UsbCamera from CameraServer
              UsbCamera camera = CameraServer.startAutomaticCapture();
              // Set the resolution
              camera.setResolution(640, 480);

              // Get a CvSink. This will capture Mats from the camera
              CvSink cvSink = CameraServer.getVideo();
              // Setup a CvSource. This will send images back to the Dashboard
              CvSource outputStream = CameraServer.putVideo("Rectangle", 640, 480);

              // Mats are very memory expensive. Lets reuse this Mat.
              Mat mat = new Mat();

              // This cannot be 'true'. The program will never exit if it is. This
              // lets the robot stop this thread when restarting robot code or
              // deploying.
              while (!Thread.interrupted()) {
                // Tell the CvSink to grab a frame from the camera and put it
                // in the source mat.  If there is an error notify the output.
                if (cvSink.grabFrame(mat) == 0) {
                  // Send the output the error.
                  outputStream.notifyError(cvSink.getError());
                  // skip the rest of the current iteration
                  continue;
                }
                // Put a rectangle on the image
                Imgproc.rectangle(
                    mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
                // Give the output stream a new image to display
                outputStream.putFrame(mat);
              }
            });
    m_visionThread.setDaemon(true);
    m_visionThread.start();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    initialMoveAutonomous = robotContainer.getInitialMoveAutonomous();
    if (initialMoveAutonomous != null) {
      initialMoveAutonomous.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (initialMoveAutonomous != null) {
      initialMoveAutonomous.cancel();
    }
    if (chargeStationRoute != null) {
      chargeStationRoute.cancel();
    }
    if (normalRoute != null) {
      normalRoute.cancel();
    }

    if (Constants.DOES_DRIVETRAIN_EXIST) {
      robotContainer.getDriveTrain().setBrakeMode(false);
      robotContainer.getDriveJoystick().schedule();
      robotContainer.getRumble().schedule();
    }

    if (Constants.DOES_ARM_EXIST) {
      robotContainer.getArmMove().schedule();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //System.out.println(Math.sin((robotContainer.getDriveTrain().getNavX().getPitch() - Constants.NAVX_PITCH_OFFSET) * 0.9 * (Math.PI / 180.0) - 0.1) * -0.5);
    //System.out.println(robotContainer.getDriveTrain().getNavX().getPitch()-Constants.NAVX_PITCH_OFFSET);
    //System.out.println(robotContainer.getaAutoBalance().getStatus());
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();

    timer.reset();
    timer.start();

    // robotContainer.getController().a().whileTrue(new TestButtons());
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
    // if (initialMoveAutonomous != null) {
    //   initialMoveAutonomous.cancel();
    // }
    // if (chargeStationRoute != null) {
    //   chargeStationRoute.cancel();
    // }
    // if (normalRoute != null) {
    //   normalRoute.cancel();
    // }

    if (Constants.DOES_DRIVETRAIN_EXIST) {
      robotContainer.getDriveTrain().setBrakeMode(false);
      robotContainer.getDriveJoystick().schedule();
      robotContainer.getRumble().schedule();
    }

    if (Constants.DOES_ARM_EXIST) {
      robotContainer.getArmMove().schedule();
    }
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
}
