package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.interfaces.PlayingCard;

public class DealResultPanel extends JPanel {

	private static int NUM_OF_CARDS = 6;
	private static double CARD_NAME_SPACING_RATIO = 0.1;

	private Object currentDisplayOwner;
	private ViewContext viewContext;

	public DealResultPanel(ViewContext viewContext) {
		super();
		this.viewContext = viewContext;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}

	public void nextCardReceived(Object owner) {
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
		if (currentDisplayOwner == owner) {
			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (currentDisplayOwner == null || !viewContext.getCardsMap().containsKey(currentDisplayOwner))
			return;

		Graphics2D g2 = (Graphics2D) g;
		int cardSpacing = calculateCardSpacing();
		Dimension cardDimension = calculateCardDimension(cardSpacing);
		Point topLeft = new Point(cardSpacing, cardSpacing);
		
		for (PlayingCard card : viewContext.getCards(currentDisplayOwner)) {
			drawCard(g2, card, topLeft, cardDimension);
			drawCardName(g2, card, topLeft, cardDimension);
			drawSuit(g2, card, topLeft, cardDimension);
			topLeft.x += cardSpacing + cardDimension.getWidth();
		}
		g2.dispose();
	}

	private int calculateCardSpacing() {
		double width = (this.getWidth() / NUM_OF_CARDS) * 0.05;

		return (int) width;
	}

	private Dimension calculateCardDimension(int cardSpacing) {
		double width = (this.getWidth() - cardSpacing * (NUM_OF_CARDS + 1)) / NUM_OF_CARDS;
		double heigh = width * 1.5;

		return new Dimension((int) width, (int) heigh);
	}

	private void drawCard(Graphics2D g2, PlayingCard card, Point topLeft, Dimension cardDimension) {
		g2.setColor(Color.WHITE);
		g2.fill(new RoundRectangle2D.Double(topLeft.x, topLeft.y, cardDimension.width, cardDimension.height, 50, 50));
	}

	private void drawCardName(Graphics2D g2, PlayingCard card, Point cardTopLeft, Dimension cardDimension) {
		String cardName = nameOf(card);

		g2.setColor(colorOf(card));
		g2.setFont(new Font("default", Font.BOLD, (int) (cardDimension.width * CARD_NAME_SPACING_RATIO)));

		Dimension cardNameSize = new Dimension(g2.getFontMetrics().stringWidth(cardName),
				(int) g2.getFontMetrics().getLineMetrics(cardName, g2).getHeight());
		
		int spacing = (int) (cardDimension.getWidth() * CARD_NAME_SPACING_RATIO);

		int topX = cardTopLeft.x + spacing;
		int topY = cardTopLeft.y + spacing + (int)(cardNameSize.height / 2) ;

		int bottomX = cardTopLeft.x + cardDimension.width - cardNameSize.width - spacing;
		int bottomY = cardTopLeft.y + cardDimension.height - spacing;

		g2.drawString(cardName, topX, topY);
		g2.drawString(cardName, bottomX, bottomY);
	}
	
	private void drawSuit(Graphics2D g2, PlayingCard card, Point cardTopLeft, Dimension cardDimension) {
		URL imageUrl = this.getClass().getResource("/img/" + suitFileName(card));
		try {
			BufferedImage image = ImageIO.read(imageUrl);
			int width = (int) cardDimension.width / 3;
			int x = (cardTopLeft.x + cardDimension.width/2 - width/2);
			int y = (cardTopLeft.y + cardDimension.height/2 - width/2);
			g2.drawImage(image, x, y, width, width, null);
		}
		catch(IOException e) {
			System.out.println("Image not found");
		}
	}

	private String nameOf(PlayingCard card) {

		switch (card.getValue()) {
		case EIGHT:
			return "8";
		case NINE:
			return "9";
		case TEN:
			return "10";
		case JACK:
		case KING:
		case QUEEN:
		case ACE:
		default:
			return card.getValue().toString().substring(0, 1);
		}
	}

	private Color colorOf(PlayingCard card) {
		switch (card.getSuit()) {
		case DIAMONDS:
		case HEARTS:
			return Color.RED;
		case CLUBS:
		case SPADES:
		default:
			return Color.BLACK;
		}
	}
	
	private String suitFileName(PlayingCard card) {
		switch (card.getSuit()) {
		case DIAMONDS:
			return "diamonds.png";
		case HEARTS:
			return "hearts.png";
		case CLUBS:
			return "clubs.png";
		case SPADES:
		default:
			return "spades.png";
		}
	}
}
