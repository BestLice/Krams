package NoteStorage;

import java.io.File;
import java.util.Date;

import Utils.DateUtils;

public class NoteData {
	private File file = null;
	private String filename = null;
	private Date modified = null;

	public NoteData(File f) {
		if(f != null) {
			String tmp = f.getName();
			tmp = tmp.substring(0, tmp.length()-4); // ".bak" abschneiden (Lass es einfach so...)
			String tmpDate = tmp.substring(tmp.length() - 17, tmp.length());
			tmp = tmp.substring(0, tmp.length() - 18);

			setFile(f);
			setFilename(tmp);
			setModified(DateUtils.string2dateLOG(tmpDate));
		}
	}

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
}
