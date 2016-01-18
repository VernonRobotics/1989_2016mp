package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class DoubleSolenoid {
	Solenoid s1;
	Solenoid s2;
	
	public DoubleSolenoid(int port1, int port2)
	{
		s1 = new Solenoid(port1);
		s2 = new Solenoid(port2);
	}
	/*
	 * Sets direction 0 = stop
	 * 				  1 = fwd
	 * 				  2 = rev
	 */
	public void set(int direction)
	{
		s1.set(direction == 1);
		s2.set(direction == 2);
	}
}
