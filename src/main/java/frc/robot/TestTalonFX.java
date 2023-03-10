package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.networktables.DoubleTopic;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.PubSubOption;

public class TestTalonFX {
    private int motorID;
    private WPI_TalonFX daTalonEfEcs;
    private DoubleTopic _bPositionTopic;
    private DoublePublisher _bPositionPublisher;

    public TestTalonFX(int motorID, String daNameOfDaThing) {
        this.motorID = motorID;
        if (motorID >= 0) {
            daTalonEfEcs = new WPI_TalonFX(motorID);
        } else {
            _bPositionTopic = NetworkTableInstance.getDefault().getDoubleTopic(daNameOfDaThing + " speed ");
            _bPositionPublisher = _bPositionTopic.publish(PubSubOption.periodic(0));
        }
    }

    public void set(double speed) {
        if (motorID >= 0) {
            daTalonEfEcs.set(speed);
        } else {
            _bPositionPublisher.set(speed);
        }
    }
}
