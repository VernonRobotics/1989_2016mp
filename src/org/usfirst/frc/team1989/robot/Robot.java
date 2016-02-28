
//Martin's Code

package org.usfirst.frc.team1989.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * 
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	double driveramp = 6.0;

	// Instantiating Talon Motors
	CANTalon frontLeftMotor = new CANTalon(3);
	CANTalon frontRightMotor = new CANTalon(9);
	CANTalon rearLeftMotor = new CANTalon(6);
	CANTalon rearRightMotor = new CANTalon(7);
	CANTalon shootMotor1 = new CANTalon(4);
	CANTalon shootMotor2 = new CANTalon(8);
	CANTalon elevator = new CANTalon(5);
	String[] lastmsg = new String[10];
	Boolean[] lastled = new Boolean[5];
	String[] msg = new String[10];
	Boolean[] led = new Boolean[5];
	
	// Instantiating Timer
	Timer t1 = new Timer();
	
	// Instantiating Servo
	Servo s1 = new Servo(0);

	// Instantiating Joysticks
	JsScaled driveStick = new JsScaled(0);
	
	// ArcadeDriveCMD Constructor - 4 motors
	ArcadeDriveCmd aDrive = new ArcadeDriveCmd(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor,
			driveStick);
	writemessage wmsg = new writemessage();
	// CMD List - Stores objects of each class to be run.
	
	Shooter shooter = new Shooter(shootMotor1, shootMotor2, driveStick);
	public static class sharedStuff{
		public static ArrayList<a_cmd> cmdlist = new ArrayList<a_cmd>();
		public static String[] msg = new String[10] ;
		public static Boolean[] led = new Boolean[5];
		public static String[] lastmsg = new String[10];
		public static Boolean[] lastled = new Boolean[5];


		/*
		    * returns an a_cmd with type stringcmd
		    */
		public static a_cmd findcmd(String cmd)
		{
			
			for (int i = 0; i < cmdlist.size(); i++) {
				 if (cmdlist.get(i).type == cmd){
					 return cmdlist.get(i);
				 };
			}

			return null;
		}

	}
	

	public void robotInit() {
		
		System.out.println("i'm Alive");
		
		// Construct CMD List
		sharedStuff.cmdlist.add( aDrive);
		sharedStuff.cmdlist.add(shooter);
		
		sharedStuff.cmdlist.add(wmsg);  // sb added last so that other objects can update first
		
		// Limit Switches
		frontLeftMotor.enableLimitSwitch(false, false);
		frontRightMotor.enableLimitSwitch(false, false);
		rearLeftMotor.enableLimitSwitch(false, false);
		rearRightMotor.enableLimitSwitch(false, false);
		shootMotor1.enableLimitSwitch(false, false);
		shootMotor2.enableLimitSwitch(false, false);

		// Voltage Ramps - none for now
//		frontLeftMotor.setVoltageRampRate(driveramp);
//		frontRightMotor.setVoltageRampRate(driveramp);
//		rearLeftMotor.setVoltageRampRate(driveramp);
//		rearRightMotor.setVoltageRampRate(driveramp);

//add ref to list


	}

	// 
	public void autonomousInit() {
		for (int i = 0; i < sharedStuff.cmdlist.size(); i++) {
			sharedStuff.cmdlist.get(i).autonomousInit();
		}

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		for (int i = 0; i < sharedStuff.cmdlist.size(); i++) {
			sharedStuff.cmdlist.get(i).autonomousPeriodic();
		}

	}

	public void DisabledPeriodic() {
		
		//  In disabled, all speeds should be set to 0
		elevator.set(0);
		shootMotor1.set(0);
		shootMotor2.set(0);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		for (int i = 0; i < sharedStuff.cmdlist.size(); i++) {
			sharedStuff.cmdlist.get(i).teleopPeriodic();
		}
	}

	public void testInit()

	{
		t1.start();
	}

	/**
	 * This function is called periodically during test mode
	 * 
	 */
	public void testPeriodic() {
		// adrive.arcadeDrive(0-driveStick.sgetY(),0-driveStick.sgetTwist());

		// Servo Logic
		if (driveStick.getRawButton(5) == true) {
			s1.set(0);
		} else if (driveStick.getRawButton(6) == true) {
			s1.set(1);
		}
		
		// Shooting Logic
		if (driveStick.getRawButton(2) == true) {
			
			// Motors for pickup
			shootMotor1.set(-.35);
			shootMotor2.set(.35);
		} else if (driveStick.getRawButton(1)) {
			
			// Motors for shoot
			shootMotor1.set(1);
			shootMotor2.set(-1);
		} else {
			shootMotor1.set(0);
			shootMotor2.set(0);
		}

		// Elevator Logic
		if (driveStick.getRawButton(3) == true) {
			elevator.set(.2);
		} else if (driveStick.getRawButton(4)) {
			elevator.set(-.2);
		} else {
			elevator.set(-.05);
		}

		// Debug Output
		if (t1.get() > .25 && false) {
			t1.reset();
			t1.start();
			SmartDashboard.putString("DB/String 0", " Left I " + frontLeftMotor.getOutputCurrent());
			SmartDashboard.putString("DB/String 5", "right I " + frontRightMotor.getOutputCurrent());
			SmartDashboard.putString("DB/String 1", " Left O " + frontLeftMotor.getOutputVoltage());
			SmartDashboard.putString("DB/String 6", "right O " + frontRightMotor.getOutputVoltage());
			SmartDashboard.putString("DB/String 2", " Left V " + frontLeftMotor.getBusVoltage());
			SmartDashboard.putString("DB/String 7", "right V " + frontRightMotor.getBusVoltage());
			SmartDashboard.putString("DB/String 3", " Enc pos " + elevator.getEncPosition());
			SmartDashboard.putString("DB/String 8", "getpos" + elevator.getPosition());
			SmartDashboard.putString("DB/String 4", " sh1 I " + shootMotor1.getOutputCurrent());
			SmartDashboard.putString("DB/String 9", "right S " + shootMotor2.getOutputCurrent());
		}

	}

}