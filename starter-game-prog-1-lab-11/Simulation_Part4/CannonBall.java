import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
* Write a description of class CannonBall here.
* 
* @author (your name) 
* @version (a version number or a date)
*/
public class CannonBall extends SimulationActor
{
    private static final double GRAVITY = -9.8;
    
    public CannonBall()
    {
        super(null, new Vector2D(0.0, 0.0), new Vector2D(0.0, GRAVITY));
    }
    
    public void act() 
    {
        super.act();
    }    
}
