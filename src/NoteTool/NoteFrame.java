package NoteTool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class NoteFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextArea area = new JTextArea();
	private JPanel pnlButton = new JPanel();
	private JButton btnOK = new JButton("OK");
	private JButton btnCncl = new JButton("Abbrechen");

	public NoteFrame() {
		build();
		init();
	}

	private void init() {
		setTitle("QuickNote");
		addWindowListener(new WindowAdapter() {
			@SuppressWarnings("unused")
			public void WindowClosing(WindowEvent e) {
				hideFrame();
		    }
		});

		area.setLineWrap(true);
		area.addKeyListener(key);

		btnOK.addActionListener(action);
		btnCncl.addActionListener(action);

		pack();
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
			        new RowSpec("fill:150dlu"),
		            FormFactory.UNRELATED_GAP_ROWSPEC,
		            FormFactory.DEFAULT_ROWSPEC,
		            FormFactory.UNRELATED_GAP_ROWSPEC}));

		pnlButton.add(btnOK);
		pnlButton.add(btnCncl);

		add(new JScrollPane(area), new CellConstraints(2, 2, 3, 1));
		add(pnlButton, new CellConstraints(4, 4));
	}

	private void doOk() {
		hideFrame();
	}

	private void doCncl() {
		hideFrame();
	}

	private void hideFrame() {
		setVisible(false);
	}

	public String getText() {
		return area.getText();
	}
	public void setText(String str) {
		area.setText(str);
	}
	public void appendText(String str) {
		area.setText(area.getText() + str);
	}

	private KeyListener key = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
				doOk();
			}
		}
	};

	private ActionListener action = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnOK)) {
				doOk();
			}
			if(e.getSource().equals(btnCncl)) {
				doCncl();
			}
		}
	};

	@Override
	public void setVisible(boolean bool) {
		super.setVisible(bool);
		area.requestFocus();
	}
}
