package NoteStorage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import Utils.DateUtils;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;

	private static final String DIR_PATH = "C:\\Program Files (x86)\\Notepad++\\safetyData";

	private JTextArea area = new JTextArea();
	private JPanel pnlButton = new JPanel();
	private JTable tblDate = new JTable();
	private DefaultTableModel tmtblDate = null;
	private JTable tblTime = new JTable();
	private DefaultTableModel tmtblTime = null;
	private JScrollPane scrollDate = new JScrollPane();
	private JScrollPane scrollTime = new JScrollPane();
//	private JButton btnOK = new JButton("OK");
	private JButton btnCncl = new JButton("Beenden");

	private List<NoteData> master = new ArrayList<>();
	private List<NoteData> masterDate = new ArrayList<>();
	private List<NoteData> masterTime = new ArrayList<>();

	public Frame() {
		build();
		init();
		master = readFileList();
		initTableDate(master);
	}

	private void init() {
		setTitle("NoteStorage");
		addWindowListener(window);

		area.setLineWrap(true);
//		area.addKeyListener(key);

//		btnOK.addActionListener(action);
		btnCncl.addActionListener(action);

		pack();
	}

	public void initTableDate(List<NoteData> daten) {
		String[] columns;
		Object[][] data;

		masterDate = new ArrayList<>();
		for(NoteData curr : daten) {
			boolean run = true;
			for(NoteData ex : masterDate) {
				if(curr.getFilename().equals(ex.getFilename())) {
					run = false;
					break;
				}
			}
			if(run) {
				masterDate.add(curr);
			}
		}

		columns = new String[]{"Name"};
		data = new Object[masterDate.size()][1];

		for(int z = 0; z < masterDate.size(); z++) {
			data[z][0] = masterDate.get(z).getFilename();
		}

		tmtblDate = new DefaultTableModel(data, columns) {
			private static final long serialVersionUID = 7389180601262098694L;
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		tblDate.setModel(tmtblDate);
		tblDate.addMouseListener(mouse);
        tblDate.getSelectionModel().setSelectionInterval(0, 0);

        scrollDate.setViewportView(tblDate);
	}

	public void initTableTime(List<NoteData> daten, String choice) {
		String[] columns;
		Object[][] data;

		masterTime = new ArrayList<>();
		for(NoteData curr : daten) {
			if(curr.getFilename().equals(choice)) {
				masterTime.add(curr);
			}
		}

		columns = new String[]{"Datum", "Uhrzeit"};
		data = new Object[masterTime.size()][2];

		for(int z = 0; z < masterTime.size(); z++) {
			data[z][0] = DateUtils.date2string(masterTime.get(z).getModified());
			data[z][1] = DateUtils.date2string(masterTime.get(z).getModified(), "HH:mm:ss");
		}

		tmtblTime = new DefaultTableModel(data, columns) {
			private static final long serialVersionUID = 7389180601262098694L;
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		tblTime.setModel(tmtblTime);
		tblTime.addMouseListener(mouse);
		tblTime.getSelectionModel().setSelectionInterval(0, 0);

		scrollTime.setViewportView(tblTime);
	}

	private void build() {
		setLayout(new FormLayout(
		        new ColumnSpec[] {
		            FormFactory.RELATED_GAP_COLSPEC,
		            new ColumnSpec("fill:150dlu:grow(1.0)"),
		            FormFactory.RELATED_GAP_COLSPEC,
		            new ColumnSpec("fill:150dlu:grow(1.0)"),
		            FormFactory.RELATED_GAP_COLSPEC,
		            FormFactory.DEFAULT_COLSPEC,
		            FormFactory.RELATED_GAP_COLSPEC},
		        new RowSpec[] {
			        FormFactory.UNRELATED_GAP_ROWSPEC,
			        new RowSpec("fill:150dlu"),
			        FormFactory.UNRELATED_GAP_ROWSPEC,
			        new RowSpec("fill:150dlu:grow(1.0)"),
		            FormFactory.UNRELATED_GAP_ROWSPEC,
		            FormFactory.DEFAULT_ROWSPEC,
		            FormFactory.UNRELATED_GAP_ROWSPEC}));

//		pnlButton.add(btnOK);
		pnlButton.add(btnCncl);

		add(scrollDate, new CellConstraints(2, 2));
		add(scrollTime, new CellConstraints(4, 2, 3, 1));
		add(new JScrollPane(area), new CellConstraints(2, 4, 5, 1));
		add(pnlButton, new CellConstraints(4, 6));
	}

	private List<NoteData> readFileList() {
		List<NoteData> ret = new ArrayList<NoteData>();
		File[] files = null;
		File dir = new File(DIR_PATH);
		if(dir.isDirectory()) {
			files = dir.listFiles();
		} else {
			return ret;
		}

		for(File f : files) {
			if(!f.isDirectory()) {
				ret.add(new NoteData(f));
			}
		}

		return ret;
	}

	private void readFile(NoteData data) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(data.getFile()));
			String line;
			while ((line = in.readLine()) != null) {
				appendText(line+"\n");
			} in.close();
		} catch (IOException e) {
			appendText("Konnte nicht gelesen werden");
		}
	}

	private void appendText(String str) {
		area.setText(area.getText() + str);
	}

	private void doCncl() {
		finish();
	}

	private void finish() {
		System.exit(0);
	}

	private ActionListener action = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
//			if(e.getSource().equals(btnOK)) {
//				doOk();
//			}
			if(e.getSource().equals(btnCncl)) {
				doCncl();
			}
		}
	};

	private MouseListener mouse = new MouseListener() {
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(tblDate)) {
				initTableTime(master, tmtblDate.getValueAt(tblDate.getSelectedRow(), 0).toString());
			}
			if(e.getSource().equals(tblTime)) {
				if(tblTime.getSelectedRow() >= 0) {
					area.setText("");
					readFile(masterTime.get(tblTime.getSelectedRow()));
				}
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	};

	private WindowAdapter window = new WindowAdapter() {
		@SuppressWarnings("unused")
		public void WindowClosing(WindowEvent e) {
			finish();
	    }
	};





//	###################################
//	### Stuff I maybe need sometime ###
//	###################################

//	private void doOk() {
//		hideFrame();
//	}

//	private void hideFrame() {
//		setVisible(false);
//	}

//	private String getText() {
//		return area.getText();
//	}

//	private void setText(String str) {
//		area.setText(str);
//	}

//	private KeyListener key = new KeyListener() {
//		public void keyTyped(KeyEvent e) {}
//		public void keyReleased(KeyEvent e) {}
//		public void keyPressed(KeyEvent e) {
//			if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
//				doOk();
//			}
//		}
//	};
}
