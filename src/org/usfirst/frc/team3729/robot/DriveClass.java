package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Talon;

public class DriveClass {
	PlayStationController PlayStation;
	Talon BackRightDrive, BackLeftDrive, Rightmotor, Leftmotor;

	public DriveClass(PlayStationController PlayStation) {
		PlayStation = new PlayStationController(0);
		this.PlayStation = PlayStation;

		BackRightDrive = new Talon(0);
		BackLeftDrive = new Talon(0);
		Rightmotor = new Talon(0);
		Leftmotor = new Talon(0);
	}
}
