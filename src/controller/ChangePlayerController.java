package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.interfaces.Player;
import view.GameEngineCallbackGUI;

public class ChangePlayerController implements ActionListener {

	private GameEngineCallbackGUI gameEngineCallbackGUI;
	
	public ChangePlayerController(GameEngineCallbackGUI gameEngineCallbackGUI) {
		this.gameEngineCallbackGUI = gameEngineCallbackGUI;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JComboBox playerCombo = (JComboBox)event.getSource();
		Object selected = playerCombo.getSelectedItem();
		if (selected instanceof Player) {
			gameEngineCallbackGUI.playerChanged((Player)selected);
		} else {
			
		}
	}
}
