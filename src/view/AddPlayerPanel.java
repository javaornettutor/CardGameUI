package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPlayerPanel extends JPanel {
	
	private JTextField name;
	private JTextField point;
	
	public AddPlayerPanel() {
		super();
		this.setLayout(new GridLayout(0, 2, 2, 2));
		initializeComponents();
	}

	private void initializeComponents() {
		this.add(new JLabel("Name"));
		name = new JTextField();
		this.add(name);
		point = new JTextField();
		this.add(new JLabel("Point"));
		this.add(point);
	}
	
	public String getInputName() {
		return name.getText();
	}
	
	public String getInputPoint() {
		return point.getText();
	}
}
