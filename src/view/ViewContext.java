package view;


import java.util.ArrayList;
import java.util.HashMap;

import controller.HousePlayer;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class ViewContext {
	private Player currentPlayer;
	private HousePlayer housePlayer;
	private HashMap<Object, ArrayList<PlayingCard>> cardsMap;
	
	public ViewContext() {
		cardsMap = new HashMap<Object, ArrayList<PlayingCard>>();
		housePlayer = new HousePlayer();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public HashMap<Object, ArrayList<PlayingCard>> getCardsMap() {
		return cardsMap;
	}

	public HousePlayer getHousePlayer() {
		return housePlayer;
	}
}
