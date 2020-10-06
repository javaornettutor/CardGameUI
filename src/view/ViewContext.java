package view;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import controller.HousePlayer;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class ViewContext {
	private Player currentPlayer;
	private HousePlayer housePlayer;
	private HashMap<Object, ArrayList<PlayingCard>> cardsMap;
	private HashSet<Player> dealtPlayers;
	
	public ViewContext() {
		cardsMap = new HashMap<Object, ArrayList<PlayingCard>>();
		dealtPlayers = new HashSet<Player>();
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
	
	public void startNewRound() {
		dealtPlayers.clear();
	}
	
	public void finishDealPlayer(Player player) {
		dealtPlayers.add(player);
	}
	
	public boolean allPlayerDealt(Collection<Player> allPlayers) {
		for (Player player: allPlayers) {
			if (!dealtPlayers.contains(player)) return false;
		}
		return true;
	}
	
	public List<PlayingCard> getCards(Object owner) {
		if (cardsMap.containsKey(owner)) {
			return (List<PlayingCard>) cardsMap.get(owner).clone();
		}
		return new ArrayList<>();
	}
}
