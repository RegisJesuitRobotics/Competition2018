package org.usfirst.frc.team3729.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

//import org.usfirst.frc.team3729.robot.commands.PlayStationController;

//import edu.wpi.first.wpilibj.Victor;

public class soccordrivE {
	Victor LeftMotor, RightMotor;
	PlayStationController playStation;
	Talon testMotor;
	Encoder RightEncode, LeftEncode;

	public soccordrivE(PlayStationController playStation) {

		playStation = new PlayStationController(0);
		this.playStation = playStation;
		LeftMotor = new Victor(1);
		RightMotor = new Victor(0);
		testMotor = new Talon(2);
		RightEncode = new Encoder(0, 1);
		LeftEncode = new Encoder(2, 3);
	}

	public void soccordrivE1() {
		double RightTrigger = playStation.GetRightTrigger();
		double LeftTrigger = playStation.GetLeftTrigger();
		double LeftStick = playStation.GetLeftStickAxisX();
		double Deadzone = 0.1;
		double RightPower;
		double LeftPower;
		double Power;
		double turn = 2 * LeftStick;
		Power = RightTrigger - LeftTrigger;
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

		LeftMotor.set(-LeftPower);
		RightMotor.set(RightPower * 0.93);
	}

	public void TestBoi() {
		if (playStation.GetButtonX()) {
			testMotor.set(.5);
			System.out.println("omae wa gay if ur reading this omae wa idiot");
		} else {
			testMotor.set(0.0);
		}
	}

	public void AutoGoForeward(double speed, int distance) {
		LeftEncode.reset();

		while (!(LeftEncode.get() >= distance)) {
			LeftMotor.set(-speed);
			RightMotor.set(speed * 0.93);
		}
		LeftMotor.set(1);
		RightMotor.set(-1);
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
