package org.usfirst.frc.team1989.robot;

import org.usfirst.frc.team1989.robot.Robot.sharedStuff;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

public class Shooter  extends a_cmd{

	public CANTalon shootMotor1;
	public CANTalon shootMotor2;
	JsScaled driveStick;
	int TalonID1;
	int TalonID2;
	Timer t1 = new Timer();
	int lastaction = 0; //0 off, 1 pickup 2 spinup
	double lasti =0.0; //last current	
	double nexttimer = 0.0;
	 
	
	
	public Shooter(CANTalon shootMotor1,CANTalon shootMotor2,JsScaled driveStick){
		this.shootMotor1 = shootMotor1;
		this.shootMotor2 = shootMotor2;
		this.driveStick = driveStick;
	}


	@Override
	
	
	
	public void disabledInit() {
		// TODO Auto-generated method stub
		shootMotor1.set(0);
		shootMotor2.set(0);
		
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
			shootMotor1.set(0);
			shootMotor2.set(0);
		
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
		// if neither 3 or 1 is pressed we turn off - this is for test only
		// we need a logic to turn it off too or it shuts off after a while
		//so the shooter needs a timer and a state and some logic depending on state
		sharedStuff.msg[0] =" Left s I " + shootMotor1.getOutputCurrent();
		sharedStuff.msg[5] = "right s I " + shootMotor2.getOutputCurrent();

		if(driveStick.getRawButton(2) == true){
			this.lastaction = 1;
			shootMotor1.set(-.35);
			shootMotor2.set(.35);
			Robot.sharedStuff.led[0] = false;		}
		else if (driveStick.getRawButton(1)){
			if(lastaction != 2){
				t1.stop();
				t1.reset();
				t1.start();
				Robot.sharedStuff.led[0] = false;
				nexttimer = 1.0;
			}
			else if(t1.get() > nexttimer && !Robot.sharedStuff.led[0]){
				nexttimer = t1.get() + .3;
				if(lasti-.25 > shootMotor1.getOutputCurrent() && lasti+.25 > shootMotor1.getOutputCurrent()){
					Robot.sharedStuff.led[0] = true;
				}
				lasti = shootMotor1.getOutputCurrent();
			}
			shootMotor1.set(1);
			shootMotor2.set(-1);
		}
		else
		{
			Robot.sharedStuff.led[0] = false;
			shootMotor1.set(0);
			shootMotor2.set(0);
			this.lastaction = 0;
			
		}
		
	}

	@Override
	public void testPeriodic() {
		// TODO Auto-generated method stub
		
	}
	
	public void pickupBall() {
		if(driveStick.getRawButton(3) == true){
			shootMotor1.set(0.2);
			shootMotor2.set(-0.2);
		}
		
	}
	public void spinWheels(){
		if (driveStick.getRawButton(1)){
			shootMotor1.set(-0.7);
			shootMotor2.set(0.7);
		}
	}
	
}
