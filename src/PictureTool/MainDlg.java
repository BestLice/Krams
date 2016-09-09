package PictureTool;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Utils.Resizer;
import Utils.Utils;

public class MainDlg extends JFrame implements ActionListener {
	/**
	 * !!! DO NOT TOUCH, UNLESS YOU KNOW WHAT YOU ARE DOING !!!
	 */
	private static final long	serialVersionUID	= 1L;

	private static String PATH = System.getProperty("user.home")+"\\PSP";
	private static String PATH_RESIZED = System.getProperty("user.home")+"\\PSP\\verkleinert";

	// Variablen
	private JLabel lblBreak = new JLabel();
	
	private JButton btnImage = new JButton("Bilder durchrechnen");
	private JButton btnClose = new JButton("Beenden");
	
	private File dict = null;
	
	// Konstruktor und Programmaufbau
	public MainDlg() {
		init();
		build();
		pack();
	}
	
	private void init() {
		dict = new File(PATH_RESIZED);
		if (!dict.exists()) {
			dict.mkdir();
		}
		dict = new File(PATH);
		if (!dict.exists()) {
			dict.mkdir();
		}
		
		btnImage.addActionListener(this);
		btnClose.addActionListener(this);
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
            	beenden();
            }
        });
	}
	
	private void build() {
		setLayout(new GridLayout(3, 1));
		
		add(btnImage);
		add(lblBreak);
		add(btnClose);
	}
	
	// Funktionen
	private void startGenerateImageFiles(File[] images) {
		Resizer resize = new Resizer();
		String ERROR = "";
		StringBuilder builder = new StringBuilder();
		
		builder.append(HEADER_TAG);
		
		if(images.length > 0) {
			for(int i = 0; i < images.length; i++) {
				try {
					File resizedImage = new File(PATH_RESIZED + "\\" + "k-" + images[i].getName());
					File thumb = new File(PATH_RESIZED + "\\" + "thumb-" + images[i].getName());
					resize.resize(images[i], 640, 640, true, true);
					resize.write(resizedImage, "jpg");
					resize.resize(images[i], 150, 150, true, true);
					resize.write(thumb, "jpg");
					builder.append("<td><a href=\"verkleinert/k-" + images[i].getName() + "\" rel=\"lightbox[group]\">  <img src=\"verkleinert/thumb-" + images[i].getName() + "\" /></a></td>\n");
					if((i+1) %5 == 0) builder.append("</tr><tr>\n");
				} catch (IOException e) {
					ERROR += "Das " + i+1 + " Bild (" + images[i].getName() + ") konnte nicht gelesen/verändert werden.\n";
				}
				
//				dialog.update(ProgressDialog.TYP_RENDERING, new Object[] {i+1, images.length});
			}
			
			builder.append(FOOTER_TAG);
			Utils.writeText2File(PATH+"\\index.html", builder.toString());
			
			if(ERROR.length() > 0) {
				JOptionPane.showMessageDialog(null, ERROR);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Es konnten keine Bilder geladen werden.\nBitte beachten Sie, das nur JPEG Dateien gelesen werden.");
		}
	}
	
	// Hilfsfunktionen
	private File[] loadImages() {
		List<File> images = new ArrayList<File>();
		File[] data = dict.listFiles();
		
		if(data != null && data.length > 0) {
			File curr = null;
			
			for(int i = 0; i < data.length; i++) {
				curr = data[i];
				
				String name = curr.getName();
				name = name.toLowerCase();
				if(
						name.substring(name.length()-3, name.length()).equals("jpg") ||
						name.substring(name.length()-4, name.length()).equals("jpeg")
				) {
					images.add(curr);
				}
			}
		}
		
		return images.toArray(new File[images.size()]);
	}
	
	private void beenden() {
		setVisible(false);
		System.exit(0);
	}
	
	// ActionListener
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnImage)) {
			startGenerateImageFiles(loadImages());
		}
		if(e.getSource().equals(btnClose)) {
			beenden();
		}
	}
	
	// ############
	// ### MAIN ###
	// ############
	public static void main(String args[]) {
		MainDlg dlg = new MainDlg();
		dlg.setVisible(true);
	}
	
	private static String HEADER_TAG = 
			"<html>\n" +
			"<head>\n" +
			"<meta name=\"viewport\" content=\"width=device-width\">\n" +
			"<link rel=\"stylesheet\" href=\"css/lightbox.css\" type=\"text/css\" media=\"screen\" />\n" +
			"</head>"+
			"<body>\n" +
			"<table>\n" +
			"<tr>";
	
	private static String FOOTER_TAG =
		"     </tr> \n" +
		"  </table> \n" +
		"</div> \n" +
		"<script src=\"js/jquery-1.7.2.min.js\"></script> \n" +
		"<script src=\"js/jquery-ui-1.8.18.custom.min.js\"></script> \n" +
		"<script src=\"js/jquery.smooth-scroll.min.js\"></script> \n" +
		"<script src=\"js/lightbox.js\"></script> \n" +
		
		"<script> \n" +
		"  jQuery(document).ready(function($) { \n" +
		"      $('a').smoothScroll({ \n" +
		"        speed: 1000, \n" +
		"        easing: 'easeInOutCubic' \n" +
		"      }); \n" +
		
		"      $('.showOlderChanges').on('click', function(e){ \n" +
		"        $('.changelog .old').slideDown('slow'); \n" +
		"        $(this).fadeOut(); \n" +
		"        e.preventDefault(); \n" +
		"      }) \n" +
		"  }); \n" +

		"  var _gaq = _gaq || []; \n" +
		"  _gaq.push(['_setAccount', 'UA-2196019-1']); \n" +
		"  _gaq.push(['_trackPageview']); \n" +
	    
		"  (function() { \n" +
		"    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true; \n" +
		"    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js'; \n" +
		"    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s); \n" +
		"  })(); \n" +
	    
		"</script> \n" +
		"</body> \n" +
		"</html> ";
}
