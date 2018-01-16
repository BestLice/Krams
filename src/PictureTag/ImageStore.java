package PictureTag;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageStore extends ArrayList<File> {
	private static final long serialVersionUID = 1L;
	
	private int index = -1;
	
	public void loadData(String filename) {
		loadData(new File(filename));
	}
	
	public void loadData(File file) {
		if(file.exists()) {
			if(file.isDirectory()) {
				addAll(Arrays.asList(file.listFiles()));
			} else {
				add(file);
			}
		}
	}
	
	public void loadData(File[] files) {
		for(File file : files) {
			if(file.exists()) {
				add(file);
			}
		}
	}
	
	public int getIndex() {
		return index;
	}
	
	public File next() {
		System.out.println(index);
		if(index + 1 < size()) {
			return get(++index);
		} 
		return get(index);
	}
	
	public File previous() {
		System.out.println(index);
		if(index - 1 >= 0) {
			return get(--index);
		} 
		return get(index);
	}
}
