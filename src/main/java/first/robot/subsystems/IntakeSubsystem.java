/**
 * controls the intake rollers consisting of a single motor
 * need to be able to run a set speed command
 * that is controlled by and intake and out take command
 */



package first.robot.subsystems;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;
import org.wpilib.hardware.expansionhub.ExpansionHubMotor;
import org.wpilib.math.system.DCMotor;

import first.robot.Constants;

public class IntakeSubsystem extends Mechanism {
    private ExpansionHubMotor m_intake;

    public void IntakeSubsystem(){
        m_intake = new ExpansionHubMotor(0, Constants.IntakeConstants.motorID);
    }

    public void setIntakeSpeed() {
        m_intake.setThrottle(Constants.IntakeConstants.intakePower);
    }
    public void setIdleSpeed() {
         m_intake.setThrottle(Constants.IntakeConstants.idlePower);
    }

    public void setFeedSpeed(){
         m_intake.setThrottle(Constants.IntakeConstants.feedPower);
    }
    //optional expansion
    //velocity control with pid loops to handle different power req.
}
