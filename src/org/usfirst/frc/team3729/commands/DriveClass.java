package org.usfirst.frc.team3729.commands;

import org.usfirst.frc.team3729.robot.FieldElementScoringSide;
import org.usfirst.frc.team3729.robot.FieldStartingPosition;

import edu.wpi.first.wpilibj.Timer;
import subsystems.PlayStationController;
import subsystems.PortMap;

public class DriveClass {

	PlayStationController playStation;
	subsystems.PortMap Map;

	public DriveClass(PlayStationController playStation) {

		playStation = new PlayStationController(0);
		this.playStation = playStation;
		Map = new PortMap();

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

		Map.LeftMotor.set(LeftPower * 0.88 * Limiter);
		Map.RightMotor.set(-RightPower * Limiter);
	}

	public void TestBoi() {
		if (playStation.GetButtonX()) {
			Map.testMotor.set(-0.5);
			System.out.println("omae wa gay if ur reading this omae wa idiot");
		} else {
			Map.testMotor.set(0);
		}

	}

	public void AutoGoForeward(double speed, int time) {

		Map.LeftMotor.set(-speed);
		Map.RightMotor.set(speed);
		Timer.delay(time);

		Map.LeftMotor.stopMotor();
		Map.RightMotor.stopMotor();

	}

	public void AutoGoOverLine() {

		Map.LeftMotor.set(-0.5);
		Map.RightMotor.set(0.5);
		// System.out.println("PreDELAY");
		Timer.delay(1);
		// System.out.println("cuccckerino");
		Map.LeftMotor.stopMotor();
		Map.RightMotor.stopMotor();

	}

	public void AutoScoreSwitch(FieldElementScoringSide SwitchSide, FieldStartingPosition StartingPosition) {
		if (StartingPosition == FieldStartingPosition.Left && SwitchSide == FieldElementScoringSide.Left) {
			// START LEFT, ELEMENT LEFT
			AutoGoForeward(0.5, 2);
			AutoPointTurnRight(0.325, 1);

		} else if (StartingPosition == FieldStartingPosition.Right && SwitchSide == FieldElementScoringSide.Right) {
			// START RIGHT, ELEMENT RIGHT
			AutoGoForeward(0.5, 2);
			AutoPointTurnLeft(0.325, 1);
		} else if (StartingPosition == FieldStartingPosition.Left && SwitchSide == FieldElementScoringSide.Right) {
			// START RIGHT, ELEMENT RIGHT
			AutoGoForeward(0.8, 3);
			AutoPointTurnRight(0.325, 1);
			AutoGoForeward(0.8, 3);
			AutoPointTurnRight(0.325, 1);
		} else if (StartingPosition == FieldStartingPosition.Right && SwitchSide == FieldElementScoringSide.Left) {
			// START RIGHT, ELEMENT RIGHT
			AutoGoForeward(0.8, 3);
			AutoPointTurnLeft(0.325, 1);
			AutoGoForeward(0.8, 3);
			AutoPointTurnLeft(0.325, 1);
		}
	}

	public void AutoScoreScale(FieldElementScoringSide ScaleSide, FieldStartingPosition StartingPosition) {
		if (StartingPosition == FieldStartingPosition.Left && ScaleSide == FieldElementScoringSide.Left) {
			AutoGoOverLine();
			AutoGoOverLine();
			AutoPointTurnRight(0.38, 1);
		} else if (StartingPosition == FieldStartingPosition.Right && ScaleSide == FieldElementScoringSide.Right) {
			AutoGoOverLine();
			AutoGoOverLine();
			AutoPointTurnLeft(0.38, 1);
		}
	}

	public void AutoGoRight(double speed, int time) {
		Map.LeftMotor.set(-speed * 0.5);
		Map.RightMotor.set(speed);
		Timer.delay(time);
		Map.LeftMotor.set(0.0);
		Map.RightMotor.set(0);
	}

	public void AutoGoLeft(double speed, int time) {

		Map.LeftMotor.set(-speed);
		Map.RightMotor.set(speed * 0.5);
		Timer.delay(time);
		Map.LeftMotor.set(0.0);
		Map.RightMotor.set(0);
	}

	public void AutoPointTurnLeft(double speed, int time) {

		Map.LeftMotor.set(speed);
		Map.RightMotor.set(speed);
		Timer.delay(time);
		Map.LeftMotor.set(0.0);
		Map.RightMotor.set(0);
	}

	public void AutoPointTurnRight(double speed, int time) {

		Map.LeftMotor.set(-speed);
		Map.RightMotor.set(-speed);
		Timer.delay(time);
		Map.LeftMotor.set(0.0);
		Map.RightMotor.set(0);
	}

}
