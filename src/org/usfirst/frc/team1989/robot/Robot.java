
package org.usfirst.frc.team1989.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
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
	
	CANTalon frontleftmotor = new CANTalon(3);
	CANTalon frontrightmotor = new CANTalon(9);
	CANTalon backleftmotor = new CANTalon(2);
	CANTalon backrightmotor = new CANTalon(3);
	
	
    JsScaled utilityStick = new JsScaled(0);
    JsScaled driveStick = new JsScaled(1);
    ArcadeDriveCmd aDrive = new ArcadeDriveCmd(frontleftmotor, frontrightmotor, driveStick);
    ArrayList<cmd> cmdlist = new ArrayList<cmd>();
    Joystick js = new Joystick(0);
    
   RobotDrive drive = new RobotDrive(frontleftmotor, frontrightmotor);
   
    
    
    public void robotInit() {
    	cmdlist.add(aDrive);
    	frontleftmotor.enableLimitSwitch(false, false);
    	frontrightmotor.enableLimitSwitch(false, false);
    	backleftmotor.enableLimitSwitch(false, false);
    	backrightmotor.enableLimitSwitch(false, false);
    	
    	frontleftmotor.setVoltageRampRate(driveramp);
    	frontrightmotor.setVoltageRampRate(driveramp);
    	backleftmotor.setVoltageRampRate(driveramp);
    	backrightmotor.setVoltageRampRate(driveramp);
    	    	
    }

    public void autonomousInit() {
    	for (int i = 0; i < cmdlist.size(); i++) {
    	    cmdlist.get(i).autonomousInit();
    	}
    	
    	
    	    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	for (int i = 0; i < cmdlist.size(); i++) {
    	    cmdlist.get(i).autonomousPeriodic();
    	}
    	
  	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	for (int i = 0; i < cmdlist.size(); i++) {
    	    cmdlist.get(i).teleopPeriodic();
    	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
  drive.arcadeDrive(driveStick.sgetY(),driveStick.sgetX());
//    	frontrightmotor.set(0.5);
    }
    
}