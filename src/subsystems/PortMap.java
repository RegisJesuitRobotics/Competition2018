package subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;

public class PortMap {
	private static PortMap map;

	public WPI_TalonSRX RightDriveMotorFront, RightDriveMotorBack, LeftDriveMotorFront, LeftDriveMotorBack, LifterMotor,
			ClimbMotor, GrabberMotor;
	public DoubleSolenoid GrabberSolenoid, TransmisionExchangeSolenoid;
	public PlayStationController playStation;
	// public AnalogInput Laser;
	public Compressor compressor;

	private PortMap() {

		LeftDriveMotorFront = new WPI_TalonSRX(4);
		RightDriveMotorFront = new WPI_TalonSRX(3);
		LeftDriveMotorBack = new WPI_TalonSRX(6);
		RightDriveMotorBack = new WPI_TalonSRX(2);
		LifterMotor = new WPI_TalonSRX(5);
		ClimbMotor = new WPI_TalonSRX(1);
		// GrabberMotor = new WPI_TalonSRX(5);
		// compressor = new Compressor(0);
		GrabberSolenoid = new DoubleSolenoid(0, 1);
		TransmisionExchangeSolenoid = new DoubleSolenoid(2, 3);
		// ENCODERS DIO
		// VICTORS GO IN PWM
	}

	public static PortMap getInstance() {
		if (map == null) {
			map = new PortMap();
		}

		return map;
	}
}