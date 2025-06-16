import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*; 
import java.awt.image.*; 

/**
 * Write a description of class SimulationActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SimulationActor extends Actor
{
    protected Point2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;

    protected GreenfootImage originalImage;
    
    public SimulationActor()
    {
        this.position = null;
        this.velocity = new Vector2D(0.0,0.0);
        this.acceleration = new Vector2D(0.0,0.0);
        originalImage = null;
    }
    
    public SimulationActor(Point2D position, Vector2D velocity, Vector2D acceleration)
    {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        originalImage = null;
    }
    
    public void act() 
    {
        eulerIntegration();
    }    
    
    protected void eulerIntegration()
    {
        // Initial position
        if (position == null)
        {
            position = windowToWorld(new Point2D(getX(), getY()));
        }
        
        if (originalImage == null && getImage() != null)
        {
            saveOriginalImage();
        }

        
        // Get time step duration
        SimulationWorld world = (SimulationWorld) getWorld();
        double dt = world.getTimeStepDuration();
        
        // Update velocity
        Vector2D velocityVariation = Vector2D.multiply(acceleration, dt);
        velocity = Vector2D.add(velocity, velocityVariation);

        // Update position
        Vector2D positionVariation = Vector2D.multiply(velocity, dt);
        position.add(positionVariation);
        
        // Set new actor position
        setLocation(position);
    }

    public void saveOriginalImage()
    {
        BufferedImage img = getImage().getAwtImage();
        originalImage = new GreenfootImage(img.getWidth(), img.getHeight());

        Graphics g = originalImage.getAwtImage().getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();        
    }
    
    public void scaleImage(double zoomRatio)
    {
        if (originalImage != null)
        {
            int imageWidth = originalImage.getWidth();
            int imageHeight = originalImage.getHeight();

            GreenfootImage scaledImage = new GreenfootImage(imageWidth, imageHeight);
            BufferedImage gBufImg = scaledImage.getAwtImage();
            Graphics2D graphics = (Graphics2D)gBufImg.getGraphics();
            graphics.drawImage(originalImage.getAwtImage(), null, 0, 0);
            graphics.dispose();
            scaledImage.scale((int)Math.max(imageWidth*zoomRatio, 1.0), (int)Math.max(imageHeight*zoomRatio, 1.0));
            setImage(scaledImage);
        }
    }
    
    public void setVelocity(Vector2D newValue)
    {
        velocity = newValue;
    }
    
    public void setLocation(Point2D worldLocation)
    {
        SimulationWorld world = (SimulationWorld) getWorld();
        Point2D windowLocation = world.worldToWindow(worldLocation);
        setLocation((int) windowLocation.getX(), (int) windowLocation.getY());
    }
    
    protected Point2D worldToWindow(Point2D windowCoordinates)
    {
        SimulationWorld world = (SimulationWorld) getWorld();
        return world.worldToWindow(windowCoordinates);
    }

    protected Vector2D worldToWindow(Vector2D windowCoordinates)
    {
        SimulationWorld world = (SimulationWorld) getWorld();
        return world.worldToWindow(windowCoordinates);
    }
    
    protected Point2D windowToWorld(Point2D windowCoordinates)
    {
        SimulationWorld world = (SimulationWorld) getWorld();
        return world.windowToWorld(windowCoordinates);
    }

    protected Vector2D windowToWorld(Vector2D windowCoordinates)
    {
        SimulationWorld world = (SimulationWorld) getWorld();
        return world.windowToWorld(windowCoordinates);
    }
}
