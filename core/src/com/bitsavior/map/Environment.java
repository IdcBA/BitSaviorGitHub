package com.bitsavior.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.bitsavior.entity.LightedEntity;
import com.bitsavior.game.Watch;

import java.util.ArrayList;

/**
 * handles the lights in the scene including
 * positioning of the lighted objects
 */
public class Environment
{
    /**
     * list of all lighted objects
     */
    private ArrayList<LightedEntity> lights;
    /**
     * number of lights in the scene
     */
    private final int NumberOfLights = 4;
    /**
     * list of the positions of the lights
     */
    private Vector2[] lightPositions;
    /**
     * standard effect, if no other is set
     */
    private final LightedEntity.EffectType standardEffect =  LightedEntity.EffectType.FLICKER;
    /**
     * timer to set a effect for a limited time
     */
    private Watch timer;
    
    /**
     * constructor
     * creates the lighted entities and fills the positions into the array
     * @param manager : assetmanager that holds all the assets
     */
    public Environment(final AssetManager manager)
    {
        lights = new ArrayList<LightedEntity>();

        for(int i = 0; i < NumberOfLights; i++)
            lights.add(new LightedEntity(manager));

        lightPositions = new Vector2[NumberOfLights];

        lightPositions[0] = new Vector2(390, 191);
        lightPositions[1] = new Vector2(638, 767);
        lightPositions[2] = new Vector2(1252, 26);
        lightPositions[3] = new Vector2(152, 615);
    }

    /**
     * sets the positions for the lighted entities
     */
    public void create()
    {
        for(int i = 0; i < NumberOfLights; i++) {
            lights.get(i).setPosition(lightPositions[i].x, lightPositions[i].y);
            lights.get(i).setEffect(LightedEntity.EffectType.FLICKER, 500L);

        }
    }

    /**
     * updates all lighted entities
     */
    public void update()
    { 
    	if(timer != null) {
    		timer.update();
    		if(!timer.isActive()) 
    			changeEffect(standardEffect);
    	}
    	
        for(LightedEntity lightbulb : lights)
            lightbulb.update();

    }

    /**
     * draws all lighted entities
     * @param batch : current Spritebatch
     * @param Delta : elapesd time since last frame in milliseconds
     */
    public void draw(SpriteBatch batch, float Delta)
    {
        for(LightedEntity lightbulb : lights)
            lightbulb.draw(batch, Delta);
    }

    /**
     * draws all the lightsources of the lighted entities
     * @param batch : current Spritebatch
     * @param Delta : elapesd time since last frame in milliseconds
     */
    public void drawLight(SpriteBatch batch, float Delta)
    {
        for(LightedEntity lightbulb : lights) {
            lightbulb.drawLight(batch, Delta);
        }
    }
    
    /**
     * changes the effect for a limited time
     * @param type : new effect
     * @param effectTime : time limit for effect
     */
    public void changeEffect(LightedEntity.EffectType type, int effectTime) {
    	timer = new Watch(effectTime);
    	timer.startWatch();
    	for(int i = 0; i < NumberOfLights; i++) {
            lights.get(i).setEffect(type);
        }
    }

    /**
     * changes the effect
     * @param type : new effect
     */
	public void changeEffect(LightedEntity.EffectType type) {
		for(int i = 0; i < NumberOfLights; i++) {
			lights.get(i).setEffect(type);
		}
	}
}