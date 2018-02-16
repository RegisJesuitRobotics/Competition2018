package org.usfirst.frc.team3729.commands;

import org.usfirst.frc.team3729.robot.FieldElementScoringSide;
import org.usfirst.frc.team3729.robot.FieldStartingPosition;

import edu.wpi.first.wpilibj.Timer;
import subsystems.PlayStationController;
import subsystems.PortMap;

public class DriveClass {

	PlayStationController playStation;
	subsystems.PortMap Map;
	RobotUtil utils;

	public DriveClass(PlayStationController playStation) {

		playStation = new PlayStationController(0);
		this.playStation = playStation;
		Map = new PortMap();
		utils = new RobotUtil();
	}

	//THESE ARE THE TELEOP METHODS THEY'RE CALLED IN ROBOT.JAVA UNDER TELEOP PERIODIC	
	public void TeleOpDrive() {
		double RightTrigger = playStation.GetRightTrigger();
		double LeftTrigger = playStation.GetLeftTrigger();
		double LeftStick = playStation.GetLeftStickAxisX();
		double Deadzone = 0.1; // TODO
		double RightPower;
		double LeftPower;
		double Power;
		double Limiter = 0.8; // TODO
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

		Map.LeftMotorFront.set(-LeftPower * Limiter * utils.SideCompensation);
		Map.LeftMotorBack.set(-LeftPower * Limiter * utils.SideCompensation);
		Map.RightMotorFront.set(RightPower * Limiter);
		Map.RightMotorBack.set(RightPower * Limiter);

	}

	public void TeleOpLift() {
		if (playStation.GetButtonX() == true) {
			Map.LifterMotor.set(0.5);

		} else if (playStation.GetButtonO()) {
			Map.LifterMotor.set(-0.5);
		} else {
			Map.LifterMotor.stopMotor();
		}
	}

	public void TeleOpGrab() {
		if (playStation.GetButtonTriangle() == true) {
			Map.GrabberMotorRight.set(0.5);
			Map.GrabberMotorLeft.set(0.5);
		} else if (playStation.GetButtonSquare()) {
			Map.GrabberMotorLeft.set(-0.5);
			Map.GrabberMotorRight.set(-0.5);
		} else {
			Map.GrabberMotorLeft.stopMotor();
			Map.GrabberMotorRight.stopMotor();
		}
	}

	// ALL THE METHODS BELOW THIS ARE OUR AUTONOMOUS MODES
	//THESE WILL BE CALLED IN ROBOT.JAVA
	
	public void AutoGoOverLine() {
		utils.AutoGoForeward(0.8, 1);

	}

	public void AutoScoreSwitch(FieldElementScoringSide SwitchSide, FieldStartingPosition StartingPosition) {
		if (StartingPosition == FieldStartingPosition.Left && SwitchSide == FieldElementScoringSide.Left) {
			// START LEFT, ELEMENT LEFT

			utils.AutoGoForeward(0.5, 2);
			utils.AutoStop(2);
			utils.AutoPointTurnRight(0.424, 1);
			utils.AutoStop(1);
			utils.AutoGoForeward(0.23, 1);
			System.out.println("starbucks1");

		} else if (StartingPosition == FieldStartingPosition.Right && SwitchSide == FieldElementScoringSide.Right) {
			// START RIGHT, ELEMENT RIGHT

			utils.AutoGoForeward(0.5, 2);
			utils.AutoStop(2);
			utils.AutoPointTurnLeft(0.424, 1);
			utils.AutoStop(1);
			utils.AutoGoForeward(0.23, 1);
			System.out.println("coffee");
		} else if (StartingPosition == FieldStartingPosition.Left && SwitchSide == FieldElementScoringSide.Right) {
			// START LEFT, ELEMENT RIGHT

			utils.AutoGoForeward(0.668, 2);
			utils.AutoStop(1);
			utils.AutoPointTurnRight(0.39, 1);
			utils.AutoStop(1);
			utils.AutoGoForeward(0.689, 2);
			utils.AutoStop(1);
			utils.AutoPointTurnRight(0.39, 1);
			utils.AutoStop(1);
			utils.AutoGoForeward(0.55, 1);
			utils.AutoPointTurnRight(0.32, 1);
			System.out.println("chipotle");

		} else if (StartingPosition == FieldStartingPosition.Right && SwitchSide == FieldElementScoringSide.Left) {
			// START RIGHT, ELEMENT LEFT
			utils.AutoGoForeward(0.668, 2);
			utils.AutoStop(1);
			utils.AutoPointTurnLeft(0.44, 1);
			utils.AutoStop(1);
			utils.AutoGoForeward(0.7, 2);
			utils.AutoStop(1);
			utils.AutoPointTurnLeft(0.45, 1);
			utils.AutoStop(1);
			utils.AutoGoForeward(0.57, 1);
			utils.AutoPointTurnLeft(0.38, 1);
			System.out.println("chili's");
		}
	}

	public void AutoScoreScale(FieldElementScoringSide ScaleSide, FieldStartingPosition StartingPosition) {
		if (StartingPosition == FieldStartingPosition.Left && ScaleSide == FieldElementScoringSide.Left) {
			// Start left element on left
			utils.AutoGoForeward(1, 2);
			utils.AutoStop(2);
			utils.AutoPointTurnRight(0.424, 1);
			utils.AutoStop(2);
			utils.AutoGoForeward(0, 0);
			System.out.println("Thiccer than a snicker");

		} else if (StartingPosition == FieldStartingPosition.Right && ScaleSide == FieldElementScoringSide.Right) {
			// Start right element on right
			utils.AutoGoForeward(1, 2);
			utils.AutoStop(2);
			utils.AutoPointTurnLeft(0.424, 1);
			utils.AutoStop(2);
			utils.AutoGoForeward(0, 0);
			System.out.println("Fetcher than a sketcher");
			//
		} else if (StartingPosition == FieldStartingPosition.Left && ScaleSide == FieldElementScoringSide.Right) {
			// Start left element on Right
			utils.AutoGoForeward(1, 3);
			utils.AutoStop(2);
			utils.AutoPointTurnRight(0.424, 1);
			utils.AutoStop(2);
			utils.AutoGoForeward(0.7, 3);
			utils.AutoStop(2);
			utils.AutoPointTurnLeft(0.424, 0);
			utils.AutoStop(2);
			utils.AutoGoForeward(0, 0);
			utils.AutoStop(2);
			utils.AutoPointTurnLeft(0, 0);
			utils.AutoStop(2);
			utils.AutoGoForeward(0, 0);
			System.out.println("Gayer than a mayor");

		} else if (StartingPosition == FieldStartingPosition.Right && ScaleSide == FieldElementScoringSide.Left) {
			// Start right element on right
			utils.AutoGoForeward(1, 3);
			utils.AutoStop(2);
			utils.AutoPointTurnLeft(0.424, 1);
			utils.AutoStop(2);
			utils.AutoGoForeward(0.7, 3);
			utils.AutoStop(2);
			utils.AutoPointTurnRight(0.424, 0);
			utils.AutoStop(2);
			utils.AutoGoForeward(0, 0);
			utils.AutoStop(2);
			utils.AutoPointTurnRight(0, 0);
			utils.AutoStop(2);
			utils.AutoGoForeward(0, 0); // TODO
			System.out.println("Finer than a Diner");

		}
	}

}
