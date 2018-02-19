package subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;

public class PortMap {
	public WPI_TalonSRX RightMotorFront, LeftMotorFront, LeftMotorBack, RightMotorBack, LifterMotor, ClimbMotor, BoxSpinMotor;
	public PlayStationController playStation;
	public DoubleSolenoid Grabber, TransmissionExchange;
	public Victor BoxSpinVictor;
	// public AnalogInput Laser;

	public PortMap() {

		LeftMotorFront = new WPI_TalonSRX(3);
		RightMotorFront = new WPI_TalonSRX(2);
		// CHANGE TO MOTOR NAMES
		LeftMotorBack = new WPI_TalonSRX(1);
		RightMotorBack = new WPI_TalonSRX(4);
		LifterMotor = new WPI_TalonSRX(8);
		ClimbMotor = new WPI_TalonSRX(7);
		BoxSpinMotor = new WPI_TalonSRX(15);  // TODO
		BoxSpinVictor = new Victor(420);  // TODO
		Grabber = new DoubleSolenoid(0,0);
		TransmissionExchange = new DoubleSolenoid(2,3);
		// Laser = new AnalogInput(0);

		// ENCODERS DIO
		// VICTORS GO IN PWM
	}
}