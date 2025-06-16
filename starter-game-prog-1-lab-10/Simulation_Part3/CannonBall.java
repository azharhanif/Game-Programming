import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
* Write a description of class CannonBall here.
* 
* @author (your name) 
* @version (a version number or a date)
*/
public class CannonBall extends Actor
{
    private Point2D position;
    private Vector2D velocity;
    private Vector2D acceleration;
    
    private static final double GRAVITY = 9.8 * 200; // 200 pixels is 1 meter
    
    public CannonBall()
    {
        position = null;
        velocity = new Vector2D(0.0, 0.0);
        acceleration = new Vector2D(0.0, GRAVITY);
    }
    
    public void act() 
    {
        updatePhysics();
    }    
    
    public void setVelocity(Vector2D newValue)
    {
        velocity = newValue;
    }
    
    public void updatePhysics()
    {
        // Initial position
        if (position == null)
        {
            position = new Point2D(getX(), getY());
        }
        
        // Get time step duration
        MyWorld world = (MyWorld) getWorld();
        double dt = world.getTimeStepDuration();
        
        // Update velocity
        Vector2D velocityVariation = Vector2D.multiply(acceleration, dt);
        velocity = Vector2D.add(velocity, velocityVariation);

        // Update position
        Vector2D positionVariation = Vector2D.multiply(velocity, dt);
        position.add(positionVariation);
        
        // Set new actor position
        setLocation((int) position.getX(), (int) position.getY());        
    }
}
