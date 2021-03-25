// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton2021.BaitAuton2021;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ConveyorAndShooter extends ParallelCommandGroup {
  /** Creates a new ConveyorAndShooter. */
  public ConveyorAndShooter() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ConveyorAuton2021(RobotContainer.m_conveyor), new ShootAuton2021(RobotContainer.m_shooter));
  }
}
