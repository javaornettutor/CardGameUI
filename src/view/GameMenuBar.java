/**
 * 
 */
package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerController;

public class GameMenuBar extends JMenuBar {
	
	private AddPlayerController addPlayerController;
	
	public GameMenuBar(AddPlayerController addPlayerController) {
		super();
		this.addPlayerController = addPlayerController;
		initializeComponent();
	}
	
	private void initializeComponent() {
        JMenuItem addPlayer = new JMenuItem("Add Player");
        addPlayer.addActionListener(addPlayerController);
        JMenu menu = new JMenu("Settings"); 
		menu.add(addPlayer);
		this.add(menu);
	}
}
