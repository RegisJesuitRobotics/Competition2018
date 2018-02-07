/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3729.robot;

import org.usfirst.frc.team3729.commands.DriveClass;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import subsystems.PlayStationController;

public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";

	private DriveClass Driver;
	private boolean autoMove = false;
	FieldElementScoringSide SwitchSide;
	FieldElementScoringSide ScaleSide;
	SendableChooser PositionChooser, ObjectiveChooser;
	// AUTOMODES

	final String defaultAuto = "Default";
	final String AutoPosLeft = "APosLeft";
	final String AutoPosMid = "APosMiddle";
	final String AutoPosRight = "APosRight";
	String autoSelectedPosition, autoSelectedObjective;
	final String AutoGoalLine = "AGoalLine";
	final String AutoGoalSwitch = "AGoalSwitch";
	final String AutoGoalScale = "AGoalScale";

	public Robot() {
		Driver = new DriveClass(new PlayStationController(0));
	}

	@Override
	public void robotInit() {
		String gameData;

		// FMS DATA PULL FOR SWITCH (INDEX 0 IS SWITCH)
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L' || gameData.charAt(0) == 'l') {
			SwitchSide = FieldElementScoringSide.Left;
		} else {
			SwitchSide = FieldElementScoringSide.Right;
		}
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L' || gameData.charAt(0) == 'l') {
			ScaleSide = FieldElementScoringSide.Left;
		} else {
			ScaleSide = FieldElementScoringSide.Right;
		}
		// INPUT START POSITION
		PositionChooser = new SendableChooser();
		PositionChooser.addDefault("Autonomous Position Left", AutoPosLeft);
		PositionChooser.addObject("Autonomous Position Middle", AutoPosMid);
		PositionChooser.addObject("Autonomous Position Right", AutoPosRight);
		SmartDashboard.putData("Auto Start Position", PositionChooser);

		// INPUT OBJECTIVE
		ObjectiveChooser = new SendableChooser();
		ObjectiveChooser.addDefault("Autonomous Goal Line", AutoGoalLine);
		ObjectiveChooser.addObject("Autonomous Goal Scale", AutoGoalScale);
		ObjectiveChooser.addObject("Autonomous Goal Switch", AutoGoalSwitch);
		SmartDashboard.putData("Auto Objective", ObjectiveChooser);
	}

	@Override
	public void autonomousInit() {
		autoMove = true;
		autoSelectedPosition = (String) PositionChooser.getSelected();
		System.out.println("Auto selected: " + autoSelectedPosition);
		autoSelectedObjective = (String) ObjectiveChooser.getSelected();
		System.out.println("Auto selected: " + autoSelectedObjective);

	}

	@Override
	public void autonomousPeriodic() {
		// System.out.println(autoSelectedPosition);
		// System.out.println(autoSelectedObjective);

		// FOR AUTO METHODS USE SPEED THEN TIME
		// GO OVER LINE
		while (autoMove) {
			System.out.println(SwitchSide);
			System.out.println(ScaleSide);

			// SCORE LINE

			if (autoSelectedPosition.equals(AutoPosLeft) && autoSelectedObjective.equals(AutoGoalLine)) {
				// START LEFT, GOAL IS LINE
				Driver.AutoGoOverLine();

			} else if (autoSelectedPosition.equals(AutoPosRight) && autoSelectedObjective.equals(AutoGoalLine)) {
				// START RIGHT, GOAL IS LINE
				Driver.AutoGoOverLine();

			} else if (autoSelectedPosition.equals(AutoPosMid) && autoSelectedObjective.equals(AutoGoalLine)) {
				// START MIDDLE, GOAL IS LINE
				Driver.AutoGoOverLine();
			}
			// SCORE SWITCH

			else if (autoSelectedPosition.equals(AutoPosLeft) && autoSelectedObjective.equals(AutoGoalSwitch)
					&& SwitchSide == FieldElementScoringSide.Left) {
				// START LEFT, GOAL IS SWITCH, SWITCH IS ON LEFT
				Driver.AutoScoreSwitch(SwitchSide, FieldStartingPosition.Left);

			} else if (autoSelectedPosition.equals(AutoPosLeft) && autoSelectedObjective.equals(AutoGoalSwitch)
					&& SwitchSide == FieldElementScoringSide.Right) {
				// START LEFT, GOAL IS SWITCH, SWITCH IS ON RIGHT
				Driver.AutoScoreSwitch(SwitchSide, FieldStartingPosition.Left);
			}

			else if (autoSelectedPosition.equals(AutoPosRight) && autoSelectedObjective.equals(AutoGoalSwitch)
					&& SwitchSide == FieldElementScoringSide.Left) {
				// START RIGHT, GOAL IS SWITCH, SWITCH IS ON LEFT
				Driver.AutoScoreSwitch(SwitchSide, FieldStartingPosition.Right);

			} else if (autoSelectedPosition.equals(AutoPosRight) && autoSelectedObjective.equals(AutoGoalSwitch)
					&& SwitchSide == FieldElementScoringSide.Right) {
				// START ON RIGHT, SWITCH IS GOAL, SWITCH IS ON RIGHT
				Driver.AutoScoreSwitch(SwitchSide, FieldStartingPosition.Right);
			}

			// SCORE SCALE

			else if (autoSelectedPosition.equals(AutoPosLeft) && autoSelectedObjective.equals(AutoGoalScale)
					&& ScaleSide == FieldElementScoringSide.Left) {
				Driver.AutoScoreScale(ScaleSide, FieldStartingPosition.Left);
			} else if (autoSelectedPosition.equals(AutoPosLeft) && autoSelectedObjective.equals(AutoGoalScale)
					&& ScaleSide == FieldElementScoringSide.Right) {
				// START LEFT, GOAL IS SCALE, SCALE IS ON THE RIGHT
				Driver.AutoGoOverLine();
			}

			else if (autoSelectedPosition.equals(AutoPosRight) && autoSelectedObjective.equals(AutoGoalScale)
					&& ScaleSide == FieldElementScoringSide.Left) {
				Driver.AutoScoreScale(ScaleSide, FieldStartingPosition.Right);

			} else if (autoSelectedPosition.equals(AutoPosRight) && autoSelectedObjective.equals(AutoGoalScale)
					&& ScaleSide == FieldElementScoringSide.Right) {
				// START RIGHT, GOAL IS SCALE, SCALE IS ON THE RIGHT
				Driver.AutoGoOverLine();
			}
			autoMove = false;
		}
	}

	@Override
	public void teleopInit() {

	}

	@Override
	public void teleopPeriodic() {
		// m_robotDrive.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			Driver.TeleOpDrive();
			Driver.TestBoi();

		}
	}

	// private void execute() {
	// SmartDashboard.putNumber("Left Encoder", .getLeftEncode());
	// }
}
