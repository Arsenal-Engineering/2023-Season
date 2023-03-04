package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.networktables.DoubleTopic;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.PubSubOption;

public class TestSparkMax {
    private int motorID;
    private CANSparkMax daSparcMacs;
    private DoubleTopic _bPositionTopic;
    private DoublePublisher _bPositionPublisher;

    public TestSparkMax(int motorID, MotorType motorType, String daNameOfDaThing) {
        this.motorID = motorID;
        if (motorID >= 0) {
            daSparcMacs = new CANSparkMax(motorID, motorType);
        } else {
            _bPositionTopic = NetworkTableInstance.getDefault().getDoubleTopic(daNameOfDaThing + " speed ");
            _bPositionPublisher = _bPositionTopic.publish(PubSubOption.periodic(0));
        }
    }

    public void set(double speed) {
        if (motorID >= 0) {
            daSparcMacs.set(speed);
        } else {
            _bPositionPublisher.set(speed);
        }
    }
}