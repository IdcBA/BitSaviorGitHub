package com.bitsavior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.bitsavior.game.GameState;
import com.bitsavior.game.World;

public class GameScreen extends ScreenAdapter {

	// private Members
	private BitSavior game;
	private World world;

	private int gameLevel = 0;

	
	/**
     * 0: no messages; 1: send messages
     */
    int aTestMode=1;

	// public methods
	/**
	 * Constructor:
	 * @param game game
	 * @param level current level
	 */
	public GameScreen(BitSavior game, int level)
	{
		this.game = game;
		//if(tScreen==null) System.out.println("tScreen beim erstellen von GameScreen leer");
		//this.tScreen = tScreen;
		world = new World(GameState.INITIALIZE);    // creating the World
		world.create();
		gameLevel = level;
		if(aTestMode==1) System.out.println("creates new world");
	}


	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown(int keyCode) {
				if (keyCode == Input.Keys.ESCAPE) {

					game.manager.showScreen(Screens.TITLE);

				}
				return true;
			}
		});
	}


	@Override
	public void render(float Delta)
	{
		switch(world.update(Delta))
		{
			case LOOSE_SHUTDOWN:
				game.manager.showScreen(Screens.LOSE);
				break;
			default:
		}
		world.render(Delta);
	}

	public int getGameLevel() { return gameLevel; }


	@Override
	public void dispose()
	{
		world.dispose();
	}
	
	@Override
	public void hide()
	{
		Gdx.input.setInputProcessor(null);
	}
	
	// private methods
	private void processEvents() {}
}
