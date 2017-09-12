package NoteTool;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Konsole extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final String KILL_COMMAND = "KILL";
	private static final String STASH_COMMAND = "STASH NOTES";
	private static final String READ_COMMAND = "READ NOTES";

	private static final String STASH_FILE = System.getenv("APPDATA")+"//quicknote.dat";

	private JTextField txt = new JTextField(30);
	private NoteFrame notes;

	public Konsole(NoteFrame notes) {
		this.notes = notes;

		setTitle("CONSOLE");
		txt.addKeyListener(key);
		add(txt);
		pack();
		requestFocus();
	}

	private void doEnter(String command) {
		if(command.equals(KILL_COMMAND)) {
			finish();
		}
		if(command.equals(STASH_COMMAND)) {
			stashNotes();
		}
		if(command.equals(READ_COMMAND)) {
			readNotes();
		}
		setVisible(false);
	}

	private static void finish() {
		System.exit(0);
	}

	private void stashNotes() {
		try (PrintWriter out = new PrintWriter(new FileWriter(STASH_FILE))) {
			out.println(notes.getText());
			out.close();

			notes.setText("");
		} catch (IOException e) {
			notes.appendText("Konnte nicht gestashed werden");
		}
	}
	private void readNotes() {
		try (BufferedReader in = new BufferedReader(new FileReader(STASH_FILE))) {
			String line;
			while ((line = in.readLine()) != null) {
				notes.appendText("\n"+line);
			} in.close();
		} catch (IOException e) {
			notes.appendText("Konnte nicht gelesen werden");
		}
	}

	private KeyListener key = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				doEnter(txt.getText());
			}
		}
	};
}
