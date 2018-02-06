package subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

public class PortMap {
	public Victor RightMotor, LeftMotor, testMotor;
	public PlayStationController playStation;
	public Encoder RightEncode, LeftEncode;
	boolean encodersHaveStated;

	public PortMap() {

		LeftMotor = new Victor(0);
		RightMotor = new Victor(1);
		testMotor = new Victor(2);
		RightEncode = new Encoder(6, 7);
		LeftEncode = new Encoder(8, 9);

		// ENCODERS DIO
		// VICTORS GO IN PWM

		encodersHaveStated = false;
	}
}