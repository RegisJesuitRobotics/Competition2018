package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Talon;

//import org.usfirst.frc.team3729.robot.commands.PlayStationController;

import edu.wpi.first.wpilibj.Victor;

public class soccordrivE {
	Talon J, R;
	PlayStationController playStation;

	public soccordrivE(PlayStationController playStation) {
		J = new Talon(0);
		R = new Talon(1);
		playStation = new PlayStationController(0);
		this.playStation = playStation;
	}

	public void soccordrivE1() {
		double RightTrigger = playStation.GetRightTrigger();
		double LeftTrigger = playStation.GetLeftTrigger();
		double LeftStick = playStation.GetLeftStickAxisX();
		double Deadzone = 0.1;
		double RightPower;
		double LeftPower;
		double Power;
		double turn = 2 * LeftStick;
		Power = RightTrigger - LeftTrigger;
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
		J.set(-LeftPower);
		R.set(RightPower);
	}
}


