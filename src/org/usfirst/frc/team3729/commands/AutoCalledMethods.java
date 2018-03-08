package org.usfirst.frc.team3729.commands;

import edu.wpi.first.wpilibj.Timer;
import subsystems.PortMap;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class AutoCalledMethods {
	subsystems.PortMap Map;

	public AutoCalledMethods() {

		Map = PortMap.getInstance();
	}

	// AUTO CODE BUILDING BLOCKS

	public void AutoGoForeward(double speed, int time) {

		Map.LeftDriveMotorFront.set(-speed * DriveClass.SideCompensation);
		Map.RightDriveMotorFront.set(speed);
		Map.LeftDriveMotorBack.set(-speed * DriveClass.SideCompensation);
		Map.RightDriveMotorBack.set(speed);
		Timer.delay(time);
		stopMotors();
	}

	public void AutoGoRight(double speed, int time) {
		Map.LeftDriveMotorFront.set(-speed * 0.5 * DriveClass.SideCompensation);
		Map.RightDriveMotorFront.set(speed);
		Map.LeftDriveMotorBack.set(-speed * DriveClass.SideCompensation);
		Map.RightDriveMotorBack.set(speed);
		Timer.delay(time);
		stopMotors();
	}

	public void AutoGoLeft(double speed, int time) {

		Map.LeftDriveMotorFront.set(-speed * 0.5 * DriveClass.SideCompensation);
		Map.RightDriveMotorFront.set(speed);
		Map.LeftDriveMotorBack.set(-speed * DriveClass.SideCompensation);
		Map.RightDriveMotorBack.set(speed);
		Timer.delay(time);
		stopMotors();
	}

	public void AutoPointTurnRight(double speed, int time) {

		Map.LeftDriveMotorFront.set(-speed * 0.5 * DriveClass.SideCompensation);
		Map.RightDriveMotorFront.set(speed);
		Map.LeftDriveMotorBack.set(-speed * DriveClass.SideCompensation);
		Map.RightDriveMotorBack.set(speed);
		Timer.delay(time);
		stopMotors();
	}

	public void AutoPointTurnLeft(double speed, int time) {

		Map.LeftDriveMotorFront.set(-speed * 0.5 * DriveClass.SideCompensation);
		Map.RightDriveMotorFront.set(speed);
		Map.LeftDriveMotorBack.set(-speed * DriveClass.SideCompensation);
		Map.RightDriveMotorBack.set(speed);
		Timer.delay(time);
		stopMotors();
	}

	public void AutoStop(int time) {
		stopMotors();
		Timer.delay(time);

	}

	// public void AutoThrow() {
	// Map.Grabber.set(Value.kReverse);
	// Timer.delay(3);
	// Map.Grabber.set(Value.kOff);
	// }

	public void stopMotors() {
		Map.LeftDriveMotorFront.stopMotor();
		Map.RightDriveMotorFront.stopMotor();
		Map.LeftDriveMotorBack.stopMotor();
		Map.RightDriveMotorBack.stopMotor();
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
