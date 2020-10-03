package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.Player;
import view.GameEngineCallbackGUI;

public class BetController implements ActionListener {

	private GameEngineCallbackGUI gameEngineCallbackGUI;
	
	public BetController(GameEngineCallbackGUI gameEngineCallbackGUI) {
		this.gameEngineCallbackGUI = gameEngineCallbackGUI;
	}

	public void actionPerformed(ActionEvent event) {
		Player player = gameEngineCallbackGUI.getViewContext().getCurrentPlayer();
		String bet = (String) event.getSource();
		new Thread() {
			@Override
			public void run() {
				if(!isPotentialBet(bet) || !player.setBet(Integer.parseInt(bet))) {
					gameEngineCallbackGUI.showMessage("Invalid bet");
				}
				gameEngineCallbackGUI.betReceive(player);
			}
		}.start();
	}
	
	private boolean isPotentialBet(String inputBet) {
		try {
			int bet = Integer.parseInt(inputBet);
			if (bet <= 0)
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
