package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Talon;

public class DriveClass {
	PlayStationController PlayStation;
	Talon BackRightDrive, BackLeftDrive, FrontRightDrive, FrontLeftDrive;

	public DriveClass(PlayStationController PlayStation) {

		PlayStation = new PlayStationController(0);
		this.PlayStation = PlayStation;

		BackRightDrive = new Talon(0);
		BackLeftDrive = new Talon(0);
		FrontRightDrive = new Talon(0);
		FrontLeftDrive = new Talon(0);
	}
	// DRIVE CODE

	public void Drive() {
		double RightTrigger = PlayStation.GetRightTrigger();
		double LeftTrigger = PlayStation.GetLeftTrigger();
		double LeftStickXAxis = PlayStation.GetLeftStickAxisX();
		double Deadzone = 0.1;
		double LeftPower;
		double RightPower;
		double Power;
		double turn = 2 * LeftStickXAxis;
		Power = RightTrigger - LeftTrigger;
		// TO TURN LEFT
		if (LeftStickXAxis > Deadzone) {

			RightPower = Power - (turn * Power);
			LeftPower = Power;
		} else if (LeftStickXAxis < -Deadzone) {
			LeftPower = Power + (turn * Power);
			RightPower = Power;
		} else {
			LeftPower = Power;
			RightPower = Power;
		}
		FrontLeftDrive.set(LeftPower);
		FrontRightDrive.set(RightPower);
		BackLeftDrive.set(LeftPower);
		BackRightDrive.set(RightPower);

	}

	// ALL AUTO COMMANDS BELOW THIS
	/* public void AutoGoForeward(double speed, int time) {
		AutoGoForeward(1, 10);
		BackLeftDrive.set(speed);
		BackRightDrive.set(speed);
		FrontRightDrive.set(speed);
		FrontLeftDrive.set(speed);
		try {
			wait(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BackLeftDrive.set(0.0);

		BackRightDrive.set(0.0);

		FrontRightDrive.set(0.0);

		FrontLeftDrive.set(0.0);
	}

	public void AutoGoRight(double speed, int time) {
		AutoGoRight(1, 5);
		BackLeftDrive.set(speed * 0.5);
		BackRightDrive.set(speed);
		FrontRightDrive.set(speed);
		FrontLeftDrive.set(speed * 0.5);
		try {
			wait(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BackLeftDrive.set(0.0);

		BackRightDrive.set(0.0);

		FrontRightDrive.set(0.0);

		FrontLeftDrive.set(0.0);
	}

	public void AutoGoLeft(double speed, int time) {
		AutoGoLeft(1, 5);

		BackLeftDrive.set(speed);
		BackRightDrive.set(speed * 0.5);
		FrontRightDrive.set(speed);
		FrontLeftDrive.set(speed * 0.5);
		try {
			wait(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BackLeftDrive.set(0.0);

		BackRightDrive.set(0.0);

		FrontRightDrive.set(0.0);

		FrontLeftDrive.set(0.0);
	}
*/
}