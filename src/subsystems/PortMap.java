package subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

public class PortMap {
	public WPI_TalonSRX RightMotor, LeftMotor, testMotor;
	public PlayStationController playStation;
	public Encoder RightEncode, LeftEncode;
	boolean encodersHaveStated;

	public PortMap() {

		LeftMotor = new WPI_TalonSRX(0);
		RightMotor = new WPI_TalonSRX(1);
		//testMotor = new Victor(2);
		RightEncode = new Encoder(6, 7);
		LeftEncode = new Encoder(8, 9);

		// ENCODERS DIO
		// VICTORS GO IN PWM

		encodersHaveStated = false;
	}
}