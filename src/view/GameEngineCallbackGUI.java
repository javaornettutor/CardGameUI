package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ControllerBuilder;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {

	private ControllerBuilder controllerBuilder;
	
	private JFrame mainFrame;
	private JPanel contentPane;
	private HeaderPanel headerPanel;
	
	public GameEngineCallbackGUI(ControllerBuilder controllerBuilder) {
		this.controllerBuilder = controllerBuilder;
	}
	
	
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

	
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

	
	public void result(Player player, int result, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

	
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

	
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

	
	public void houseResult(int result, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}
	
	public void show() {
		initializeView();
	}
	
	private void initializeView() {
		mainFrame = new JFrame("Game engine");
		mainFrame.setLayout(new BorderLayout());
		initializeContentPane();
		initializeMenu();
		initializeHeaderPanel();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setContentPane(contentPane);
		mainFrame.setMinimumSize(new Dimension(500, 500));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	private void initializeMenu() {
		mainFrame.setJMenuBar(new GameMenuBar(controllerBuilder.createAddPlayerController(this)));
	}
	
	private void initializeHeaderPanel() {
		headerPanel = new HeaderPanel();
		contentPane.add(headerPanel, BorderLayout.NORTH);
	}
	
	private void initializeContentPane() {
		contentPane = new JPanel(new BorderLayout());
	}
	
	public AddPlayerPanel promptPlayerData() {
		AddPlayerPanel panel = new AddPlayerPanel();
		int option = JOptionPane.showConfirmDialog(mainFrame, panel, "Please enter player info", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			return panel;
		} else {
			return null;
		}
	}
	
	public void showError(String message) {
		JOptionPane.showMessageDialog(mainFrame, message);
	}
	
	public void playerAdded(Player player) {
		headerPanel.playerAdded(player);
	}
}
