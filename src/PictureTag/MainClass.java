package PictureTag;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MainClass extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel btnPnl = new JPanel();
	private ImagePanel imagePnl = null;
	
	private JButton btnPrev = new JButton("<--");
	private JButton btnNext = new JButton("-->");
	
	private ImageStore images = new ImageStore();
	
	public MainClass() {
		init();
		build();
	}

	private void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		images.loadData("C:\\Users\\Harry\\OneDrive\\Pictures\\Wallpaper\\Desktop");
//		images.loadData(selectFile());
		
		setTitle("Bildergalerie - Zeige Bild 1 von " + images.size());
		setSize(1024, 768);
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/4, Toolkit.getDefaultToolkit().getScreenSize().height/4);
	}
	
	private void build() {
		setLayout(new FormLayout(
		        new ColumnSpec[] {
		            FormFactory.RELATED_GAP_COLSPEC,
		            new ColumnSpec("fill:150dlu:grow(1.0)"),
		            FormFactory.RELATED_GAP_COLSPEC,
		            FormFactory.DEFAULT_COLSPEC,
		            FormFactory.RELATED_GAP_COLSPEC,
		            new ColumnSpec("fill:150dlu:grow(1.0)"),
		            FormFactory.RELATED_GAP_COLSPEC},
		        new RowSpec[] {
			        FormFactory.UNRELATED_GAP_ROWSPEC,
			        new RowSpec("fill:300dlu:grow(1.0)"),
		            FormFactory.UNRELATED_GAP_ROWSPEC,
		            FormFactory.DEFAULT_ROWSPEC,
		            FormFactory.UNRELATED_GAP_ROWSPEC}));
		
		imagePnl = new ImagePanel(images.next());
		btnPrev.addActionListener(action);
		btnNext.addActionListener(action);
		btnPnl.add(btnPrev);
		btnPnl.add(btnNext);
		
		add(imagePnl, new CellConstraints(2, 2, 5, 1));
		add(btnPnl, new CellConstraints(4, 4));
	}
	
	private void updateTitle() {
		setTitle("Bildergalerie - Zeige Bild " + (images.getIndex()+1) + " von " + images.size());
	}
	
	private File selectFile() {                                           
	    JFileChooser fc=new JFileChooser();
	    File ret = null;
	    
	    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//	    fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    fc.setFileFilter(new FileNameExtensionFilter("Bilder", "jpg", "jpeg"));
	    if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            ret = fc.getSelectedFile();
	    }
	    
	    return ret;
	}
	
	public static void main(String[] args) {
		MainClass c = new MainClass();
		c.setVisible(true);
	}
	
	private ActionListener action = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnNext)) {
				imagePnl.loadImage(images.next());
				updateTitle();
			}
			if(e.getSource().equals(btnPrev)) {
				imagePnl.loadImage(images.previous());
				updateTitle();
			}
		}
	};
}
