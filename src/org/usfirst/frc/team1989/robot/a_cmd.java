package org.usfirst.frc.team1989.robot;

import java.util.ArrayList;

public abstract class a_cmd implements cmd {
	public String type = ""; // holds class type
	public ArrayList<autocmd> auto_list = new ArrayList<autocmd>(); // list of auto commands

	public void addauto(String Command)
	{
		this.auto_list.add(new autocmd(Command));
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
