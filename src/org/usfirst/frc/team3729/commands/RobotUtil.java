package org.usfirst.frc.team3729.commands;

import org.usfirst.frc.team3729.robot.FieldElementScoringSide;
import org.usfirst.frc.team3729.robot.FieldStartingPosition;
import edu.wpi.first.wpilibj.Timer;
import subsystems.PlayStationController;
import subsystems.PortMap;
import org.usfirst.frc.team3729.commands.DriveClass;

public class RobotUtil {
	DriveClass driveClass;
	subsystems.PortMap Map;
	public double SideCompensation;

	public RobotUtil() {

		SideCompensation = 1.0; // TODO compensates un equal weight on physical robot
		driveClass = new DriveClass(null);

		Map = new PortMap();

	}// This is the class with all the base level commands for autonomous
		// Don't edit these if you want to edit autonomous; these are called in drive
		// class

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

	public void AutoGrab(double speed, int time) {
		Map.GrabberMotorLeft.set(speed * 0.8);
		Map.GrabberMotorRight.set(speed * 0.8);
		Timer.delay(time);
		Map.GrabberMotorLeft.stopMotor();
		Map.GrabberMotorRight.stopMotor();

	}

	public void AutoLifter(double speed, int time) {
		Map.LifterMotor.set(speed * 0.8);
		Timer.delay(time);
		Map.LifterMotor.stopMotor();
	}

	public void stopMotors() {
		Map.LeftMotorFront.stopMotor();
		Map.RightMotorFront.stopMotor();
		Map.LeftMotorBack.stopMotor();
		Map.RightMotorBack.stopMotor();
	}

}
