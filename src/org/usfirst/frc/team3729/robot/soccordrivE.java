package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class soccordrivE {
	Victor RightMotor, LeftMotor;
	PlayStationController playStation;
	Victor testMotor;
	Encoder RightEncode, LeftEncode;
	boolean encodersHaveStated;

	public soccordrivE(PlayStationController playStation) {

		playStation = new PlayStationController(0);
		this.playStation = playStation;
		LeftMotor = new Victor(0);
		RightMotor = new Victor(1);
		testMotor = new Victor(2);
		RightEncode = new Encoder(6, 7);
		LeftEncode = new Encoder(8, 9);
		// ENCODERS DIO
		// VICTORS GO IN PWM

		encodersHaveStated = false;
	}

	public void soccordrivE1() {
		double RightTrigger = playStation.GetRightTrigger();
		double LeftTrigger = playStation.GetLeftTrigger();
		double LeftStick = playStation.GetLeftStickAxisX();
		double Deadzone = 0.1;
		double RightPower;
		double LeftPower;
		double Power;
		double Limiter = 0.8;
		double turn = 2 * LeftStick;
		Power = LeftTrigger - RightTrigger;
		// System.out.println("left"+LeftStick);
		if (LeftStick > Deadzone) {

			RightPower = Power - (turn * Power);
			LeftPower = Power;
		} else if (LeftStick < -Deadzone) {

			LeftPower = Power + (turn * Power);
			RightPower = Power;
		} else {
			LeftPower = Power;
			RightPower = Power;
		}

		LeftMotor.set(-LeftPower * Limiter);
		RightMotor.set(RightPower * 0.93 * Limiter);
	}

	public void TestBoi() {
		if (playStation.GetButtonX()) {
			testMotor.set(-0.5);
			System.out.println("omae wa gay if ur reading this omae wa idiot");
		} else {
			testMotor.set(0);
		}
	}

	public void EncoderStart() {
		if (encodersHaveStated == false) {
			LeftEncode.reset();
			RightEncode.reset();
			System.out.println(LeftEncode.get());
			System.out.println(RightEncode.get());
			encodersHaveStated = true;
		}
	}

	public void AutoGoForeward(double speed, int time) {

		LeftMotor.set(-speed);
		RightMotor.set(speed);
	try {
		wait(time);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		LeftMotor.stopMotor();
		RightMotor.stopMotor();
		
	}
	public void AutoGoOverLine() {

		LeftMotor.set(-0.5);
		RightMotor.set(0.5);
	try {
		wait(1);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		LeftMotor.stopMotor();
		RightMotor.stopMotor();
		
	}

	public void AutoGoRight(double speed, int time) {
		LeftMotor.set(-speed * 0.5);
		RightMotor.set(speed);
		try {
			wait(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LeftMotor.set(0.0);
		RightMotor.set(0);
	}

	public void AutoGoLeft(double speed, int time) {

		LeftMotor.set(-speed);
		RightMotor.set(speed * 0.5);
		try {
			wait(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LeftMotor.set(0.0);
		RightMotor.set(0);
	}

}
