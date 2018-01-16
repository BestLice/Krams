package PictureTag;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;

    public ImagePanel(File file) {
       loadImage(file);
    }
    
    public void loadImage(File file) {
    	try {                
            image = ImageIO.read(file);
            repaint();
         } catch (IOException ex) {
              ex.printStackTrace();
         }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), this);    
    }
}
