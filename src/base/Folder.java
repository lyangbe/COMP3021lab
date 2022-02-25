package base;
import java.util.*;
public class Folder {
	private ArrayList<Note> notes;
	private String name;
	public Folder (String name) {
		this.name=name;
		notes= new ArrayList<Note>();
	}
	public void addNote(Note n) {
		notes.add(n);
	}
	public String getName() {
		return this.name;
	}
	public ArrayList<Note> getNotes(){
		return this.notes;
	}
	public boolean equals(Object o) {
		if(o instanceof Folder) {
			return ((Folder)o).name.equals(this.name);
		}else {
			return false;
		}
	}
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (int i = 0; i < notes.size();i++) {
			if(notes.get(i) instanceof ImageNote) {
				nImage++;
			}else {
				nText++;
			}
		}
		return name+":"+nText+":"+nImage;
	}

}
