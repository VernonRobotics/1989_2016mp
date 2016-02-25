package org.usfirst.frc.team1989.robot;

import java.util.ArrayList;

public interface cmd {
    public void disabledInit();
    
    
   public void autonomousInit();
  	
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() ;
    public void DisabledPeriodic();
  
    public void testInit();
    public void teleopInit();
    public void teleopPeriodic() ;
    public void testPeriodic() ;
    
}
