package view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

import model.interfaces.Player;

public class HeaderPanel extends JPanel {

	private JComboBox<Player> playerCombo;
	
	public HeaderPanel() {
		super();
		initializePlayerCombo();
		this.setVisible(true);
	}
	
	private void initializePlayerCombo() {
		playerCombo = new JComboBox<Player>();
		playerCombo.setPreferredSize(new Dimension(200, 20));
		playerCombo.setRenderer(new DefaultListCellRenderer() {
			
		    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

		        if (value instanceof Player) {
		            value = ((Player)value).getPlayerName();
		        }

		        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		    }
		});
		this.add(playerCombo);
	}
	
	public void playerAdded(Player player) {
		playerCombo.addItem(player);
	}
}
