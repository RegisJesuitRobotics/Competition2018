package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Joystick;

public class PlayStationController extends Joystick {

	public PlayStationController(int port) {
		super(port);

	}

	
	public boolean GetButtonX (){
		return super.getRawButton(0);
		
	}
	public boolean GetButtonY (){ 
		return super.getRawButton(1);
	
	}
	public boolean GetButtonO (){
		return super.getRawButton(3);
		
	}
	public boolean GetButtonZ () {
		return super.getRawButton(4);
		
	}
	public boolean GetButtonL1 (){
		return super.getRawButton(5);
		
	}
	public boolean GetButtonR1 (){
		return super.getRawButton(6);
		
	}
	public boolean GetButtonL2 (){
		return super.getRawButton(7);
	}
	public boolean GetButtonR2 (){
		return super.getRawButton(8);
	} 
	public boolean GetShare (){
		return super.getRawButton(9);
	}
	public boolean GetOption (){
		return super.getRawButton(10);
	}
	public boolean GetLeftJoystck (){
		return super.getRawButton(11);
	}
	public boolean GetRightJoystick (){
		return super.getRawButton(12);
	}
	public boolean GetHomeButton (){
		return super.getRawButton(13);
	}
	public boolean GetTouchScreen (){
		return super.getRawButton(14);
}
	public double GetLeftAxisX (){
        return super.getRawAxis(0);
	}
	public double GetLeftAxisY (){
        return super.getRawAxis(1);
	}
	public double GetRightAxisZ (){
        return super.getRawAxis(2);
	}
	public double GetRightRotateZ (){
        return super.getRawAxis(5);
	}
	public double GetLeftRotateX (){
        return super.getRawAxis(3);
	}
	public double GetRightRotateY (){
        return super.getRawAxis(4);
	}
}