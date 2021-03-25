// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveMecanum;

import com.kauailabs.navx.frc.AHRS;

//import com.kauailabs.navx.frc.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.MecanumDrive;


/** Add your docs here. */
public class MecanumDrivetrain extends SubsystemBase
{
    public static BuiltInAccelerometer accel;
    
    public static CANSparkMax fL1;
    public static CANSparkMax fR1;
    public static CANSparkMax bL1;
    public static CANSparkMax bR1;

    public static MecanumDrive mecDrive;
    public static AHRS ahrs;

    public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    public static NetworkTableEntry ta = table.getEntry("ta");
    public static NetworkTableEntry tx = table.getEntry("tx");
    public static NetworkTableEntry ty = table.getEntry("ty");
    public static NetworkTableEntry tv = table.getEntry("tv");

    public MecanumDrivetrain(){
        
        fR1 = new CANSparkMax(10, MotorType.kBrushless);
        bR1 = new CANSparkMax(12, MotorType.kBrushless);
        fL1 = new CANSparkMax( 1, MotorType.kBrushless);
        bL1 = new CANSparkMax(3, MotorType.kBrushless);
        
        ahrs = new AHRS(SPI.Port.kMXP);
        accel = new BuiltInAccelerometer();

        final SpeedControllerGroup frontLeft = new SpeedControllerGroup(fL1);
        //frontLeft.setInverted(false);
        final SpeedControllerGroup frontRight = new SpeedControllerGroup(fR1);
        //frontRight.setInverted(true);
        final SpeedControllerGroup backLeft = new SpeedControllerGroup(bL1);
        //backLeft.setInverted(false);
        final SpeedControllerGroup backRight = new SpeedControllerGroup(bR1);
        //backRight.setInverted(true);

        mecDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
        mecDrive.setSafetyEnabled(false);
    }

    public static void driveByJoystick(){
        if(RobotContainer.autoAimButton.get()){
            
            driveCarte(0, 0, -horizontalAuto());
            Shooter.autoVertical();
        }
        else{
            driveCarte(returnLeftAxis(0), -returnLeftAxis(1), returnRightAxis(0));
        }
    }
    public static void driveCarte(double ySpeed, double xSpeed, double zRotation){
        mecDrive.driveCartesian(ySpeed, xSpeed, zRotation, -ahrs.getAngle());
    }

    public static double deadzone = 0.3;
    public static double returnLeftAxis(int leftAxis){
        double leftStick = RobotContainer.leftJoystick.getRawAxis(leftAxis);
        if(leftStick > deadzone || leftStick < -deadzone){
             return leftStick;
        }
        else{
            return 0;
       }
    }
    public static double returnRightAxis(int rightAxis){
        double rightStick = RobotContainer.rightJoystick.getRawAxis(rightAxis);
        if(rightStick > deadzone/2 || rightStick < -deadzone/2){
            return rightStick;
       }
       else{
            return 0;
       }
      
    }
    @Override
    public void periodic(){
        setDefaultCommand(new DriveMecanum(RobotContainer.m_mecanumDrivetrain));
    }

    public static double getRoll(){
        double X = accel.getX();
        double Y = accel.getY();
        double Z = accel.getZ();
        return Math.atan2(-X, Math.sqrt(Y*Y + Z*Z)) * 180/Math.PI;
    }
    
    public static double horizontalAuto(){
        double Kp = 0.0225; // Proportional control constant
        double x = tx.getDouble(0.0);
        double v = tv.getDouble(0);
        double horizontalError = -x;
        double chassisAdjust = 0;
        double targetX = 0;
    
        // If A is held down, run a PID loop to center the turret.
        if(v == 1){
            if (x > targetX) {
                chassisAdjust = Kp * horizontalError;
            } 
            else if (x < targetX) {
                chassisAdjust = Kp * horizontalError;
            }
        }
        return chassisAdjust;
    }
    
}