package view;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.AddPlayerController;
import controller.BetController;
import controller.ChangePlayerController;
import controller.DealPlayerController;
import controller.RemovePlayerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {

	private MainFrame mainFrame;
	private JPanel contentPane;
	private HeaderPanel headerPanel;
	private SummaryPanel summaryPanel;
	private ViewContext viewContext;
	private GameEngine gameEngine;
	private MenuBar menuBar;
	private DealResultPanel dealResultPanel;

	public GameEngineCallbackGUI(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		this.viewContext = new ViewContext();
	}

	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dealResultPanel.nextPlayerCardReceived(player, card);
			}
		});
	}

	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		dealFinished();
	}

	public void result(Player player, int result, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	public void houseBustCard(PlayingCard card, GameEngine engine) {
		dealFinished();
	}

	public void houseResult(int result, GameEngine engine) {
		// TODO Auto-generated method stub

	}

	public void playerAdded(Player player) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				headerPanel.playerAdded(player);
				summaryPanel.playerAdded(player);
			}
		});
	}

	public void playerChanged(Player player) {
		viewContext.setCurrentPlayer(player);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dealResultPanel.switchTo(player);
			}
		});
	}

	public void houseSelected() {
		viewContext.setCurrentPlayer(null);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				headerPanel.updateActionsOnChanged();
				dealResultPanel.switchTo(viewContext.getHousePlayer());
			}
		});
	}

	public void playerRemoved(Player player) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				headerPanel.playerRemoved(player);
				summaryPanel.playerRemoved(player);
			}
		});
	}

	public void betReceive(Player player) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				headerPanel.updateActionsOnChanged();
				summaryPanel.refreshInfo(player);
			}
		});
	}

	public void dealStarted(Player player) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				headerPanel.dealStarted();
				menuBar.dealStarted();
				dealResultPanel.dealStarted(player);
			}
		});
	}
	
	public void dealStartedHouse() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dealResultPanel.dealStarted(viewContext.getHousePlayer());
			}
		});
	}

	public void run() {
		initializeView();
	}

	private void initializeView() {
		mainFrame = new MainFrame();
		initializeContentPane();
		initializeMenu();
		initializeHeaderPanel();
		initializeSummaryPanel();
		initializeDealResultPanel();
		mainFrame.start();
	}

	private void initializeContentPane() {
		contentPane = new JPanel(new BorderLayout());
		mainFrame.setContentPane(contentPane);
	}

	private void initializeMenu() {
		menuBar = new MenuBar(new AddPlayerController(gameEngine, this));
		mainFrame.setJMenuBar(menuBar);
	}

	private void initializeHeaderPanel() {
		headerPanel = new HeaderPanel(new RemovePlayerController(gameEngine, this), new ChangePlayerController(this),
				new DealPlayerController(gameEngine, this), new BetController(this), viewContext);
		contentPane.add(headerPanel, BorderLayout.NORTH);
	}

	private void initializeSummaryPanel() {
		summaryPanel = new SummaryPanel();
		contentPane.add(summaryPanel, BorderLayout.WEST);
	}

	private void initializeDealResultPanel() {
		dealResultPanel = new DealResultPanel();
		contentPane.add(dealResultPanel, BorderLayout.CENTER);
	}

	private void dealFinished() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				headerPanel.dealFinished();
				menuBar.dealFinished();
			}
		});
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(mainFrame, message);
	}

	public ViewContext getViewContext() {
		return this.viewContext;
	}
}
