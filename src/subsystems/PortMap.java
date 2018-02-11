package subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;

public class PortMap {
	public WPI_TalonSRX RightMotorFront, LeftMotorFront, LeftMotorBack, RightMotorBack, LifterMotor, ClimbMotor,
			GrabberMotorLeft, GrabberMotorRight;
	public PlayStationController playStation;

	public AnalogInput Laser;

	public PortMap() {

		LeftMotorFront = new WPI_TalonSRX(1);
		RightMotorFront = new WPI_TalonSRX(0);
		// CHANGE TO MOTOR NAMES
		// LeftMotorBack = new WPI_TalonSRX(0);
		// RightMotorBack = new WPI_TalonSRX(0);
		// LifterMotor = new WPI_TalonSRX(0);
		// ClimbMotor = new WPI_TalonSRX(0);
		// GrabberMotorLeft = new WPI_TalonSRX(0);
		// GrabberMotorRight = new WPI_TalonSRX(0);
		// Laser = new AnalogInput(0);

		// ENCODERS DIO
		// VICTORS GO IN PWM

	}
}