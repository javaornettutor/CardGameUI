/**
 * 
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.AddPlayerController;

public class MenuBar extends JMenuBar {

	private JMenuItem addPlayer;
	private AddPlayerController addPlayerController;

	public MenuBar(AddPlayerController addPlayerController) {
		super();
		this.addPlayerController = addPlayerController;
		initializeComponent();
	}

	private void initializeComponent() {
		addPlayer = new JMenuItem("Add Player");
		addPlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddPlayerPanel panel = new AddPlayerPanel();
				int option = JOptionPane.showConfirmDialog(addPlayer, panel, "Please enter player info",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					addPlayerController.actionPerformed(new ActionEvent(panel.getAddPlayerRequest(), 0, "Add player"));
				}
			}

		});
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		JMenu menu = new JMenu("Settings");
		menu.add(addPlayer);
		menu.add(exit);
		this.add(menu);
	}
	
	public void dealStarted() {
		addPlayer.setEnabled(false);
	}
	
	public void dealFinished() {
		addPlayer.setEnabled(true);
	}
}
