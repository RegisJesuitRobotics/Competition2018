package org.usfirst.frc.team3729.commands;

import org.usfirst.frc.team3729.robot.FieldElementScoringSide;
import org.usfirst.frc.team3729.robot.FieldStartingPosition;

import edu.wpi.first.wpilibj.Timer;
import subsystems.PlayStationController;
import subsystems.PortMap;

public class DriveClass {

	PlayStationController playStation;
	subsystems.PortMap Map;
	double SideCompensation;

	public DriveClass(PlayStationController playStation) {

		playStation = new PlayStationController(0);
		this.playStation = playStation;
		Map = new PortMap();
		SideCompensation = 0.97;
	}

	public void TeleOpDrive() {
		double RightTrigger = playStation.GetRightTrigger();
		double LeftTrigger = playStation.GetLeftTrigger();
		double LeftStick = playStation.GetLeftStickAxisX();
		double Deadzone = 0.1;
		double RightPower;
		double LeftPower;
		double Power;
		double Limiter = 0.8;
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

		Map.LeftMotorFront.set(-LeftPower * Limiter * SideCompensation);
		Map.RightMotorFront.set(RightPower * Limiter);
	}

	public void AutoGoOverLine() {
		AutoGoForeward(0.8, 1);

	}

	public void AutoScoreSwitch(FieldElementScoringSide SwitchSide, FieldStartingPosition StartingPosition) {
		if (StartingPosition == FieldStartingPosition.Left && SwitchSide == FieldElementScoringSide.Left) {
			// START LEFT, ELEMENT LEFT

			AutoGoForeward(0.5, 2);
			AutoStop(2);
			AutoPointTurnRight(0.424, 1);
			AutoStop(1);
			AutoGoForeward(0.23, 1);
			System.out.println("starbucks1");

		} else if (StartingPosition == FieldStartingPosition.Right && SwitchSide == FieldElementScoringSide.Right) {
			// START RIGHT, ELEMENT RIGHT

			AutoGoForeward(0.5, 2);
			AutoStop(2);
			AutoPointTurnLeft(0.424, 1);
			AutoStop(1);
			AutoGoForeward(0.23, 1);
			System.out.println("coffee");
		} else if (StartingPosition == FieldStartingPosition.Left && SwitchSide == FieldElementScoringSide.Right) {
			// START LEFT, ELEMENT RIGHT

			AutoGoForeward(0.666, 2);
			AutoStop(2);
			AutoPointTurnRight(0.45, 1);
			AutoStop(2);
			AutoGoForeward(0, 0);
			AutoStop(2);
			AutoPointTurnRight(0, 0);
			AutoStop(2);
			AutoGoForeward(0, 0);

			System.out.println("chipotle");
		} else if (StartingPosition == FieldStartingPosition.Right && SwitchSide == FieldElementScoringSide.Left) {
			// START RIGHT, ELEMENT LEFT
			AutoGoForeward(0.666, 2);
			AutoStop(2);
			AutoPointTurnLeft(0.45, 1);
			AutoStop(2);
			AutoGoForeward(0, 0);
			AutoStop(2);
			AutoPointTurnLeft(0, 0);
			AutoStop(2);
			AutoGoForeward(0, 0);
			System.out.println("chili's");
		}
	}

	public void AutoScoreScale(FieldElementScoringSide ScaleSide, FieldStartingPosition StartingPosition) {
		if (StartingPosition == FieldStartingPosition.Left && ScaleSide == FieldElementScoringSide.Left) {
			// Start left element on left
			AutoGoForeward(0, 0);
			AutoStop(2);
			AutoPointTurnRight(0, 0);
			AutoStop(2);
			AutoGoForeward(0, 0);
			System.out.println("Thiccer than a snicker");

		} else if (StartingPosition == FieldStartingPosition.Right && ScaleSide == FieldElementScoringSide.Right) {
			// Start right element on right
			AutoGoForeward(0, 0);
			AutoStop(2);
			AutoPointTurnLeft(0, 0);
			AutoStop(2);
			AutoGoForeward(0, 0);
			System.out.println("Fetcher than a sketcher");

		} else if (StartingPosition == FieldStartingPosition.Left && ScaleSide == FieldElementScoringSide.Right) {
			// Start left element on Right
			AutoGoForeward(0, 0);
			AutoStop(2);
			AutoPointTurnRight(0, 0);
			AutoStop(2);
			AutoGoForeward(0, 0);
			AutoStop(2);
			AutoPointTurnLeft(0, 0);
			AutoStop(2);
			AutoGoForeward(0, 0);
			AutoStop(2);
			AutoPointTurnLeft(0, 0);
			AutoStop(2);
			AutoGoForeward(0, 0);
			System.out.println("Gayer than a mayor");

		} else if (StartingPosition == FieldStartingPosition.Right && ScaleSide == FieldElementScoringSide.Left) {
			// Start right element on right
			AutoGoForeward(0, 0);
			AutoStop(2);
			AutoPointTurnLeft(0, 0);
			AutoStop(2);
			AutoGoForeward(0, 0);
			AutoStop(2);
			AutoPointTurnRight(0, 0);
			AutoStop(2);
			AutoGoForeward(0, 0);
			AutoStop(2);
			AutoPointTurnRight(0, 0);
			AutoStop(2);
			AutoGoForeward(0, 0);
			System.out.println("Finer than a Diner");

		}
	}

	public void AutoGoForeward(double speed, int time) {

		Map.LeftMotorFront.set(-speed * SideCompensation);
		Map.RightMotorFront.set(speed);
		Timer.delay(time);

		Map.LeftMotorFront.stopMotor();
		Map.RightMotorFront.stopMotor();

	}

	public void AutoGoRight(double speed, int time) {
		Map.LeftMotorFront.set(-speed * 0.5 * SideCompensation);
		Map.RightMotorFront.set(speed);
		Timer.delay(time);
		Map.LeftMotorFront.stopMotor();
		Map.RightMotorFront.stopMotor();
	}

	public void AutoGoLeft(double speed, int time) {

		Map.LeftMotorFront.set(-speed * SideCompensation);
		Map.RightMotorFront.set(speed * 0.5 * 0.8);
		Timer.delay(time);
		Map.LeftMotorFront.stopMotor();
		Map.RightMotorFront.stopMotor();
	}

	public void AutoPointTurnRight(double speed, int time) {

		Map.LeftMotorFront.set(-speed * SideCompensation);
		Map.RightMotorFront.set(-speed);
		Timer.delay(time);
		Map.LeftMotorFront.stopMotor();
		Map.RightMotorFront.stopMotor();
	}

	public void AutoPointTurnLeft(double speed, int time) {
		System.out.println(" Motor Left Pos, Motor Right pos");
		Map.LeftMotorFront.set(speed * SideCompensation);
		Map.RightMotorFront.set(speed);
		Timer.delay(time);
		Map.LeftMotorFront.stopMotor();
		Map.RightMotorFront.stopMotor();

	}

	public void AutoStop(int time) {
		Map.LeftMotorFront.stopMotor();
		Map.RightMotorFront.stopMotor();
		Timer.delay(time);

	}
}
