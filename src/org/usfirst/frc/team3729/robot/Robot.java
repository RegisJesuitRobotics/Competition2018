/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";

	private soccordrivE Driver;
	private boolean autoMove = false;

	public Robot() {
		Driver = new soccordrivE(new PlayStationController(0));
	}

	NetworkTable table;

	@Override
	public void robotInit() {
		table = NetworkTable.getTable("datatable");

		// Uncomment when ready. This is the code to get the active switch
		// and scale side. Throws an exception otherwise.
		// String gameData;
		// gameData = DriverStation.getInstance().getGameSpecificMessage();
		// if(gameData.charAt(0) == 'L')
		// {
		// //Put left auto code here
		// } else {
		// //Put right auto code here
		// }
	}

	@Override
	public void autonomousInit() {
		autoMove = true;
	}

	@Override
	public void autonomousPeriodic() {
		while (autoMove == true) {
			Driver.AutoGoForeward(0.6, 3);
			Driver.AutoGoLeft(0.6, 5);
			autoMove = false;
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
			table.putNumber("Left Encoder", Driver.LeftEncode.get());
			table.putNumber("Right Encoder", Driver.RightEncode.get());
		}
	}

	// private void execute() {
	// SmartDashboard.putNumber("Left Encoder", .getLeftEncode());
	// }
}
