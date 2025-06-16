import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.awt.Graphics2D; 
import java.awt.image.*; 
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class SimulationWorld extends World
{
    protected final double DEFAULT_CAMERA_WIDTH; // 1 meter per second
    
    protected Point2D cameraCenter;
    protected double cameraWidth;
    private Matrix2D toWindowMatrix;
    private Matrix2D toWorldMatrix;

    private long lastFrameTimeMS;
    private double timeStepDuration;

    public SimulationWorld(int windowWidth, int windowHeight, Point2D cameraCenter, double cameraWidth)
    {    
        super(windowWidth, windowHeight, 1, false); 
        
        // Save the initial width to compute the zooming ratio
        DEFAULT_CAMERA_WIDTH = cameraWidth;
        
        this.cameraCenter = cameraCenter;
        this.cameraWidth = cameraWidth;
        this.toWindowMatrix = Matrix2D.worldToWindow(cameraCenter, cameraWidth, new Vector2D(getWidth(), getHeight()));
        this.toWorldMatrix = Matrix2D.windowToWorld(cameraCenter, cameraWidth, new Vector2D(getWidth(), getHeight()));
    }
    
    public void started()
    {
        lastFrameTimeMS = System.currentTimeMillis();
    }

    public void act()
    {
        // Update time step duration
        timeStepDuration = (System.currentTimeMillis() - lastFrameTimeMS) / 1000.0;
        lastFrameTimeMS = System.currentTimeMillis();

        // Update Viewport Matrix
        toWindowMatrix = Matrix2D.worldToWindow(cameraCenter, cameraWidth, new Vector2D(getWidth(), getHeight()));
        toWorldMatrix = Matrix2D.windowToWorld(cameraCenter, cameraWidth, new Vector2D(getWidth(), getHeight()));

    }
    
    public double getTimeStepDuration()
    {
        return timeStepDuration;
    }

    public Point2D worldToWindow(Point2D worldCoord)
    {
        return toWindowMatrix.transform(worldCoord);
    }

    public Vector2D worldToWindow(Vector2D worldCoord)
    {
        return toWindowMatrix.transform(worldCoord);
    }
    
    public Point2D windowToWorld(Point2D windowCoord)
    {        
        return toWorldMatrix.transform(windowCoord);
    }

    public Vector2D windowToWorld(Vector2D windowCoord)
    {        
        return toWorldMatrix.transform(windowCoord);
    }
    
    public void scaleActors()
    {
        List<SimulationActor> actors = getObjects(SimulationActor.class);
        
        for (int i=0; i<actors.size(); i++)
        {
            SimulationActor actor = actors.get(i);
            actor.scaleImage(getZoomRatio());
        }
    }
    
    public double getZoomRatio()
    {
        return DEFAULT_CAMERA_WIDTH / cameraWidth;
    }
    

}
