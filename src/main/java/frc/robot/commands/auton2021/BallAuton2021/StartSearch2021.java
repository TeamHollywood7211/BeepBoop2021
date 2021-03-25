// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton2021.BallAuton2021;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Harvester;
import frc.robot.subsystems.MecanumDrivetrain;
import edu.wpi.first.wpilibj.Timer;

public class StartSearch2021 extends CommandBase {
  public static double a;
  public static double v;
  public static Timer timer;
  public static Timer elevatorTimer;
  public static Timer stepTime;
  /** Creates a new StartSearch. */
  public StartSearch2021(MecanumDrivetrain mecanumDrivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mecanumDrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    elevatorTimer= new Timer();
    stepTime = new Timer();
    stepTime.start();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    a = MecanumDrivetrain.ta.getDouble(0.0);
    v = MecanumDrivetrain.tv.getDouble(0);
    Harvester.harvesterMotor.set(1);

    if(v == 1){
      timer.start();
      if(timer.get() >= 0){
        MecanumDrivetrain.mecDrive.driveCartesian(0, 0.1, -MecanumDrivetrain.horizontalAuto());
      }
      else{
        MecanumDrivetrain.mecDrive.driveCartesian(0, 0, 0);
      }
    }
    else{
      timer.reset();
      timer.stop();
      MecanumDrivetrain.mecDrive.driveCartesian(0, 0.1, 0);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Harvester.harvesterMotor.set(0);
    stepTime.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(stepTime.get() >= 7){
      if(elevatorTimer.get()<2){
        Conveyor.frontConveyor.set(1);
      }
      return true;
    }
    return false;
  }
}
