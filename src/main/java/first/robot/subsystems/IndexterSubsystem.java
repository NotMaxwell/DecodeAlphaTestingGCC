package first.robot.subsystems;

import org.wpilib.command3.Mechanism;
import org.wpilib.hardware.expansionhub.ExpansionHubCRServo;

import first.robot.Constants;

public class IndexterSubsystem extends Mechanism {
    private ExpansionHubCRServo leftServo = new ExpansionHubCRServo(0, Constants.IndexterConstants.leftMotorId);
    
}
