package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AddPlayerRequest;

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
	
	public AddPlayerRequest getAddPlayerRequest() {
		AddPlayerRequest request = new AddPlayerRequest();
		request.setName(name.getText());
		request.setPoint(point.getText());
		return request;
	}
}
