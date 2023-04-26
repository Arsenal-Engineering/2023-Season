package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;


public class Rumble extends CommandBase {
  private Timer timer = new Timer();

  private final CommandXboxController joystick;
  private final double intensity;
  private final double duration;

  public Rumble(CommandXboxController joystick, double duration, double intensity) {
    this.joystick = joystick;
    this.intensity = intensity;
    this.duration = duration;
  }

  @Override
  public void initialize() {
    joystick.getHID().setRumble(RumbleType.kBothRumble, intensity);

    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    joystick.getHID().setRumble(RumbleType.kBothRumble, 0.0);
    
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return timer.get() > duration;
  }
}