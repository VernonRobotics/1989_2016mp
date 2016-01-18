package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.Solenoid;
import java.util.ArrayList;

public class PneumaticsCmd extends DoubleSolenoid implements cmd {
	public String type = "PneumaticsCmd";
	public ArrayList<cmd> list ;

	public JsScaled joystick; //holds joystick object
	public int chan0button; //button for chan 0 (in )
	public int chan1button; //out

	public PneumaticsCmd(int port1, int port2) {
		super(port1, port2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void disabledInit() {
		// TODO Auto-generated method stub
		this.set(0);
	}

	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub

	}

	@Override
	public void DisabledPeriodic() {
		// TODO Auto-generated method stub
		this.set(0);

	}

	@Override
	public void teleopPeriodic() {
		// Activate based on button pressed
		//using the else if / else ensures only one possible method so chan0 has precedence
		if (joystick.getRawButton(chan0button))
		{
			this.set(1);
		}
		else if (this.joystick.getRawButton(chan1button))
		{
			this.set(2);
		}
		else
		{
			this.set(0);
		}
	}
    public void testInit()
    {
    	
    }
    public void teleopInit()
    {
    	
    }


	@Override
	public void testPeriodic() {
		// TODO Auto-generated method stub
		this.teleopPeriodic();  //use teleop unless specified otherwise
	}

	//Set Joystick and specify the buttons
	public void Setjoystick(JsScaled js, int chan0, int chan1) {
		this.joystick = js;
		this.chan0button = chan0;
		this.chan1button = chan1;
	}
}
