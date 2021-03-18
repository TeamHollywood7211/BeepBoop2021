// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton2021;

import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MecanumDrivetrain;
import frc.robot.subsystems.Shooter;

public class AutoAim2021 extends CommandBase {
  Timer timer;
  /** Creates a new AutoAim2021. */
  public AutoAim2021(MecanumDrivetrain mecanumDrive) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mecanumDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    MecanumDrivetrain.horizontalAuto();
    while(timer.get() <= 1){
      Shooter.verticalMotor.set(0.10);
    }
    while(timer.get() < 2){
      Shooter.verticalMotor.set(-0.05);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    Shooter.verticalMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timer.get() >= 3){
      return true;
    }
    return false;
  }
}
