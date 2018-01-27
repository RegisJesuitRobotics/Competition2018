/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";

	private soccordrivE Driver;
	private boolean autoMove = false;
	FieldElementScoringSide SwitchSide;
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
		Driver = new soccordrivE(new PlayStationController(0));
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
		// FOR AUTO METHODS USE SPEED THEN TIME
		// GO OVER LINE
		if (autoSelectedPosition.equals(AutoPosLeft) && autoSelectedObjective.equals(AutoGoalLine)) {
			Driver.AutoGoOverLine();
		} else if (autoSelectedPosition.equals(AutoPosRight) && autoSelectedObjective.equals(AutoGoalLine)) {
			Driver.AutoGoOverLine();
		} else if (autoSelectedPosition.equals(AutoPosMid) && autoSelectedObjective.equals(AutoGoalLine)) {
			Driver.AutoGoOverLine();
		}
		// SCORE SWITCH
		// left start
		else if (autoSelectedPosition.equals(AutoPosLeft) && autoSelectedObjective.equals(AutoGoalSwitch)
				&& SwitchSide == FieldElementScoringSide.Left) {
			Driver.AutoGoOverLine();
		} else if (autoSelectedPosition.equals(AutoPosLeft) && autoSelectedObjective.equals(AutoGoalSwitch)
				&& SwitchSide == FieldElementScoringSide.Right) {
			Driver.AutoGoOverLine();
		}
		// right start
		else if (autoSelectedPosition.equals(AutoPosRight) && autoSelectedObjective.equals(AutoGoalSwitch)
				&& SwitchSide == FieldElementScoringSide.Left) {
			Driver.AutoGoOverLine();
		} else if (autoSelectedPosition.equals(AutoPosRight) && autoSelectedObjective.equals(AutoGoalSwitch)
				&& SwitchSide == FieldElementScoringSide.Right) {
			Driver.AutoGoOverLine();
		}

		// SCORE SCALE
		// left start
		else if (autoSelectedPosition.equals(AutoPosLeft) && autoSelectedObjective.equals(AutoGoalScale)
				&& SwitchSide == FieldElementScoringSide.Left) {
			Driver.AutoGoOverLine();
		} else if (autoSelectedPosition.equals(AutoPosLeft) && autoSelectedObjective.equals(AutoGoalScale)
				&& SwitchSide == FieldElementScoringSide.Right) {
			Driver.AutoGoOverLine();
		}
		// right start
		else if (autoSelectedPosition.equals(AutoPosRight) && autoSelectedObjective.equals(AutoGoalScale)
				&& SwitchSide == FieldElementScoringSide.Left) {
			Driver.AutoGoOverLine();
		} else if (autoSelectedPosition.equals(AutoPosRight) && autoSelectedObjective.equals(AutoGoalScale)
				&& SwitchSide == FieldElementScoringSide.Right) {
			Driver.AutoGoOverLine();
		}

	}

	@Override
	public void teleopInit() {

	}

	@Override
	public void teleopPeriodic() {
		// m_robotDrive.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			Driver.soccordrivE1();
			Driver.TestBoi();

			Driver.EncoderStart();
			SmartDashboard.putNumber("Left Encoder", Driver.LeftEncode.get());
			SmartDashboard.putNumber("Right Encoder", Driver.RightEncode.get());
		}
	}

	// private void execute() {
	// SmartDashboard.putNumber("Left Encoder", .getLeftEncode());
	// }
}
