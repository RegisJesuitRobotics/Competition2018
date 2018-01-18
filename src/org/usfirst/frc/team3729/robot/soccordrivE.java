package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

//import org.usfirst.frc.team3729.robot.commands.PlayStationController;

//import edu.wpi.first.wpilibj.Victor;

public class soccordrivE {
	Victor J, R;
	PlayStationController playStation;

	public soccordrivE(PlayStationController playStation) {
		
		playStation = new PlayStationController(0);
		this.playStation = playStation;
		J = new Victor(0);
		R = new Victor(1);
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
		//System.out.println("left"+LeftStick);
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
		//System.out.println("lp"+LeftPower);
		//System.out.println("rp"+RightPower);
		J.set(-LeftPower);
		R.set(RightPower);
	}
	
	public void autonomous () {
		for (int i = 0; i < 4; i ++) {
			J.set(-1.0);
			R.set(1.0);
			
		}
	}
	
}


