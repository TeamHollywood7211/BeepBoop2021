// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton2021.BaitAuton2021;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootAuton2021 extends CommandBase {
  Timer timer;
  /** Creates a new ShootAuton2021. */
  public ShootAuton2021(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
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
    Shooter.shootingFrontMotor.set(1);
    Shooter.shootingBackMotor.set(-1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Shooter.shootingFrontMotor.set(0);
    Shooter.shootingBackMotor.set(0);
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timer.get() >= 11){
      return true;
    }
    return false;
  }
}
