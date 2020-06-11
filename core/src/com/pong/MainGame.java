package com.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends Game {

	private AssetManager manager;
	
	@Override
	public void create () {
		manager = new AssetManager();
		manager.load("bola_Test.png", Texture.class);
		manager.load("stick.png", Texture.class);
		manager.finishLoading();

		setScreen(new MainGameScreen(this));
	}

	/**
	 * Getter of the AssetManager of the whole game.
	 * @return AssetManager which contains the assets used.
	 */
	public AssetManager getManager(){
		return manager;
	}


}
