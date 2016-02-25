package org.usfirst.frc.team1989.robot;

import java.util.ArrayList;

public abstract class a_cmd implements cmd {
	public String type = ""; // holds class type
	public ArrayList<a_cmd> cmdlist = new ArrayList<a_cmd>();
	public String[] msg = new String[10] ;
	public Boolean[] led = new Boolean[5];
	   /*
	    * returns an a_cmd with type stringcmd
	    */
	public a_cmd findcmd(String cmd)
	{
		
		for (int i = 0; i < cmdlist.size(); i++) {
			 if (cmdlist.get(i).type == cmd){
				 return cmdlist.get(i);
			 };
		}

		return null;
	}

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

}
