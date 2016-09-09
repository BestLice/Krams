package Sudoku;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JFrame parent = null; // Will be the MenuFrame after constructing Frame

	// Konstruktor
	public GameFrame(JFrame parent){
		this.parent = parent;
		
		init();
		build();
	}
	
	private void init(){
		setTitle(CONSTANTS.getTitle());
		setSize(CONSTANTS.GAME_WIDTH, CONSTANTS.GAME_HEIGHT);
		setLayout(null); // I want to build my own Layout 
		setLocation(CONSTANTS.getGameLocation()); // Try to locate it as central as possible
		
		// When closing this Frame, I want to see the Menu again.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(true);
				parent.setVisible(true);
				dispose();
			}
		});
	}

	private void build(){
		
	}
}
