package view;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.Player;

public class SummaryPanel extends JPanel {

	private HashMap<String, JLabel> componentMap;
	private HashMap<String, Integer> previousBalanceMap;
	private HashMap<String, Integer> previousWinLoss;

	public SummaryPanel() {
		super();
		componentMap = new HashMap<>();
		previousBalanceMap = new HashMap<>();
		previousWinLoss = new HashMap<>();
		initialize();
	}

	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.YELLOW);
	}

	public void playerAdded(Player player) {
		previousBalanceMap.put(player.getPlayerId(), player.getPoints());
		componentMap.put(player.getPlayerId(), new JLabel(playerInfo(player)));
		add(componentMap.get(player.getPlayerId()));
	}

	public void playerRemoved(Player player) {
		remove(componentMap.remove(player.getPlayerId()));
		previousBalanceMap.remove(player.getPlayerId());
		repaint();
	}

	public void refreshInfo(Player player, boolean updateWinLoss) {
		if (updateWinLoss) {
			previousWinLoss.put(player.getPlayerId(), player.getPoints() - previousBalanceMap.get(player.getPlayerId())); 
		}
		componentMap.get(player.getPlayerId()).setText(playerInfo(player));
		previousBalanceMap.put(player.getPlayerId(), player.getPoints());
	}

	private String playerInfo(Player player) {
		String template = "Player: %s - Points: %d - Bet: %d - Last play: %s %d";
		
		if (previousWinLoss.containsKey(player.getPlayerId())) {
			int previousResult = previousWinLoss.get(player.getPlayerId());
			return String.format(template, player.getPlayerName(), player.getPoints(), player.getBet(),
					previousResult > 0 ? "WIN" : "LOST", Math.abs(previousResult));
		} else {
			template = "Player: %s - Points: %d - Bet: %d - Last play: N/A";
			return String.format(template, player.getPlayerName(), player.getPoints(), player.getBet());
		}
	}
}
