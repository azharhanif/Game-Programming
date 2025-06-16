    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    import java.util.*;
    
    /**
     * Write a description of class MyWorld here.
     * 
     * @author (your name) 
     * @version (a version number or a date)
     */
    public class MyWorld extends SimulationWorld
    {
        private final static double CAMERA_SPEED = 5.0; // 1 meter per second
        
        public MyWorld()
        {    
            super(1024, 768, new Point2D(8.0, 6.0), 16.0); 
    
            prepare();
        }
    
        public void act()
        {
            super.act();
            moveCamera();

        }
    
        public void moveCamera()
        {
            double dt = getTimeStepDuration();
            
            if (Greenfoot.isKeyDown("a")){
                cameraCenter.setX(cameraCenter.getX() - CAMERA_SPEED * dt);
            }
            if (Greenfoot.isKeyDown("d")){
                cameraCenter.setX(cameraCenter.getX() + CAMERA_SPEED * dt);
            }
            if (Greenfoot.isKeyDown("s")){
                cameraCenter.setY(cameraCenter.getY() - CAMERA_SPEED * dt);
            }        
            if (Greenfoot.isKeyDown("w")){
                cameraCenter.setY(cameraCenter.getY() + CAMERA_SPEED * dt);
            }
            if (Greenfoot.isKeyDown("-")){
                cameraWidth += CAMERA_SPEED * dt;
                scaleActors();
            }
            if (Greenfoot.isKeyDown("=") || Greenfoot.isKeyDown("+")){
                cameraWidth -= CAMERA_SPEED * dt;
                scaleActors();
            }    
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
        CannonBall cannonBall = new CannonBall();
        addObject(cannonBall,244,122);
        CannonBall cannonBall2 = new CannonBall();
        addObject(cannonBall2,270,121);
        cannonBall2.setLocation(270,108);
        Cannon cannon2 = new Cannon();
        addObject(cannon2,460,658);
        Cannon cannon3 = new Cannon();
        addObject(cannon3,872,414);
    }
}
