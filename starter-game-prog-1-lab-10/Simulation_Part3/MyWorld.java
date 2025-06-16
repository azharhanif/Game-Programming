import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private long lastFrameTimeMS;
    private double timeStepDuration;

    public MyWorld()
    {    
        super(1024, 768, 1, false); 

        lastFrameTimeMS = System.currentTimeMillis();
        timeStepDuration = 1.0 / 60; // seems to be the default
        prepare();
    }

    public void started()
    {
        lastFrameTimeMS = System.currentTimeMillis();
    }

    public void act()
    {
        timeStepDuration = (System.currentTimeMillis() - lastFrameTimeMS) / 1000.0;
        lastFrameTimeMS = System.currentTimeMillis();
    }

    public double getTimeStepDuration()
    {
        return timeStepDuration;
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Cannon cannon = new Cannon();
        addObject(cannon,128,419);
        Target target = new Target();
        addObject(target,517,116);
        Target target2 = new Target();
        addObject(target2,602,190);
        target2.setLocation(626,216);
        Target target3 = new Target();
        addObject(target3,626,216);
        addObject(target3,660,310);
        target3.setLocation(660,291);
        Target target4 = new Target();
        addObject(target4,660,291);
        removeObject(target4);
        Target target5 = new Target();
        addObject(target5,735,552);
        target3.setLocation(693,367);
    }
}
