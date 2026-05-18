// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import org.wpilib.units.VelocityUnit;
import org.wpilib.units.measure.AngularVelocity;
import org.wpilib.units.measure.LinearVelocity;
import org.wpilib.units.measure.Velocity;



/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    public static final int kLeftMotor1Port = 0;
    public static final int kLeftMotor2Port = 1;
    public static final int kRightMotor1Port = 2;
    public static final int kRightMotor2Port = 3;

    public static final int[] kLeftEncoderPorts = new int[] {0, 1};
    public static final int[] kRightEncoderPorts = new int[] {2, 3};
    public static final boolean kLeftEncoderReversed = false;
    public static final boolean kRightEncoderReversed = true;

    public static final int kEncoderCPR = 1024;
    public static final double kWheelDiameterInches = 6;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterInches * Math.PI) / kEncoderCPR;
  }

  public static final class HatchConstants {
    public static final int kHatchSolenoidModule = 0;
    public static final int[] kHatchSolenoidPorts = new int[] {0, 1};
  }

  public static final class AutoConstants {
    public static final double kAutoDriveDistanceInches = 60;
    public static final double kAutoBackupDistanceInches = 20;
    public static final double kAutoDriveSpeed = 0.5;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static final class IntakeConstants {
    public static final int motorID = 0;
    public static final double intakePower = 0.5;
    public static final double feedPower = 0.2;
    public static final double idlePower = 0.0;
    
  }

  public static final class IndexterConstants {
    public static final int leftMotorId = 1;
    public static final int rightMotorId = 2;
    public static final double feedPower = 1.0;
    public static final double idlePower = 0.1;
  }

  public static final class LauncherSubsystem {
    public static final int motorID = 0;
    // Target launcher speed in revolutions per minute (RPM).
    public static final double TARGET_VELOCITY = 1600.0;
    public static final double IDLE_VELOCITY = 400.0;
    public static final double MIN_VELOCITY = TARGET_VELOCITY - (TARGET_VELOCITY * 0.1);

    //PID values
    public static final double kp = 0;
    public static final double ki = 0;
    public static final double kd = 0;
    // Physical / encoder configuration
    public static final double SHOOTER_RADIUS_METERS = 0.0762; // example: 3 in = 0.0762 m
    public static final int ENCODER_CPR = 1024; // encoder counts per motor revolution
    public static final double GEAR_RATIO = 1.0; // motor revs per output rev

    // Feedforward gains (tune these)
    public static final double kS = 0.0;
    public static final double kV = 0.0; // V per (rad/s)
    public static final double kA = 0.0; // V per (rad/s^2)

    public static final double MAX_VOLTAGE = 12.0;
    public static final double TOLERANCE_RPM = 25.0; // acceptable error in RPM
  }
}
