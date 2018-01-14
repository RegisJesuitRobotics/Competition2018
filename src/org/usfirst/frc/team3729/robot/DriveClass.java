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

	public void AutoGoForeward(double speed, int time) {
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
	
	
}