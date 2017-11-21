package TestStuff;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class TestNotifier {

    public static void main(String[] args) throws AWTException, IOException {
        if (SystemTray.isSupported()) {
            TestNotifier.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public static void displayTray() throws AWTException, IOException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = ImageIO.read(new File("src\\TestStuff\\icon.jpg"));
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resizes the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("HEIM & HAUS Infotainment", "Hier eine Beispielnachricht.", MessageType.ERROR);
        trayIcon.setToolTip("Tooltips sind auch möglich ? Cool !");
        trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Ha !! Geil !!");
			}
		});
    }
}