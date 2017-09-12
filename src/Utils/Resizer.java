package Utils;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class Resizer {
	public static Object BICUBIC = RenderingHints.VALUE_INTERPOLATION_BICUBIC;
    public static Object BILINEAR = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
    public static Object NEAREST_NEIGHBOR = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;

    private BufferedImage image;

    public BufferedImage resize(String img, int targetWidth, int targetHeight, boolean crossresize, boolean higherQuality) throws IOException {
        return resize(new File(img), targetWidth, targetHeight, crossresize, higherQuality);
    }

    public BufferedImage resize(File f, int targetWidth, int targetHeight, boolean crossresize, boolean higherQuality) throws IOException {
        return resize( ImageIO.read(f), targetWidth, targetHeight, crossresize, higherQuality);
    }

    public BufferedImage resize(BufferedImage img, int targetWidth, int targetHeight, boolean crossresize, boolean higherQuality) {
        return resize(img, targetWidth, targetHeight, crossresize, higherQuality, false);
    }
    
    public BufferedImage resize(BufferedImage img, int targetWidth, int targetHeight, boolean crossresize, boolean higherQuality, boolean force) {
        if(img == null) {
        	return null;
        }

        int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        image = img;
        int w, h;

        int realW = img.getWidth();
        int realH = img.getHeight();

        double scale = 0.0;

        if(crossresize) {
        	if(realW > realH) {
        		scale = getImageScale(realW, realH, targetWidth, 0);
        	}else {
        		scale = getImageScale(realW, realH, 0, targetHeight);
        	}
        }else {
        	scale = getImageScale(realW, realH, targetWidth, targetHeight);
        }
        
        //lets scale image in case if one of the parameter is 0
        targetWidth = (int) (realW * scale);
        targetHeight = (int) (realH * scale);

        //lets prevent resizing to a bigger image as it is
        if (force || ((targetWidth > realW || targetHeight > realH))) return img;

        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = realW;
            h = realH;
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        BufferedImage tmp = null;

        do {
            if (higherQuality && w > targetWidth) {
                w /= 2;
                if (w < targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h > targetHeight) {
                h /= 2;
                if (h < targetHeight) {
                    h = targetHeight;
                }
            }

            tmp = new BufferedImage(w, h, type);
            Graphics2D g = tmp.createGraphics();
//            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(image, 0, 0, w, h, null);
            g.dispose();

            image = tmp;

            tmp.flush(); //flush resources

        } while (w != targetWidth || h != targetHeight);

        return image;
    }

    public void dispose() {
        image.flush();
        image = null;
    }

    public void write(String img, String format) throws IOException {
        write(new File(img), format);
    }

    public void write(File f, String format) throws IOException {
        if(image != null) {
            f.mkdirs();
            ImageIO.write(image, format, f);
        }
    }

    public void write(String format, OutputStream outputStream) throws IOException {
        if(image != null) {
        	ImageIO.write(image, format, outputStream);
        }
    }

    private static double getImageScale(int sourceWidth, int sourceHeight, int targetWidth, int targetHeight) {
        double scalex = (double) targetWidth / sourceWidth;
        double scaley = (double) targetHeight / sourceHeight;
        return Math.max(scalex, scaley);
    }
}