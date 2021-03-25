// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton2021.BallAuton2021;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MecanumDrivetrain;

public class Search2021 extends CommandBase {
  public static double a;
  public static double v;
  public static Timer timer;
  public static Timer stepTime;
  /** Creates a new Search2021. */
  public Search2021(MecanumDrivetrain mecanumDrivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mecanumDrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    stepTime = new Timer();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    a = MecanumDrivetrain.ta.getDouble(0.0);
    v = MecanumDrivetrain.tv.getDouble(0);

    if(v ==1){
      MecanumDrivetrain.mecDrive.driveCartesian(0, 0.2, -MecanumDrivetrain.horizontalAuto());
    }
    else{
      timer.start();
      if(timer.get() <= 1.5){
        MecanumDrivetrain.mecDrive.driveCartesian(0, 0, -0.25);
      }
      else if(timer.get() <= 3.5){
        MecanumDrivetrain.mecDrive.driveCartesian(0, 0, 0.25);
      }
      else{
        timer.reset();
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    stepTime.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(stepTime.get() >= 3){
      return true;
    }
    return false;
  }
}
