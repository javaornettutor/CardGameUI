package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.Player;

public class SummaryPanel extends JPanel {
	
	private HashMap<String, JLabel> componentMaps;
	
	public SummaryPanel() {	
		super();
		componentMaps = new HashMap<>();
		initialize();
	}
	
	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.YELLOW);
	}
	
	public void playerAdded(Player player) {
		componentMaps.put(player.getPlayerId(), new JLabel(player.toString()));
		add(componentMaps.get(player.getPlayerId()));
	}

	public void playerRemoved(Player player) {
		remove(componentMaps.remove(player.getPlayerId()));
	}
	
	public void refreshInfo(Player player) {
		componentMaps.get(player.getPlayerId()).setText(player.toString());
	}
}
