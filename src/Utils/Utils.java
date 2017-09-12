package Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.ArrayUtils;

import PictureTool.MyLog;

//import com.sun.image.codec.jpeg.ImageFormatException;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Utils {
	public static int SORT_ASC = 0;
	public static int SORT_DESC = 1;
	
	public static double string2double (String value){
		double ret = -1.0;
		
		try {
			ret = Double.valueOf(value).doubleValue();
		} catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public static int string2int (String value){
		int ret = -1;
		
		try {
			ret = Integer.valueOf(value).intValue();
		} catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		return ret;
	}

	public static String obj2string(Object o){
		return String.valueOf(o);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> array2map (Object[][] val){
		return ArrayUtils.toMap(val);
	}
	
	public static Object[] list2array(List<?> val){
		return val.toArray(new Object[val.size()]);
	}
	
//	public static List<Object> array2list(Object[] val){
//		return Arrays.asList(val);
//	}
		
	public static int[] sortInt(int[] array,int typ){
		MyLog dlg = MyLog.getInstance();
		if(typ == SORT_ASC){
			if(dlg != null){
				dlg.appendDblLine();
				dlg.appendDebug("ASC SORT");
				dlg.appendDblLine();
			}
			return ascSort (array);
		}
		else if(typ == SORT_DESC){
			if(dlg != null){
				dlg.appendDblLine();
				dlg.appendDebug("DESC SORT");
				dlg.appendDblLine();
			}
			return descSort(array);
		}
		else{
			if(dlg != null){
				dlg.appendError("Falsche Typangabe");
			}
			return null;
		}
	}
	
	private static int[] ascSort(int[] array){
		MyLog dlg = MyLog.getInstance();
		boolean unsortiert=true;
	    int temp;
	      
	    while (unsortiert){
	    	unsortiert = false;
				
	    	dlg.appendLine();
			dlg.appendDebug("Neuer Versuch");
			
			for (int i=0; i < array.length; i++){
	    		dlg.appendDebug(array[i]);
			}
			
	    	for (int i=0; i < array.length-1; i++){
	    		if (array[i] > array[i+1]) {  
	    			temp       = array[i];
		            array[i]   = array[i+1];
		            array[i+1] = temp;
		            unsortiert = true;
	    		}
	    	}
	    }
	    
	    return array;
	}
	
	private static int[] descSort(int[] array){
		MyLog dlg = MyLog.getInstance();
		boolean unsortiert=true;
		int temp;
		
		while (unsortiert){
			unsortiert = false;
			dlg.appendLine();
			dlg.appendDebug("Neuer Versuch");
			
			for (int i=0; i > array.length-1; i++){
				dlg.appendDebug(array[i]);
			}
			for (int i=0; i < array.length-1; i++){
				if (array[i] < array[i+1]) {  
					temp       = array[i];
					array[i]   = array[i+1];
					array[i+1] = temp;
					unsortiert = true;
				}
			}
		}
		return array;
	}
	
	public static void writeText2File (String pfad, String txt){
		try (BufferedWriter out = new BufferedWriter(new FileWriter(pfad, true))) {
		    out.write(txt);
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fileCopy( File in, File out ) {
        try (
        		FileInputStream instream = new FileInputStream(in);
                FileOutputStream outstream = new FileOutputStream(out);
        		FileChannel inChannel = instream.getChannel();
                FileChannel outChannel = outstream.getChannel();
        ) {
            int maxCount = (64 * 1024 * 1024) - (32 * 1024);
            long size = inChannel.size();
            long position = 0;
            while ( position < size ) {
               position += inChannel.transferTo( position, maxCount, outChannel );
            }
            
            instream.close();
     	   	inChannel.close();
     	   	outstream.close();
			outChannel.close();
        } catch (IOException e){
        	e.printStackTrace();
        }
    }
	
//	public void createThumbnail(String filename, int thumbWidth, int thumbHeight, int quality, String outFilename){
//        Image image = Toolkit.getDefaultToolkit().getImage(filename);
//        MediaTracker mediaTracker = new MediaTracker(new Container());
//        mediaTracker.addImage(image, 0);
//        try {
//			mediaTracker.waitForID(0);
//	        double thumbRatio = (double)thumbWidth / (double)thumbHeight;
//	        int imageWidth = image.getWidth(null);
//	        int imageHeight = image.getHeight(null);
//	        double imageRatio = (double)imageWidth / (double)imageHeight;
//	        if (thumbRatio < imageRatio) {
//	            thumbHeight = (int)(thumbWidth / imageRatio);
//	        } else {
//	            thumbWidth = (int)(thumbHeight * imageRatio);
//	        }
//	         
//	        BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
//	        Graphics2D graphics2D = thumbImage.createGraphics();
//	        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//	        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
//	         
//	        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename));
//	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//	        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
//	        quality = Math.max(0, Math.min(quality, 100));
//	        param.setQuality((float)quality / 100.0f, false);
//	        encoder.setJPEGEncodeParam(param);
//	        encoder.encode(thumbImage);
//	        out.close();
//		} catch (InterruptedException | ImageFormatException | IOException e) {
//			e.printStackTrace();
//		}
//    }
//	
//	public static void captureScreen(String fileName) {
//		 try{
//		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		   Rectangle screenRectangle = new Rectangle(screenSize);
//		   Robot robot = new Robot();
//		   BufferedImage image = robot.createScreenCapture(screenRectangle);
//		   ImageIO.write(image, "png", new File(fileName));
//		 }
//		 catch(IOException | AWTException e){
//			 e.printStackTrace();
//		 }
//	}
	
	public static void postMail( String recipients[ ], String subject, String message , String from){
		 boolean debug = false;
	 
	     Properties props = new Properties();
	     try{
	    	 props.put("mail.smtp.host", "mail.jreh.de");
	    	 
		     Session session = Session.getDefaultInstance(props, null);
		     session.setDebug(debug);
		 
		     Message msg = new MimeMessage(session);
		 
		     InternetAddress addressFrom = new InternetAddress(from);
		     msg.setFrom(addressFrom);
		 
		     InternetAddress[] addressTo = new InternetAddress[recipients.length];
		     for (int i = 0; i < recipients.length; i++){
		        addressTo[i] = new InternetAddress(recipients[i]);
		     }
		     msg.setRecipients(Message.RecipientType.TO, addressTo);
		     msg.setSubject(subject);
		     msg.setContent(message, "text/plain");
		     Transport.send(msg);
	     }
	     catch(MessagingException e){
	    	 e.printStackTrace();
	     }
	}
}
