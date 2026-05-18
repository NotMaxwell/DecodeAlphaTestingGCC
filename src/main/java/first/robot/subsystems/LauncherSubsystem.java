package first.robot.subsystems;

import java.io.Console;
import java.lang.invoke.VolatileCallSite;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;
import org.wpilib.hardware.expansionhub.ExpansionHubMotor;
import org.wpilib.math.controller.PIDController;
import org.wpilib.math.controller.SimpleMotorFeedforward;
import org.wpilib.units.AngularVelocityUnit;
import org.wpilib.units.measure.AngularVelocity;
import org.wpilib.units.measure.Voltage;

import static org.wpilib.units.Units.*;

import first.robot.Constants;

public class LauncherSubsystem extends Mechanism {
    private ExpansionHubMotor m_launcher = new ExpansionHubMotor(0, Constants.LauncherSubsystem.motorID);
    private PIDController velocityController = new PIDController(
        Constants.LauncherSubsystem.kp,
        Constants.LauncherSubsystem.ki,
        Constants.LauncherSubsystem.kd);
    private SimpleMotorFeedforward velocityFeedforward = new SimpleMotorFeedforward(
        Constants.LauncherSubsystem.kS,
        Constants.LauncherSubsystem.kV,
        Constants.LauncherSubsystem.kA);

    public LauncherSubsystem(){
        // distancePerCount = circumference / (CPR * gearRatio)
        double distancePerCount = 2.0 * Math.PI * Constants.LauncherSubsystem.SHOOTER_RADIUS_METERS
            / (Constants.LauncherSubsystem.ENCODER_CPR * Constants.LauncherSubsystem.GEAR_RATIO);
        m_launcher.setDistancePerCount(distancePerCount);
    }

    private AngularVelocity getVelocity() {
        double linearVelocity = m_launcher.getEncoderVelocity(); // meters / second (distancePerCount units)
        double omegaRadPerSec = linearVelocity / Constants.LauncherSubsystem.SHOOTER_RADIUS_METERS;
        return AngularVelocity.ofBaseUnits(omegaRadPerSec, RadiansPerSecond);
    }

    public Command goToRPM(AngularVelocity speed){
        // use radians/sec internally for feedforward and PID
        double setpointRadPerSec = speed.in(RadiansPerSecond);
        velocityController.setSetpoint(setpointRadPerSec);
        return this.run(coroutine -> {
            while (true) {
                double measuredRadPerSec = getVelocity().in(RadiansPerSecond);
                double pidVolts = velocityController.calculate(measuredRadPerSec);
                double ffVolts = velocityFeedforward.calculate(setpointRadPerSec);
                double volts = pidVolts + ffVolts;
                volts = Math.max(-Constants.LauncherSubsystem.MAX_VOLTAGE,
                    Math.min(Constants.LauncherSubsystem.MAX_VOLTAGE, volts));

                m_launcher.setVoltage(Voltage.ofBaseUnits(volts, Volts));

                double errorRPM = Math.abs(getVelocity().in(RPM) - speed.in(RPM));
                if (errorRPM <= Constants.LauncherSubsystem.TOLERANCE_RPM) return;

                coroutine.yield();
            }
        }).named("GoToRPM");
    }
}
