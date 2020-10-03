package controller;

import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;

public class ControllerBuilder {
	private GameEngine gameEngine;
	
	public ControllerBuilder(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}
	
	public AddPlayerController createAddPlayerController(GameEngineCallbackGUI gameEngineCallbackGUI) {
		return new AddPlayerController(gameEngine, gameEngineCallbackGUI);
	}
}
