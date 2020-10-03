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
		AddPlayerRequest request = (AddPlayerRequest) event.getSource();

		if (validatePlayerData(request)) {
			int point = Integer.parseInt(request.getPoint());
			Player player = new SimplePlayer(request.getName().replaceAll("\\s", "_").toUpperCase(), request.getName(),
					point);
			new Thread() {
				@Override
				public void run() {
					gameEngine.addPlayer(player);
					gameEngineCallbackGUI.playerAdded(player);
				}
			}.start();
		} else {
			gameEngineCallbackGUI.showMessage("Invalid player data");
		}
	}

	private boolean validatePlayerData(AddPlayerRequest request) {
		if (request.getName().isEmpty())
			return false;
		try {
			int point = Integer.parseInt(request.getPoint());
			if (point <= 0)
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
