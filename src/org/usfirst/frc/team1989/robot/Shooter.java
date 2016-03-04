package org.usfirst.frc.team1989.robot;

// All Imports - Will remove unecessary later
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Shooter extends  a_cmd {
	
	CANTalon shootMotor1;
	CANTalon shootMotor2;
	CANTalon elevator;
	JsScaled driveStick;
	Servo s1;
	Timer t1 = new Timer();
	int lastaction = 0; //0 off, 1 pickup 2 spinup
	double lasti =0.0; //last current	
	double nexttimer = 0.0;
	
	public Shooter(CANTalon shootMotor1, CANTalon shootMotor2, CANTalon elevator, JsScaled driveStick, Servo s1){
		this.shootMotor1 = shootMotor1;
		this.shootMotor2 = shootMotor2;
		this.elevator = elevator;
		this.driveStick = driveStick;
		this.s1 = s1;
		
	}
	
	public void elevatorOperation(){
		if(driveStick.getPOV(0) == 180){
			elevator.set(.2);
		}else if(driveStick.getPOV(0) == 0){
			elevator.set(-.2);
		}else{
			elevator.set(0);
		}
		
	}
	
	public void shootMotorOperation(){
		//so the shooter needs a timer and a state and some logic depending on state
		Robot.sharedStuff.msg[0] =" Left s I " + shootMotor1.getOutputCurrent();
		Robot.sharedStuff.msg[5] = "right s I " + shootMotor2.getOutputCurrent();

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
	
	public void servoOperation(){
		
		if(driveStick.getRawButton(1) == true){
			s1.set(1);
			t1.start();
			
			
		}
	}
	/* Teleop Init and Teleop Periodic */
	public void teleopInit(){
		// Once we have a limit switch then make the elevator start at the bottom.
		s1.set(0);
	}
    public void teleopPeriodic(){
    	if(t1.get() > .2 ){
    		s1.set(0);
    		t1.stop();
    		t1.reset();
    	}
		
    	servoOperation();
    	elevatorOperation();
    	shootMotorOperation();
    }

    
    public void autonomousPeriodic(){}
    public void DisabledPeriodic(){}
    public void testInit(){}
    public void testPeriodic(){}
    public void disabledInit(){}
    public void autonomousInit(){}
}
