package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import java.util.ArrayList;
import java.util.LinkedList;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the
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
    /*
     * button 5 and 6 for up and down button 3 and 4 for in and out
     */
    int tempint = 0;
    MecDriveCmd mdrive = new MecDriveCmd(0, 1, 2, 3);
    JsScaled rightStick = new JsScaled(0);
    JsScaled leftStick = new JsScaled(1);
//    PneumaticsCmd InOut = new PneumaticsCmd(0, 1);
    ArrayList<cmd> cmdlist = new ArrayList<cmd>();
    
    Timer t1 = new Timer();
    RangeFinderCmd RangeFinder1 = new RangeFinderCmd(0);
    RangeFinderCmd RangeFinder2 = new RangeFinderCmd(1);
    RangeFinderCmd RangeFinder3 = new RangeFinderCmd(2);
    RangeFinderCmd RangeFinder4 = new RangeFinderCmd(3);
    DigitalInput ElevFwd = new DigitalInput(0); // reverse limit switch
    DigitalInput ElevRev = new DigitalInput(1); // fwd limit switch
    TalonSRXcmd Elev = new TalonSRXcmd(4); // Elevator
    BuiltInAccelerometer acc = new BuiltInAccelerometer();
    int session;
    Image frame;
    AxisCamera camera;
    int autostate = 0;
    int autodistance = 175; // distance we wand to be from the wall
    double autotime = 5;
   
    public void robotInit() {
	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

	// open the camera at the IP address assigned. This is the IP address
	// that the camera
	// can be accessed through the web interface.
	camera = new AxisCamera("10.19.89.11");
	mdrive.Setjoystick(rightStick);
	Elev.Setjoystick(rightStick);
	Elev.Setswitches(ElevFwd, ElevRev);
//	InOut.Setjoystick(rightStick, 3, 4);
	cmdlist.add(mdrive);
//	cmdlist.add(InOut);
	cmdlist.add(Elev);
	cmdlist.add(RangeFinder1);
	cmdlist.add(RangeFinder2);
	cmdlist.add(RangeFinder3);
	cmdlist.add(RangeFinder4);

	// add reference to arraylist to all elements
    }

    public void autonomousInit() {
	for (int i = 0; i < cmdlist.size(); i++) {
	    cmdlist.get(i).autonomousInit();
	}
	autostate = 0;
	t1.reset();
	// put logic here for multiple outo modes if applicable
    }

    /**
     * This function is called	 periodically during autonomous
     */
    public void autonomousPeriodic() {
	if (autostate == 0) // pick up box
	{
	    rightStick.pY = 0;
	    rightStick.pX = 0;
	    rightStick.pTwist = 0;
	    rightStick.buttons[4]= true;
	    
	    if (!Elev.SwFwd)
	    {
		autostate = 1;
	    }
	}
	else if (autostate == 1)
	{
	   t1.reset();
	   t1.start();
	    rightStick.pY = .35;
	    rightStick.pX = 0;
	    rightStick.pTwist = 0;
	    rightStick.buttons[4]= false;
	   autostate = 2; 
	}
	else if (autostate == 2)
	{
	    rightStick.pY = .35;
	    rightStick.pX = 0;
	    rightStick.pTwist = 0;
	    rightStick.buttons[4]= false;
	    
	    if (RangeFinder1.distance > autodistance || t1.get() > autotime)
	    {
		autostate = 3;
	    }
	}
	else if (autostate == 3){
	    rightStick.pY = 0;
	    rightStick.pX = 0;
	    rightStick.pTwist = 0;
	    rightStick.buttons[5]= true;
	    if (!Elev.SwRev)
	    {
		autostate = 4;
	    }
	}
	else if (autostate >= 4)  // we are done
	{
	    rightStick.pY = 0;
	    rightStick.pX = 0;
	    rightStick.pTwist = 0;
	    rightStick.buttons[5]= false;
		autostate = 5;
	}
	    
	for (int i = 0; i < cmdlist.size(); i++) {
	    cmdlist.get(i).autonomousPeriodic();
	}
    }

    public void teleopInit() {
	tempint = 0;
	t1.start();
	for (int i = 0; i < cmdlist.size(); i++) {
	    cmdlist.get(i).teleopInit();
	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {

	/**
	 * grab an image from the camera, draw the circle, and provide it for
	 * the camera server which will in turn send it to the dashboard.
	 */
//	NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
	camera.getImage(frame);
//	NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.DRAW_VALUE,
//		ShapeMode.SHAPE_OVAL, 0.0f);
	if (t1.get() > .25) {
	    t1.reset();
	    t1.start();
	    double distance = Math
		    .floor(RangeFinder1.getAverageValue() * 1024 / 5);
	    SmartDashboard.putString("DB/String 5", "Rangefinder4: "
		    + new Integer((int) distance).toString());
	    double y = acc.getY() *1000;
	    SmartDashboard.putString("DB/String 6", "Acc X " 
		    + new Integer((int) acc.getX() *1000).toString()
	    + " Y " + new Integer((int) acc.getY() *1000).toString()
	    + " Z " +  new Integer((int) acc.getZ() *1000).toString());
	}

	CameraServer.getInstance().setImage(frame);
	for (int i = 0; i < cmdlist.size(); i++) {
	    cmdlist.get(i).teleopPeriodic();
	}
    }

    public void testInit() {
	for (int i = 0; i < cmdlist.size(); i++) {
	    cmdlist.get(i).testInit();
	}
    }

    /**
     * This function is called periodically during autonomous
     */
    public void testPeriodic() {
	for (int i = 0; i < cmdlist.size(); i++) {
	    cmdlist.get(i).testPeriodic();
	}
    }

    public void disabledInit() {
	for (int i = 0; i < cmdlist.size(); i++) {
	    cmdlist.get(i).disabledInit();
	}

    }
}
