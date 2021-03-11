/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.*;

import frc.robot.commands.auton2021.SequentialAuton2021;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;




/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  public static Harvester m_harvester = new Harvester();
  public static Conveyor m_conveyor = new Conveyor();
  public static Shooter m_shooter = new Shooter();
  public final static MecanumDrivetrain m_mecanumDrivetrain = new MecanumDrivetrain();
  //public static Turret m_turret = new Turret();

  //The robot's commands

  public SequentialAuton2021 m_seqAuton2021 = new SequentialAuton2021();

  //public static TurnTurret m_turnTurret;
  public static RunHarvester m_runHarvester;

  public final static Joystick leftJoystick = new Joystick(0);
  public final static JoystickButton autoAimButton = new JoystickButton(leftJoystick, 0);

  public final static Joystick rightJoystick = new Joystick(1);
  public final static JoystickButton shootButton = new JoystickButton(rightJoystick, 0);
  public final static JoystickButton feedButton = new JoystickButton(rightJoystick, 1);

  public final static Joystick operatorJoystick = new Joystick(2);
  public final static JoystickButton harvesterArmButton = new JoystickButton(operatorJoystick, 8);
  public final static JoystickButton harvesterMotorButton = new JoystickButton(operatorJoystick, 6);
  
  public final static POVButton reverseConveyorButton = new POVButton(operatorJoystick, 90);
   
  static boolean isPressed;

  public static boolean checkPOV(Joystick joystick) {
    if (joystick.getPOV() == -1) {
      isPressed = false;
    }
    else{
      isPressed = true;
    }
    return isPressed;
  }
  // @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    new DriveMecanum(m_mecanumDrivetrain);
  }

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    MecanumDrivetrain.ahrs.calibrate();
  }
  
  public Command getAutonomousCommand(){
    return m_seqAuton2021;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }
}

