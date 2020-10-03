package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AddPlayerPanel;
import view.GameEngineCallbackGUI;

public class RemovePlayerController implements ActionListener {

	private GameEngineCallbackGUI gameEngineCallbackGUI;
	private GameEngine gameEngine;
	
	public RemovePlayerController(GameEngine gameEngine, GameEngineCallbackGUI gameEngineCallbackGUI) {
		this.gameEngine = gameEngine;
		this.gameEngineCallbackGUI = gameEngineCallbackGUI;
	}

	public void actionPerformed(ActionEvent event) {
		Player player = gameEngineCallbackGUI.getViewContext().getCurrentPlayer();
		new Thread() {
			@Override
			public void run() {
				if (gameEngine.removePlayer(player)) {
					gameEngineCallbackGUI.playerRemoved(player);
				}
			}
		}.start();
	}
}
