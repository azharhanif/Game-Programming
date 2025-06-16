// WARNING: This file is auto-generated and any changes to it will be overwritten
import lang.stride.*;
import greenfoot.*;

/**
 * Write a description of class CrabWorld here.
 * @author (your name) @version (a version number or a date)
 */
public class CrabWorld extends World
{

    /* (World, Actor, GreenfootImage, Greenfoot and MouseInfo)*/

    /**
     * Constructor for objects of class CrabWorld.
     */
    public CrabWorld()
    {
        super(560, 560, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Crab crab =  new Crab();
        addObject(crab, 180, 334);
        Worm worm2 =  new Worm();
        addObject(worm2, 249, 146);
        Worm worm4 =  new Worm();
        addObject(worm4, 532, 360);
        Worm worm6 =  new Worm();
        addObject(worm6, 405, 252);
        Worm worm7 =  new Worm();
        addObject(worm7, 338, 491);
        Lobster lobster =  new Lobster();
        addObject(lobster, 417, 126);
        crab.setLocation(29, 271);
        worm2.setLocation(315, 272);
        worm4.setLocation(475, 274);
        worm7.setLocation(145, 238);
        lobster.setLocation(194, 178);
        lobster.setLocation(187, 164);
        worm4.setLocation(483, 262);
        lobster.setLocation(178, 369);
        crab.setLocation(76, 275);
    }
}
