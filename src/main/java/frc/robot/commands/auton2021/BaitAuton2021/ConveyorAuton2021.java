/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton2021.BaitAuton2021;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class ConveyorAuton2021 extends CommandBase {
  /**
   * Creates a new ConveyorAuton.
   */
  Timer timer;
  public ConveyorAuton2021(Conveyor conveyor) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    timer.start();
    
    //Conveyor.backConveyor.set(0.8);
  }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while(timer.get()<= 6 && timer.get() >= 2){
      Conveyor.frontConveyor.set(-0.10);
    }
    while(timer.get() > 6){
      Conveyor.frontConveyor.set(-0.9);
    }
  /*Conveyor.frontConveyor.set(0.8);
  Conveyor.backConveyor.set(0.7);*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Conveyor.frontConveyor.set(0);
    timer.stop();
    //Conveyor.backConveyor.set(0);
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