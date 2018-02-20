package org.usfirst.frc.team3729.commands;

import java.util.Date;

import org.usfirst.frc.team3729.robot.FieldElementScoringSide;
import org.usfirst.frc.team3729.robot.FieldStartingPosition;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import subsystems.PlayStationController;
import subsystems.PortMap;

public class DriveClass {

	PlayStationController playStation;
	subsystems.PortMap Map;
	AutoCalledMethods Auto;

	double timeBetweenPresses = 1;
	Date now = new Date();
	Date now2 = new Date();
	Date now3 = new Date();
	Date LastRightJoystickButtonPress = new Date();
	Date LastR1Press = new Date();
	Date LastHomePress = new Date();
	public static double SideCompensation;
	public int LifterState;
	boolean BoxSpinMotorIsRunning;
	boolean IsHoldingCube;
	boolean TransmissionIsToggled;

	public DriveClass(PlayStationController playStation) {
		SideCompensation = 1.0;
		playStation = new PlayStationController(0);
		this.playStation = playStation;
		Map = new PortMap();
		BoxSpinMotorIsRunning = false;
		IsHoldingCube = false;
		TransmissionIsToggled = false;

		// // CPU THREADS
		// Thread t = new Thread(() -> {
		// // LOGIC GOES HERE
		//
		// // GO TO STATE 1
		//
		// // GO TO STATE 2 FROM 1
		//
		// // GO TO STATE 1 FROM 2
		//
		// while (true) {
		//
		// }
		// });

	}

	// THESE ARE THE TELEOP METHODS THEY'RE CALLED IN ROBOT.JAVA UNDER TELEOP
	// PERIODIC
	public void TeleOpDrive() {
		double RightTrigger = playStation.GetRightTrigger();
		double LeftTrigger = playStation.GetLeftTrigger();
		double LeftStick = playStation.GetLeftStickAxisX();
		double Deadzone = 0.1;
		double RightPower;
		double LeftPower;
		double Power;
		double Limiter = 0.5;
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
		Map.LeftMotorBack.set(-LeftPower * Limiter * SideCompensation);
		Map.RightMotorFront.set(RightPower * Limiter);
		Map.RightMotorBack.set(RightPower * Limiter);

	}

	// NON DRIVE TELEOP METHODS

	public void TeleOpTransmission() {
		if (playStation.GetButtonHome() == true) {
			now3 = new Date();
			if (now3.getTime() - LastHomePress.getTime() > timeBetweenPresses) {
				ToggleTransmission();
			}
		}
		LastHomePress = now;
	}

	public void TeleOpBoxSpin() {

		if (playStation.GetButtonR1() == true) {
			now = new Date();
			if (now.getTime() - LastR1Press.getTime() > timeBetweenPresses) {
				ToggleBoxSpin();
			}
		}
		LastR1Press = now;
	}

	public void TeleOpGrabBox() {

		if (playStation.GetButtonRightJoystick() == true) {
			now2 = new Date();
			if (now2.getTime() - LastRightJoystickButtonPress.getTime() > timeBetweenPresses) {
				ToggleHolding();
			}

		}
		LastRightJoystickButtonPress = now2;
	}

	public void TeleOpManualLifter() {
		if (playStation.GetButtonTriangle()) {
			Map.LifterMotor.set(0.8);
		} else if (playStation.GetButtonX()) {
			Map.LifterMotor.set(-0.8);
		} else {
			Map.LifterMotor.stopMotor();
		}
	}

	public void TeleOpClimb() {
		if (playStation.GetButtonL1() == true) {
			Map.ClimbMotor.set(0.5);
		} else {
			Map.ClimbMotor.stopMotor();
		}
	}

	public void VoltageDetector() {
		SmartDashboard.putNumber("Left Front Drive", Map.LeftMotorFront.getBusVoltage());
		SmartDashboard.putNumber("Left Back Drive", Map.LeftMotorBack.getBusVoltage());
		SmartDashboard.putNumber("Right Front Drive", Map.RightMotorFront.getBusVoltage());
		SmartDashboard.putNumber("Right Front Drive", Map.RightMotorBack.getBusVoltage());
		SmartDashboard.putNumber("Climber", Map.ClimbMotor.getBusVoltage());

	}

	// public void TeleOpGoToTwoFeet() {
	// if (playStation.GetButtonSquare() == true) {
	// //IF (the lazer isnt on teh ){thing do this
	// Map.LifterMotor.set(0);
	// //else if (lazer is on the thing) {
	// //Map.LiferMotor.stopMotor();
	// //}
	// }
	// }
	//
	// public void TeleOpGoToSixFeet() {
	// if (playStation.GetButtonCircle()==true) {
	// //IF (the lazer isnt on teh ){thing do this
	// Map.LifterMotor.set(0);
	// //else if (lazer is on the thing) {
	// //Map.LiferMotor.stopMotor();
	// //}
	// }
	// }

	// TOGGLES

	public void ToggleBoxSpin() {
		if (BoxSpinMotorIsRunning == false) {
			Map.BoxSpinMotor.set(0.5);
			Map.BoxSpinVictor.set(0.5);
			BoxSpinMotorIsRunning = true;
		} else if (BoxSpinMotorIsRunning == true) {
			Map.BoxSpinMotor.stopMotor();
			Map.BoxSpinVictor.stopMotor();
			BoxSpinMotorIsRunning = false;
		}
	}

	public void ToggleHolding() {
		if (IsHoldingCube == false) {
			Map.Grabber.set(Value.kForward);
			IsHoldingCube = true;
		} else if (IsHoldingCube == true) {
			Map.Grabber.set(Value.kOff);
			IsHoldingCube = false;
		}
	}

	public void ToggleTransmission() {
		if (TransmissionIsToggled == false) {
			Map.TransmissionExchange.set(Value.kForward);
			TransmissionIsToggled = true;
		} else if (TransmissionIsToggled == true) {
			Map.TransmissionExchange.set(Value.kOff);
			TransmissionIsToggled = false;
		}
	}

}