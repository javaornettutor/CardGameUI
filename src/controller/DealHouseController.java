package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AddPlayerPanel;
import view.GameEngineCallbackGUI;

public class DealHouseController implements ActionListener {

	private GameEngineCallbackGUI gameEngineCallbackGUI;
	private GameEngine gameEngine;

	public DealHouseController(GameEngine gameEngine, GameEngineCallbackGUI gameEngineCallbackGUI) {
		this.gameEngine = gameEngine;
		this.gameEngineCallbackGUI = gameEngineCallbackGUI;
	}

	public void actionPerformed(ActionEvent event) {
		gameEngineCallbackGUI.dealStartedHouse();
		new Thread() {
			@Override
			public void run() {
				gameEngine.dealHouse(100);
			}
		}.start();
	}
}
