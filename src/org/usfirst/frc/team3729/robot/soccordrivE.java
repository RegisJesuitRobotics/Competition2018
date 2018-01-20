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
		// System.out.println("lp"+LeftPower);
		// System.out.println("rp"+RightPower);
		J.set(-LeftPower);
		R.set(RightPower);
	}

	public void AutoGoForeward(double speed, int time) {
		// TODO Auto-generated method stub
		J.set(-speed);
		R.set(speed);
		try {
			wait(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		J.set(0);
		R.set(0);
	}

	public void AutoGoRight(double speed, int time) {
		AutoGoRight(1, 5);
		J.set(-speed * 0.5);
		R.set(speed);
		try {
			wait(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		J.set(0.0);
		R.set(0);
	}

	public void AutoGoLeft(double speed, int time) {
		AutoGoLeft(1, 5);

		J.set(-speed);
		R.set(speed * 0.5);
		try {
			wait(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		J.set(0.0);
		R.set(0);
	}

}
