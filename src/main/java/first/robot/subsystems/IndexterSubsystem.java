package first.robot.subsystems;

import org.wpilib.command3.Mechanism;
import org.wpilib.hardware.expansionhub.ExpansionHubCRServo;

import first.robot.Constants;

public class IndexterSubsystem extends Mechanism {
    private ExpansionHubCRServo leftServo;
    private ExpansionHubCRServo rightServo;

    public IndexterSubsystem(){
        rightServo = new ExpansionHubCRServo(0, Constants.IndexterConstants.rightMotorId);
        leftServo  = new ExpansionHubCRServo(0, Constants.IndexterConstants.leftMotorId);
        //make left or right servo run in reverse
        rightServo.setReversed(true);
    }


    public void setFeedSpeed(){
        leftServo.setThrottle(Constants.IndexterConstants.feedPower);    
        rightServo.setThrottle(Constants.IndexterConstants.feedPower);   
    }
    public void setIdleSpeed(){
        leftServo.setThrottle(Constants.IndexterConstants.idlePower);
        rightServo.setThrottle(Constants.IndexterConstants.idlePower);
    }
    public void setReverseSpeed(){
        leftServo.setThrottle(-Constants.IndexterConstants.feedPower);
        rightServo.setThrottle(-Constants.IndexterConstants.feedPower);
    }
}
