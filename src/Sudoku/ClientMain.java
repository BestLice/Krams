package Sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientMain extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	// buttonmenu :)
	private JButton start = new JButton(CONSTANTS.START_BTN);
	private JButton draw = new JButton(CONSTANTS.DARW_BTN);
	private JButton settings = new JButton(CONSTANTS.SETTINGS_BTN);
	private JButton exit = new JButton(CONSTANTS.EXIT_BTN);
	
	// Konstruktor
	public ClientMain(){
		init();
		build();
	}
	
	private void init(){
		setTitle(CONSTANTS.getTitle());
		setSize(CONSTANTS.MENU_WIDTH, CONSTANTS.MENU_HEIGHT);
		setLayout(null); // I want to build my own Layout 
		setLocation(CONSTANTS.getMenuLocation()); // Try to locate it as central as possible
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// TODO initialise EVERYTHING
	}

	private void build(){
		/*
		 * set size and position of the buttons 
		 * add buttons to the frame
		 * add ActionListener
		 */
		start.setBounds(40, 40, 200, 40);
		start.addActionListener(this);
		add(start);
		
		draw.setBounds(40, 120, 200, 40);
		draw.addActionListener(this);
		add(draw);
		
		settings.setBounds(40, 200, 200, 40);
		settings.addActionListener(this);
		add(settings);
		
		exit.setBounds(40, 280, 200, 40);
		exit.addActionListener(this);
		add(exit);
		
		// TODO build up FUCKING EVERYTHING
	}
	
	public void actionPerformed(ActionEvent e) {
		// What shall we do with a click on the button "start" 
		if(e.getSource().equals(start)){
			JOptionPane.showMessageDialog(null, "Hier entsteht demnächst ein Funktion !!!\nHier lässt sich dann ein zufälliges Sudoku starten."); // TODO Open new Gameframe with puzzle from Database
		}
		
		// What shall we do with a click on the button "draw"
		if(e.getSource().equals(draw)){
			GameFrame game = new GameFrame(this);
			game.setVisible(true);
			
			setVisible(false);
		}
		
		// What shall we do with a click on the button "settings"
		if(e.getSource().equals(settings)){
			JOptionPane.showMessageDialog(null, "Hier entsteht demnächst ein Funktion !!!\nHier lässt sich dann alles mögliche Einstellen."); // TODO Open new Frame to configure FUCKING EVERYTHING
		}

		// What shall we do with a click on the button "exit"
		if(e.getSource().equals(exit)){
			System.exit(0); // Good BYE
		}
	}
	
	// We have to start somewhere....
	public static void main(String[] args) {
		ClientMain main = new ClientMain();
		main.setVisible(true);
	}
}
