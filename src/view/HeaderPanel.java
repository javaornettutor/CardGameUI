package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.BetController;
import controller.ChangePlayerController;
import controller.DealPlayerController;
import controller.RemovePlayerController;
import model.interfaces.Player;

public class HeaderPanel extends JPanel {

	private JComboBox<Object> playerCombo;
	private JButton removeBtn;
	private JButton dealBtn;
	private JButton betBtn;
	private JToolBar toolbar;
	private RemovePlayerController removePlayerController;
	private ChangePlayerController changePlayerController;
	private DealPlayerController dealPlayerController;
	private BetController betController;
	private ViewContext viewContext;
	private boolean dealing;

	public HeaderPanel(RemovePlayerController removePlayerController, ChangePlayerController changePlayerController,
			DealPlayerController dealPlayerController, BetController betController, ViewContext viewContext) {
		super();
		this.removePlayerController = removePlayerController;
		this.changePlayerController = changePlayerController;
		this.dealPlayerController = dealPlayerController;
		this.betController = betController;
		this.viewContext = viewContext;
		initializeComponents();
		this.setVisible(true);
	}

	private void initializeComponents() {
		initializePlayerCombo();
		initializeToolbar();
	}

	private void initializeToolbar() {
		toolbar = new JToolBar();
		this.add(toolbar);
		initializeBetButton();
		initializeDealButton();
		initializeRemoveButton();
	}

	private void initializePlayerCombo() {
		playerCombo = new JComboBox<Object>();
		playerCombo.setPreferredSize(new Dimension(200, 20));
		playerCombo.addItem(viewContext.getHousePlayer());
		playerCombo.setRenderer(new DefaultListCellRenderer() {

			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				if (value instanceof Player) {
					value = ((Player) value).getPlayerName();
				}

				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				return this;
			}
		});
		playerCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				updateActionsOnChanged();
			}
		});
		playerCombo.addActionListener(changePlayerController);
		this.add(playerCombo);
	}

	private void initializeRemoveButton() {
		removeBtn = new JButton("Remove");
		removeBtn.addActionListener(removePlayerController);
		removeBtn.setEnabled(false);
		toolbar.add(removeBtn);
	}

	private void initializeDealButton() {
		dealBtn = new JButton("Deal");
		dealBtn.addActionListener(dealPlayerController);
		dealBtn.setEnabled(false);
		toolbar.add(dealBtn);
	}

	private void initializeBetButton() {
		betBtn = new JButton("Bet");
		betBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String bet = JOptionPane.showInputDialog("Please enter bet");
				betController.actionPerformed(new ActionEvent(bet, 1, "Bet"));
			}
		});
		betBtn.setEnabled(false);
		toolbar.add(betBtn);
	}

	public void updateActionsOnChanged() {
		Player player = (playerCombo.getSelectedItem() instanceof Player) ? (Player) playerCombo.getSelectedItem()
				: null;
		removeBtn.setEnabled(player != null || dealing);
		dealBtn.setEnabled(dealing || (player != null && player.getBet() > 0));
		betBtn.setEnabled(dealing || (player != null && player.getBet() == 0));
	}

	public void playerAdded(Player player) {
		playerCombo.addItem(player);
	}

	public void playerRemoved(Player player) {
		playerCombo.removeItem(player);
	}

	public void dealStarted() {
		dealing = true;
		updateActionsOnChanged();
	}

	public void dealFinished() {
		dealing = false;
		updateActionsOnChanged();
	}
}
