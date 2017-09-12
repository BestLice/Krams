package SQLTool;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


public class MainClass extends JFrame implements ActionListener, ClipboardOwner{
	private static final long	serialVersionUID	= 1L;
	private JPanel pnl = new JPanel();
	private JTextArea area = new JTextArea();
	private JScrollPane scroll = new JScrollPane();
	private JButton btnSql = new JButton("In SQL umwandeln");
	private JButton btnJava = new JButton("In Java umwandeln");
	private JCheckBox chk = new JCheckBox("Mit Bemerkungen");

	public MainClass() {
		build();
		init();
	}

	private void build() {
		setLayout(new FormLayout(
			        new ColumnSpec[] {
			            FormFactory.RELATED_GAP_COLSPEC,
			            new ColumnSpec("fill:150dlu:grow(1.0)"),
			            FormFactory.RELATED_GAP_COLSPEC,
			            FormFactory.DEFAULT_COLSPEC,
			            FormFactory.RELATED_GAP_COLSPEC},
			        new RowSpec[] {
				        FormFactory.UNRELATED_GAP_ROWSPEC,
				        FormFactory.DEFAULT_ROWSPEC,
			            FormFactory.UNRELATED_GAP_ROWSPEC,
			            new RowSpec("fill:300dlu"),
			            FormFactory.UNRELATED_GAP_ROWSPEC}));

		add(pnl, new CellConstraints(4, 2));
		btnSql.addActionListener(this);
		btnJava.addActionListener(this);
		pnl.add(chk);
		pnl.add(btnSql);
		pnl.add(btnJava);
		scroll.setViewportView(area);
		add(scroll, new CellConstraints(2, 4, 3, 1));
	}

	private void init() {
		setTitle("SQL Tool");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}

	private void wandle2SQL() {
		String text = area.getText();
		String curr = "";

		text = text.replaceAll("\";", "");
		text = text.replaceAll(";", "");
		text = text.replaceAll("\\\\n", "");
		text = text.replaceAll("\\\\r", "");
		text = text.replaceAll("\t", "");
		text = text.replaceAll("\" \\+", "");
		text = text.replaceAll("\"", "");
//		text = text.replaceAll("\\", "\" ");
		text = text.trim();
		text += ";";

		String[] strs = text.split("\n");
		text = "";
		for(int i = 0; i < strs.length; i++) {
			curr = strs[i];
			if(curr.contains("//") && !chk.isSelected()) {
				curr = curr.substring(0, curr.indexOf("//"));
			}
			text += curr +"\n";
		}

		area.setText(text);
	}

	private void wandle2Java() {
		String text = area.getText();
		String curr = "";

		text = text.replaceAll(";", "");

		String[] strs = text.split("\n");
		text = "";
		for(int i = 0; i < strs.length; i++) {
			curr = strs[i];
			if(curr.contains("--") && !chk.isSelected()) {
				curr = curr.substring(0, curr.indexOf("--"));
			}
			curr = curr.replaceAll("\"", "\\\\\"");
			text += "\" "+ curr + " \"";
			if(i == strs.length-1) text += ";";
			else text += " +\n";
		}

		area.setText(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSql)) {
			if(area.getText().length() == 0) {
				load(area);
			}
			wandle2SQL();
		}
		if(e.getSource().equals(btnJava)) {
			if(area.getText().length() == 0) {
				load(area);
			}
			wandle2Java();
		}
		copy(area, area.getText());
		area.requestFocus();
	}

	public void copy(JComponent owner, String text) {
		Clipboard cb = owner.getToolkit().getSystemClipboard();
		StringSelection cbcontents = new StringSelection(text);
		cb.setContents(cbcontents, this);
	}
	public void load(JComponent owner) {
		try {
			Clipboard cb = owner.getToolkit().getSystemClipboard();
			Transferable transfer = cb.getContents(null);
			area.setText((String) transfer.getTransferData(DataFlavor.stringFlavor));
		}
		catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {}

	public static void main (String args[]) {
		MainClass dlg = new MainClass();
		dlg.setVisible(true);
	}
}
