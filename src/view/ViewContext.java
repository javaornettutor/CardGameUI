package view;


import model.interfaces.Player;

public class ViewContext {
	private Player currentPlayer;

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}
