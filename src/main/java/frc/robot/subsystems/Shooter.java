/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.RunShooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  public static CANSparkMax shootingFrontMotor;
  public static CANSparkMax shootingBackMotor;

  public static CANEncoder shooterEncoder;
  static double deviser;

  public static CANSparkMax verticalMotor;
  public static CANEncoder verticalEncoder;

  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public static NetworkTableEntry tv = table.getEntry("tv");
  public static NetworkTableEntry ta = table.getEntry("ta");

  //work in progress variables for distance//
  //public static double a1 = 83.9875579;
  //public static double aSum = a1 + a2;

  public static double difference = 41.9375;

  public Shooter() {
    shootingFrontMotor = new CANSparkMax(40, MotorType.kBrushless);
    shootingBackMotor = new CANSparkMax(41, MotorType.kBrushless);
    shooterEncoder = shootingFrontMotor.getEncoder();

    verticalMotor = new CANSparkMax(50, MotorType.kBrushless);
    verticalEncoder = verticalMotor.getEncoder();
  }
  @Override
  
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new RunShooter(RobotContainer.m_shooter));

    //calculateDistance();
    //System.out.println("Distance: " + calculateDistance());
  }

 /* public double calculateDistance(){
    double distance;
    //If we can see the target, calulate the distance to the target.
    if(visibleTarget == 1){
    deviser = Math.tan(aSum);
    distance = difference / deviser;
    }
    else{
      distance = 0;
    }
    return distance;
  }*/
  public static void autoVertical(){
    double a = ta.getDouble(0.0);
    double v = tv.getDouble(0);

    //Encoder values
    double currentPos = verticalEncoder.getPosition();
    double maxIncline = -110;
    double maxDecline = 0;

    double closestPos = -70.716;
    double middlePos = -27.4;
    double farthestPos = -1;

    double middleArea = 1.219;
    double farthestArea = 0.655;
    double closestArea = 2.324;

    //double kP = 0.009;
    //double movementError = 0;
    double motorAdjust = 0;

    if (maxIncline > currentPos || currentPos < maxDecline){
      if(a > closestArea - 0.4 && a < closestArea + 0.4){
          if(closestPos - 5 < currentPos){
            motorAdjust = -0.25;
          }
          else if(closestPos - 5 > currentPos){
            motorAdjust = 0.25;
          }
          else{
            motorAdjust = 0;
          }
      }
      else if (a > middleArea - 0.4 && a < closestArea + 0.2){
        if(middlePos - 5 < currentPos){
          motorAdjust = -0.25;
        }
        else if(middlePos - 5 > currentPos){
          motorAdjust = 0.25;
        }
        else{
          motorAdjust = 0;
        }
      }
      else if(a > farthestArea - 0.2 && a < farthestArea + 0.2){
        if(farthestPos - 5 < currentPos){
          motorAdjust = -0.25;
        }
        else if(farthestPos - 5 > currentPos){
          motorAdjust = 0.25;
        }
        else{
          motorAdjust = 0;
        }
      }
      else{
        if(v == 0)
        {
          motorAdjust = -0.1;
        }
      }
      verticalMotor.set(motorAdjust);
      SmartDashboard.putNumber("motorAdjust", motorAdjust);
    }
    else{
      if(maxIncline < currentPos){
        verticalMotor.set(-0.1);
      }
      else{
        verticalMotor.set(0.1);
      }
    }
  }
}
