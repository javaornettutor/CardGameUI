package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class DealResultPanel extends JPanel {

	private HashMap<Object, ArrayList<PlayingCard>> cardsMap;
	private Object currentDisplayOwner;

	public DealResultPanel() {
		super();
		cardsMap = new HashMap<Object, ArrayList<PlayingCard>>();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}
	
	public void nextPlayerCardReceived(Object owner, PlayingCard card) {
		if (!cardsMap.containsKey(owner)) {
			cardsMap.put(owner, new ArrayList<PlayingCard>());
		}
		cardsMap.get(owner).add(card);
		if (owner == currentDisplayOwner) {
			repaint();
		}
	}
	
	public void switchTo(Object owner) {
		if (currentDisplayOwner != owner) {
			currentDisplayOwner = owner;
			repaint();
		}
	}
	
	public void dealStarted(Object owner) {
		if(cardsMap.containsKey(owner)) {
			cardsMap.get(owner).clear();
		}
		if (currentDisplayOwner == owner) {
			repaint();
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (currentDisplayOwner == null || !cardsMap.containsKey(currentDisplayOwner)) return;
	    Graphics2D g2 = (Graphics2D) g;
	    Dimension cardDimension = calculateCardDimension();
	    
	    int topLeftX = 0;
	    for (PlayingCard card : cardsMap.get(currentDisplayOwner)) {
	    	g2.drawRect(topLeftX, 0, (int) cardDimension.getWidth(), (int) cardDimension.getHeight());
	    	g2.drawString(card.getValue().toString(),topLeftX, 20); 
	    	topLeftX += cardDimension.getWidth();
		}
	    g2.dispose();
	}
	
	private Dimension calculateCardDimension() {
		double width = this.getWidth() / 6;
		double heigh = width * 1.5;
		
		return new Dimension((int) width, (int) heigh);
	}
}
