// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton2021.BallAuton2021;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MecanumDrivetrain;

public class FinishSearch2021 extends CommandBase {
  AHRS ahrs = MecanumDrivetrain.ahrs;
  Timer timer;
  /** Creates a new FinishSearch2021. */
  public FinishSearch2021(MecanumDrivetrain mecanumDrivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mecanumDrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(ahrs.getAngle() > 10){
      MecanumDrivetrain.mecDrive.driveCartesian(0, 0, -0.5);
    }
    else{
      timer.start();
      if(timer.get() < 2){
        MecanumDrivetrain.mecDrive.driveCartesian(0, 1, 0);
      }
      else{
        MecanumDrivetrain.mecDrive.driveCartesian(0, 0, 0);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    MecanumDrivetrain.mecDrive.driveCartesian(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timer.get() == 2){
      return true;
    }
    return false;
  }
}
