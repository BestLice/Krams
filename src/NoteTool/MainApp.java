package NoteTool;

import de.stkiese.dflt.GermanKeyboardConverter;
import de.stkiese.events.GlobalKeyEvent;
import de.stkiese.interfaces.GlobalKeyListener;
import de.stkiese.logger.GlobalKeyLogger;

public class MainApp {
	private GlobalKeyLogger logger = new GlobalKeyLogger();
	private NoteFrame frame = new NoteFrame();
	private Konsole konsole = null;

	public static void main(String[] args) {
		MainApp app = new MainApp();
		app.init();
	}

	private void init() {
		try {
            System.loadLibrary("GlobalKeyLogger");
            logger.addGlobalKeyListener(globalkey);
            logger.setConverter(new GermanKeyboardConverter());
            logger.startListening();
		} catch (UnsatisfiedLinkError e) {
			try {
				System.load(".\\lib\\GlobalKeyLogger.dll");
			} catch (UnsatisfiedLinkError ex) {
	            ex.printStackTrace();
			}
		}

		frame.setVisible(false);
	}

	private GlobalKeyListener globalkey = new GlobalKeyListener() {
		public void keyPressed(GlobalKeyEvent e) {
			if(e.isCtrlDown() && e.isMenuDown() && e.getConverted().equals("o")) {
				frame.setVisible(true);
			}
			if(e.isCtrlDown() && e.isMenuDown() && e.getConverted().equals("c")) {
				if(frame.isVisible()) {
					konsole = new Konsole(frame);
					konsole.setVisible(true);
				}
			}
		}
	};
}
