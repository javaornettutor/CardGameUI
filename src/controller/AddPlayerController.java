package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AddPlayerPanel;
import view.GameEngineCallbackGUI;

public class AddPlayerController implements ActionListener {

	private GameEngineCallbackGUI gameEngineCallbackGUI;
	private GameEngine gameEngine;
	
	public AddPlayerController(GameEngine gameEngine, GameEngineCallbackGUI gameEngineCallbackGUI) {
		this.gameEngine = gameEngine;
		this.gameEngineCallbackGUI = gameEngineCallbackGUI;
	}

	public void actionPerformed(ActionEvent event) {
		AddPlayerPanel playerPanel = gameEngineCallbackGUI.promptPlayerData();
		if (playerPanel == null) return;
		String inputName = playerPanel.getInputName();
		String inputPoint = playerPanel.getInputPoint();
		
		if(validatePlayerData(inputName, inputPoint)) {
			int point = Integer.parseInt(inputPoint);
			Player player = new SimplePlayer(inputName.replaceAll("\\s", "_").toUpperCase(), inputName, point);
			gameEngine.addPlayer(player);
			gameEngineCallbackGUI.playerAdded(player);
		} else {
			gameEngineCallbackGUI.showError("Invalid player data");
		}
	}
	
	private boolean validatePlayerData(String inputName, String inputPoint) {
		if(inputName.isEmpty()) return false;
		try { 
	        int point = Integer.parseInt(inputPoint);
	        if (point <= 0) return false;
	    } catch(NumberFormatException e) { 
	        return false;
	    }
		return true;
	}
}
