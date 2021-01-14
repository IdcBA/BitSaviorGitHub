package com.bitsavior.game;

import com.badlogic.gdx.graphics.Texture;


public class PickUp extends Entity
{
    // static Members

    /**
     * counts the amount of existing pickups
     */
    static int pickUpCounter;

    static {
        pickUpCounter = 0;
    }


    // public Methods
    public PickUp(Texture texture)
    {
        super(texture, 0.f);

        isAlive = false;

        // set size of the pickup
        sprite.setSize(10, 10);

        pickUpCounter++;
    }

    /**
     * set the enemy alive and spawn it at the given position
     * @param x : x-coordinate(spawn)
     * @param y : y-coordinate(spawn)
     */
    public void spawn(float x, float y)
    {
        isAlive = true;
        sprite.setPosition(x, y);
    }
}
