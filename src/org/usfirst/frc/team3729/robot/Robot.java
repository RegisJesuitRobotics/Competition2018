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
	char SwitchSide = '_';
	SendableChooser chooser;
	//AUTOMODES
	final String defaultAuto = "Default";
	final String autonomousPos1 = "APos1";
	final String autonomousPos2 = "APos2";
	final String autonomousPos3 = "APos3";
	String autoSelected;

	public Robot() {
		Driver = new soccordrivE(new PlayStationController(0));
	}

	@Override
	public void robotInit() {
		String gameData;

		// FMS DATA PULL
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L') {
			// SETS FOR RIGHT SWITCHT
			char SwitchSide = 'L';
		} else if (gameData.charAt(0) == 'R') {
			// SETS FOR LEFT SWITCH
			char SwitchSide = 'R';
		}
		
		//INPUT START POSITION
		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("STR8 M8 8/8", autonomousPos1);
		chooser.addObject("Starting on the Right", autonomousPos2);
		chooser.addObject("Starting on the Left", autonomousPos3);
		SmartDashboard.putData("Auto choices", chooser);
	}

	@Override
	public void autonomousInit() {
		autoMove = true;
	}

	@Override
	public void autonomousPeriodic() {
		// FOR AUTO METHODS USE SPEED THEN TIME
		while (autoMove == true) {
			Driver.AutoGoOverLine();

			Driver.AutoGoForeward(0.5, 1);
		}

		// j motor needs less power ACCOMPLISHED
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
