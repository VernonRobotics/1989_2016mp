package org.usfirst.frc.team1989.robot;



import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;


public class ArmsCmd implements cmd{
	
	
	JsScaled driveStick;
	CANTalon armMotor1 = new CANTalon(1);
	CANTalon armMotor2 = new CANTalon(4);
	
	public ArmsCmd(JsScaled driveStick){
			
			this.driveStick = driveStick;
			
	}
	
	public void armMotorOperation(){
		
		// Going Up - both negative
		if (driveStick.getRawButton(6) == true){
			armMotor1.set(-0.7);
			armMotor2.set(-0.7);
		}
		
		// Going Down - both positive
		else if (driveStick.getRawButton(4) == true){
			armMotor1.set(0.7);
			armMotor2.set(0.7);
		}
		else{
			armMotor1.set(-0.05);
			armMotor2.set(-0.05);
		}
	}



	public void disabledInit() {}
	public void autonomousInit() {}
	public void autonomousPeriodic() {
		if (driveStick.buttons[6] == true){
			armMotor1.set(-0.85);
			armMotor2.set(-0.85);
		}
		
		else if (driveStick.buttons[4] == true){
			armMotor1.set(0.85);
			armMotor2.set(0.85);
		}
		else{
			armMotor1.set(-0.05);
			armMotor2.set(-0.05);
		}
		
	}
	 public void disabledPeriodic(){
	    	armMotor1.set(0);
	    	armMotor2.set(0);
	    	
	    }
	public void testInit() {}
	public void teleopInit() {}
	public void teleopPeriodic() {
		armMotorOperation();
	}
	public void testPeriodic() {}
	
	
}
