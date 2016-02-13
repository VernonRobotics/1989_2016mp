package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

public class Shooter implements cmd {

	CANTalon s1 = new CANTalon(4);
	CANTalon s2 = new CANTalon(5);
	Timer timer = new Timer();
	JsScaled drivestick = new JsScaled(0);
	
	
	
	
	
	@Override
	
	
	
	public void disabledInit() {
		// TODO Auto-generated method stub
		
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
		
	}

	@Override
	public void testInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testPeriodic() {
		// TODO Auto-generated method stub
		
	}
	
	public void pickupBall() {
		if(drivestick.getRawButton(3) == true){
			s1.set(0.2);
			s1.set(-0.2);
		}
		
	}
	public void spinWheels(){
	
	}
	
}
