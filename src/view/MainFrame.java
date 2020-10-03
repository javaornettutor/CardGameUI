package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public MainFrame() {
		super("Casino Style Card Game");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(500, 500));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void start() {
		this.pack();
		this.setVisible(true);
	}
}
