package org.usfirst.frc.team3729.commands;

import org.usfirst.frc.team3729.robot.FieldElementScoringSide;
import org.usfirst.frc.team3729.robot.FieldStartingPosition;

import edu.wpi.first.wpilibj.Timer;
import subsystems.PortMap;

public class AutoModes {
	// AUTO MODES
	AutoCalledMethods Auto;
	PortMap Map;

	public AutoModes() {
		Auto = new AutoCalledMethods();
		Map = PortMap.getInstance();
	}

	public void AutoGoOverLine() {
		Auto.AutoGoForeward(1, 6);
		Auto.stopMotors();

	}

	public void AutoScoreSwitch(FieldElementScoringSide SwitchSide, FieldStartingPosition StartingPosition) {
		if (StartingPosition == FieldStartingPosition.Left && SwitchSide == FieldElementScoringSide.Left) {
			// START LEFT, ELEMENT LEFT

			Auto.AutoGoForeward(0.5, 2);
			Auto.AutoStop(2);
			Auto.AutoPointTurnRight(0.424, 1);
			Auto.AutoStop(1);
			Auto.AutoLifter();
			Auto.AutoGoForeward(0.23, 1);
			// Auto.AutoThrow();
			System.out.println("starbucks1");

		} else if (StartingPosition == FieldStartingPosition.Right && SwitchSide == FieldElementScoringSide.Right) {
			// START RIGHT, ELEMENT RIGHT

			Auto.AutoGoForeward(0.5, 2);
			Auto.AutoStop(2);
			Auto.AutoPointTurnLeft(0.424, 1);
			Auto.AutoStop(1);
			Auto.AutoLifter();
			Auto.AutoGoForeward(0.23, 1);
			// Auto.AutoThrow();
			System.out.println("coffee");
		} else if (StartingPosition == FieldStartingPosition.Left && SwitchSide == FieldElementScoringSide.Right) {
			// START LEFT, ELEMENT RIGHT

			Auto.AutoGoForeward(0.668, 2);
			Auto.AutoStop(1);
			Auto.AutoPointTurnRight(0.39, 1);
			Auto.AutoStop(1);
			Auto.AutoGoForeward(0.689, 2);
			Auto.AutoLifter();
			// Auto.AutoThrow();
			System.out.println("chipotle");

		} else if (StartingPosition == FieldStartingPosition.Right && SwitchSide == FieldElementScoringSide.Left) {
			// START RIGHT, ELEMENT LEFT
			Auto.AutoGoForeward(0.668, 2);
			Auto.AutoStop(1);
			Auto.AutoPointTurnLeft(0.44, 1);
			Auto.AutoStop(1);
			Auto.AutoGoForeward(0.7, 2);
			Auto.AutoStop(1);
			Auto.AutoPointTurnLeft(0.45, 1);
			Auto.AutoStop(1);
			Auto.AutoGoForeward(0.57, 1);
			Auto.AutoPointTurnLeft(0.38, 1);
			Auto.AutoLifter();
			// Auto.AutoThrow();
			System.out.println("chili's");
		}
	}

	public void AutoScoreScale(FieldElementScoringSide ScaleSide, FieldStartingPosition StartingPosition) {
		if (StartingPosition == FieldStartingPosition.Left && ScaleSide == FieldElementScoringSide.Left) {
			// Start left element on left
			Auto.AutoGoForeward(1, 2);
			Auto.AutoStop(2);
			Auto.AutoPointTurnRight(0.424, 1);
			Auto.AutoStop(2);
			Auto.AutoLifter();
			Auto.AutoGoForeward(0, 0);
			// Auto.AutoThrow();
			System.out.println("Thiccer than a snicker");

		} else if (StartingPosition == FieldStartingPosition.Right && ScaleSide == FieldElementScoringSide.Right) {
			// Start right element on right
			Auto.AutoGoForeward(1, 2);
			Auto.AutoStop(2);
			Auto.AutoPointTurnLeft(0.424, 1);
			Auto.AutoStop(2);
			Auto.AutoLifter();
			Auto.AutoGoForeward(0, 0);
			// Auto.AutoThrow();
			System.out.println("Fetcher than a sketcher");
			//
		} else if (StartingPosition == FieldStartingPosition.Left && ScaleSide == FieldElementScoringSide.Right) {
			// Start left element on Right
			Auto.AutoGoForeward(1, 3);
			Auto.AutoStop(2);
			Auto.AutoPointTurnRight(0.424, 1);
			Auto.AutoStop(2);
			Auto.AutoGoForeward(0.7, 3);
			Auto.AutoStop(2);
			Auto.AutoPointTurnLeft(0.424, 0);
			Auto.AutoStop(2);
			Auto.AutoGoForeward(0, 0);
			Auto.AutoStop(2);
			Auto.AutoPointTurnLeft(0, 0);
			Auto.AutoStop(2);
			Auto.AutoLifter();
			Auto.AutoGoForeward(0, 0);
			// Auto.AutoThrow();
			System.out.println("Gayer than a mayor");

		} else if (StartingPosition == FieldStartingPosition.Right && ScaleSide == FieldElementScoringSide.Left) {
			// Start right element on right
			Auto.AutoGoForeward(1, 3);
			Auto.AutoStop(2);
			Auto.AutoPointTurnLeft(0.424, 1);
			Auto.AutoStop(2);
			Auto.AutoGoForeward(0.7, 3);
			Auto.AutoStop(2);
			Auto.AutoPointTurnRight(0.424, 0);
			Auto.AutoStop(2);
			Auto.AutoGoForeward(0, 0);
			Auto.AutoStop(2);
			Auto.AutoPointTurnRight(0, 0);
			Auto.AutoStop(2);
			Auto.AutoLifter();
			Auto.AutoGoForeward(0, 0);
			// Auto.AutoThrow();
			System.out.println("Finer than a Diner");

		}
	}
}
