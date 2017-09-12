package PictureTool;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MyLogDlg extends JFrame implements WindowListener{
	private static final long serialVersionUID = 1L;
	private JMenuBar bar = new JMenuBar();
	private JMenu menu = new JMenu("Einstellungen");
	private JCheckBoxMenuItem  info = new JCheckBoxMenuItem("INFO");
	private JCheckBoxMenuItem debug = new JCheckBoxMenuItem("DEBUG");
	private JCheckBoxMenuItem error = new JCheckBoxMenuItem("ERROR");
	private JCheckBoxMenuItem  line = new JCheckBoxMenuItem("LINE");
	private JPanel pnl = new JPanel();
	public MyTextArea area = new MyTextArea(30,30);
	private JScrollPane pane = new JScrollPane(area);
	private JLabel lbl = new JLabel("Debuginformationen:");
	
	public MyLogDlg(){
		pane.setSize(50, 50);
		
		setLayout(new FormLayout(
				new ColumnSpec[]{
					FormFactory.DEFAULT_COLSPEC
				},
				new RowSpec[]{
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.UNRELATED_GAP_ROWSPEC,
					FormFactory.PREF_ROWSPEC
				}));
		pnl.setLayout(new FormLayout(
				new ColumnSpec[]{
						FormFactory.UNRELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.UNRELATED_GAP_COLSPEC
				},
				new RowSpec[]{
						FormFactory.UNRELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.UNRELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC,
						FormFactory.UNRELATED_GAP_ROWSPEC
				}));
		
		menu.add(info);
		menu.add(debug);
		menu.add(error);
		menu.add(line);
		
		bar.add(menu);
		
		pnl.add(lbl, new CellConstraints(2, 2));
		pnl.add(pane, new CellConstraints(2, 4));

		add(bar, new CellConstraints(1, 1));
		add(pnl, new CellConstraints(1, 3));
		
				
		area.setEditable(false);
		info.setSelected(true);
		debug.setSelected(true);
		error.setSelected(true);
		line.setSelected(true);
		
		pack();
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - 400, 0);
		addWindowListener(this);
		setVisible(true);
	}
	
	public boolean showInfo(){
		return info.isSelected();
	}
	public boolean showError(){
		return error.isSelected();
	}
	public boolean showDebug(){
		return debug.isSelected();
	}
	public boolean showLine(){
		return line.isSelected();
	}

	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {
		setVisible(false);
		dispose();
	}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
}
