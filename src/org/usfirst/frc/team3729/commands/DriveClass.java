package org.usfirst.frc.team3729.commands;

import java.util.Date;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import subsystems.PlayStationController;
import subsystems.PortMap;

public class DriveClass {
	PlayStationController playStation;
	subsystems.PortMap Map;
	AutoCalledMethods Auto;

	double timeBetweenPresses = 100;
	public static double SideCompensation;
	public int LifterState;
	boolean BoxSpinMotorIsRunning;
	boolean IsHoldingCube;
	boolean TransmissionIsToggled;

	Date LastHomePress = new Date();
	Date LastSquarePress = new Date();
	Date LastCirclePress = new Date();

	public DriveClass(PlayStationController playStation) {
		SideCompensation = 1.0;
		playStation = new PlayStationController(0);
		this.playStation = playStation;
		Map = PortMap.getInstance();
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
		double Limiter = 1;
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

		Map.LeftDriveMotorFront.set(-LeftPower * Limiter * SideCompensation);
		Map.RightDriveMotorFront.set(RightPower * Limiter);
		Map.RightDriveMotorBack.set(RightPower * Limiter);
		Map.LeftDriveMotorBack.set(-LeftPower * Limiter * SideCompensation);
	}

	// NON DRIVE TELEOP METHODS

	public void TeleOpGetVoltage() {
		// SmartDashboard.putNumber("Left Drive Motor",
		// Map.LeftDriveMotorFront.getBusVoltage());
		// SmartDashboard.putNumber("Right Drive Motor",
		// Map.RightDriveMotorFront.getBusVoltage());
		// SmartDashboard.putNumber("Box Spin Motor", Map.GrabberMotor.getBusVoltage());
		// SmartDashboard.putNumber("Climber Motor", Map.ClimbMotor.getBusVoltage());
		// SmartDashboard.putNumber("Lifter Motor", Map.LifterMotor.getBusVoltage());
	}

	// public void TeleOpBoxSpin() {
	//
	// if (playStation.GetButtonSquare() == true) {
	// Date now = new Date();
	// if (now.getTime() - LastSquarePress.getTime() > timeBetweenPresses) {
	// ToggleBoxSpin();
	//
	// }
	// LastSquarePress = now;
	// }
	// SmartDashboard.putBoolean("Spinner is running", BoxSpinMotorIsRunning);
	// }

	public void TeleOpGrabBox() {

		if (playStation.GetButtonCircle() == true) {
			Date now = new Date();
			if (now.getTime() - LastCirclePress.getTime() > timeBetweenPresses) {
				ToggleHolding();
			}
			LastCirclePress = now;
		}
	}

	public void TeleOpManualLifter() {
		if (playStation.GetButtonTriangle()) {
			Map.LifterMotor.set(-1);
		} else if (playStation.GetButtonX()) {
			Map.LifterMotor.set(1);
		} else {
			Map.LifterMotor.stopMotor();
		}
	}

	public void TeleOpClimb() {
		if (playStation.GetButtonL1() == true) {
			Map.ClimbMotor.set(1);
		} else if (playStation.GetButtonR1()) {
			Map.ClimbMotor.set(-1);
		} else {
			Map.ClimbMotor.stopMotor();
		}

	}

	public void TeleOpTransmissionExchange() {
		if (playStation.GetButtonHome() == true) {
			Date now = new Date();
			if (now.getTime() - LastHomePress.getTime() > timeBetweenPresses) {
				ToggleTransmission();
			}
			LastHomePress = now;
		}

	}

	// TODO EDIT RAISE CONDITIONS
	// public void TeleOpRaiseTwoFeet() {
	// if (playStation.GetButtonSquare() == true) {
	// Date now = new Date();
	// // TODO FIX
	// if (now.getTime() - LastSquarePress.getTime() > 6000000) {
	//
	// Thread AutoLift = new Thread(() -> {
	//
	// Map.LifterMotor.set(0);
	// Timer.delay(0);
	// Map.LifterMotor.stopMotor();
	//
	// });
	// AutoLift.start();
	//
	// }
	// LastRightJoystickButtonPress = now;
	//
	// }
	//
	// }

	// public void ToggleBoxSpin() {
	// if (BoxSpinMotorIsRunning == false) {
	// Map.GrabberMotor.set(1);
	// BoxSpinMotorIsRunning = true;
	// } else if (BoxSpinMotorIsRunning == true) {
	// Map.GrabberMotor.stopMotor();
	// BoxSpinMotorIsRunning = false;
	// }
	// }

	public void ToggleHolding() {
		if (IsHoldingCube == false) {
			Map.GrabberSolenoid.set(Value.kReverse);
			IsHoldingCube = true;
		} else if (IsHoldingCube == true) {
			Map.GrabberSolenoid.set(Value.kForward);
			IsHoldingCube = false;
		}
	}

	public void ToggleTransmission() {
		if (TransmissionIsToggled == false) {
			Map.TransmisionExchangeSolenoid.set(Value.kReverse);
			TransmissionIsToggled = true;
		} else if (TransmissionIsToggled == true) {
			Map.TransmisionExchangeSolenoid.set(Value.kForward);
			TransmissionIsToggled = false;
		}
	}

	// Im pretending to be typing important things right now
	// WEW LAD THIS IS SPOOKY
	// HAHAHAHAHAHA HElp im kinda SHOOK RN
	// lmao this will still compile

}