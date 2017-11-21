package TestStuff;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class TestNotifier {
	private SystemTray tray = null;
	private TrayIcon trayIcon = null;
	private PopupMenu menu = new PopupMenu("Menu");
    private MenuItem info = new MenuItem("Info");
    private MenuItem warning = new MenuItem("Warnung");
    private MenuItem error = new MenuItem("Fehler");
    private MenuItem exit = new MenuItem("Beenden");
    
    public TestNotifier() {
    	tray = SystemTray.getSystemTray();
    	build();
    }

    private void build() {
    	info.addActionListener(action);
    	menu.add(info);
    	
    	warning.addActionListener(action);
    	menu.add(warning);
    	
    	error.addActionListener(action);
    	menu.add(error);

    	exit.addActionListener(action);
        menu.add(exit);
        
        try {
			trayIcon = new TrayIcon(ImageIO.read(new File("src\\TestStuff\\icon.jpg")), "Tray Demo");
	        trayIcon.setImageAutoSize(true);
	        trayIcon.setToolTip("Tooltips sind auch möglich ? Cool !");
	        trayIcon.setPopupMenu(menu);
	        tray.add(trayIcon);
		} catch (IOException | AWTException ex) {
			JOptionPane.showMessageDialog(null, "Programminterner Fehler beim Notifier");
		}
    }
    
    private ActionListener action = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if(e.getSource().equals(info)) {
    			trayIcon.displayMessage("HEIM & HAUS Infotainment", "Hier eine Beispielnachricht.", MessageType.INFO);
    		}
    		if(e.getSource().equals(warning)) {
    			trayIcon.displayMessage("HEIM & HAUS Infotainment", "Hier eine Beispielwarnung.", MessageType.WARNING);
    		}
    		if(e.getSource().equals(error)) {
    			trayIcon.displayMessage("HEIM & HAUS Infotainment", "Hier ein Beispielfehler.", MessageType.ERROR);
    		}
    		if(e.getSource().equals(exit)) {
    			System.exit(0);
    		}
		}
	};
	
	public static void main (String[] args){
		TestNotifier n = new TestNotifier();
		n.hashCode();
	}
}