package org.usfirst.frc.team3729.commands;

import java.util.Date;

import org.usfirst.frc.team3729.robot.FieldElementScoringSide;
import org.usfirst.frc.team3729.robot.FieldStartingPosition;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import subsystems.PlayStationController;
import subsystems.PortMap;

public class DriveClass {

	PlayStationController playStation;
	subsystems.PortMap Map;
	double timeBetweenPresses = 1;
	Date now = new Date();
	Date now2 = new Date();
	Date now3 = new Date();
	Date LastRightJoystickButtonPress = new Date();
	Date LastR1Press = new Date();
	Date LastHomePress = new Date();
	public double SideCompensation;
	public int LifterState;
	boolean SuccIsGoing;
	boolean IsHoldingCube;
	boolean TransmissionIsToggled;

	public DriveClass(PlayStationController playStation) {
		SideCompensation = 1.0;
		playStation = new PlayStationController(0);
		this.playStation = playStation;
		Map = new PortMap();
		SuccIsGoing = false;
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

	// public void TeleOpGoToSixFeet() {
	// if (playStation.GetButtonCircle() == true) {
	// //IF (the lazer isnt on teh ){thing do this
	// Map.LifterMotor.set(0);
	// //else if (lazer is on the thing) {
	// //Map.LiferMotor.stopMotor();
	// //}
	// }
	// }

	// TOGGLES

	public void ToggleBoxSpin() {
		if (SuccIsGoing == false) {
			Map.BoxSpinMotor.set(0.5);
			Map.BoxSpinVictor.set(0.5);
			SuccIsGoing = true;
		} else if (SuccIsGoing == true) {
			Map.BoxSpinMotor.stopMotor();
			Map.BoxSpinVictor.stopMotor();
			SuccIsGoing = false;
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

	// AUTO MODES

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
			AutoLifter();
			AutoGoForeward(0.23, 1);
			AutoThrow();
			System.out.println("starbucks1");

		} else if (StartingPosition == FieldStartingPosition.Right && SwitchSide == FieldElementScoringSide.Right) {
			// START RIGHT, ELEMENT RIGHT

			AutoGoForeward(0.5, 2);
			AutoStop(2);
			AutoPointTurnLeft(0.424, 1);
			AutoStop(1);
			AutoLifter();
			AutoGoForeward(0.23, 1);
			AutoThrow();
			System.out.println("coffee");
		} else if (StartingPosition == FieldStartingPosition.Left && SwitchSide == FieldElementScoringSide.Right) {
			// START LEFT, ELEMENT RIGHT

			AutoGoForeward(0.668, 2);
			AutoStop(1);
			AutoPointTurnRight(0.39, 1);
			AutoStop(1);
			AutoGoForeward(0.689, 2);
			AutoLifter();
			AutoThrow();
			System.out.println("chipotle");

		} else if (StartingPosition == FieldStartingPosition.Right && SwitchSide == FieldElementScoringSide.Left) {
			// START RIGHT, ELEMENT LEFT
			AutoGoForeward(0.668, 2);
			AutoStop(1);
			AutoPointTurnLeft(0.44, 1);
			AutoStop(1);
			AutoGoForeward(0.7, 2);
			AutoStop(1);
			AutoPointTurnLeft(0.45, 1);
			AutoStop(1);
			AutoGoForeward(0.57, 1);
			AutoPointTurnLeft(0.38, 1);
			AutoLifter();
			AutoThrow();
			System.out.println("chili's");
		}
	}

	public void AutoScoreScale(FieldElementScoringSide ScaleSide, FieldStartingPosition StartingPosition) {
		if (StartingPosition == FieldStartingPosition.Left && ScaleSide == FieldElementScoringSide.Left) {
			// Start left element on left
			AutoGoForeward(1, 2);
			AutoStop(2);
			AutoPointTurnRight(0.424, 1);
			AutoStop(2);
			AutoLifter();
			AutoGoForeward(0, 0);
			AutoThrow();
			System.out.println("Thiccer than a snicker");

		} else if (StartingPosition == FieldStartingPosition.Right && ScaleSide == FieldElementScoringSide.Right) {
			// Start right element on right
			AutoGoForeward(1, 2);
			AutoStop(2);
			AutoPointTurnLeft(0.424, 1);
			AutoStop(2);
			AutoLifter();
			AutoGoForeward(0, 0);
			AutoThrow();
			System.out.println("Fetcher than a sketcher");
			//
		} else if (StartingPosition == FieldStartingPosition.Left && ScaleSide == FieldElementScoringSide.Right) {
			// Start left element on Right
			AutoGoForeward(1, 3);
			AutoStop(2);
			AutoPointTurnRight(0.424, 1);
			AutoStop(2);
			AutoGoForeward(0.7, 3);
			AutoStop(2);
			AutoPointTurnLeft(0.424, 0);
			AutoStop(2);
			AutoGoForeward(0, 0);
			AutoStop(2);
			AutoPointTurnLeft(0, 0);
			AutoStop(2);
			AutoLifter();
			AutoGoForeward(0, 0);
			AutoThrow();
			System.out.println("Gayer than a mayor");

		} else if (StartingPosition == FieldStartingPosition.Right && ScaleSide == FieldElementScoringSide.Left) {
			// Start right element on right
			AutoGoForeward(1, 3);
			AutoStop(2);
			AutoPointTurnLeft(0.424, 1);
			AutoStop(2);
			AutoGoForeward(0.7, 3);
			AutoStop(2);
			AutoPointTurnRight(0.424, 0);
			AutoStop(2);
			AutoGoForeward(0, 0);
			AutoStop(2);
			AutoPointTurnRight(0, 0);
			AutoStop(2);
			AutoLifter();
			AutoGoForeward(0, 0);
			AutoThrow();
			System.out.println("Finer than a Diner");

		}
	}

	// AUTO CODE BUILDING BLOCKS
	public void AutoGoForeward(double speed, int time) {

		Map.LeftMotorFront.set(-speed * SideCompensation);
		Map.RightMotorFront.set(speed);
		Map.LeftMotorBack.set(-speed * SideCompensation);
		Map.RightMotorBack.set(speed);
		Timer.delay(time);
		stopMotors();
	}

	public void AutoGoRight(double speed, int time) {
		Map.LeftMotorFront.set(-speed * 0.5 * SideCompensation);
		Map.LeftMotorBack.set(-speed * 0.5 * SideCompensation);
		Map.RightMotorFront.set(speed);
		Map.RightMotorBack.set(speed);
		Timer.delay(time);
		stopMotors();
	}

	public void AutoGoLeft(double speed, int time) {

		Map.LeftMotorFront.set(-speed * SideCompensation);
		Map.LeftMotorBack.set(-speed * SideCompensation);
		Map.RightMotorFront.set(speed * 0.5 * 0.8);
		Map.RightMotorBack.set(speed * 0.5 * 0.8);
		Timer.delay(time);
		stopMotors();
	}

	public void AutoPointTurnRight(double speed, int time) {

		Map.LeftMotorFront.set(-speed * SideCompensation);
		Map.LeftMotorBack.set(-speed * SideCompensation);
		Map.RightMotorFront.set(-speed);
		Map.RightMotorBack.set(-speed);
		Timer.delay(time);
		stopMotors();
	}

	public void AutoPointTurnLeft(double speed, int time) {

		Map.LeftMotorFront.set(speed * SideCompensation);
		Map.LeftMotorBack.set(speed * SideCompensation);
		Map.RightMotorFront.set(speed);
		Map.RightMotorBack.set(speed);
		Timer.delay(time);
		stopMotors();
	}

	public void AutoStop(int time) {
		stopMotors();
		Timer.delay(time);

	}

	public void AutoThrow() {
		Map.Grabber.set(Value.kReverse);
		Timer.delay(3);
		Map.Grabber.set(Value.kOff);
	}

	public void stopMotors() {
		Map.LeftMotorFront.stopMotor();
		Map.RightMotorFront.stopMotor();
		Map.LeftMotorBack.stopMotor();
		Map.RightMotorBack.stopMotor();
	}

	public void AutoLifter() {
		Thread AutoLift = new Thread(() -> {
			boolean ThreadIsDoinIt = true;
			while (ThreadIsDoinIt == true) {
				Map.LifterMotor.set(0.6);
				Timer.delay(0);
				Map.LifterMotor.stopMotor();
				ThreadIsDoinIt = false;
			}
		});
		AutoLift.start();
	}
}
