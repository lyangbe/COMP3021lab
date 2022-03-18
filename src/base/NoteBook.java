package base;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class NoteBook implements java.io.Serializable{
	private ArrayList<Folder> folders;
	private static final long serialVersionUID = 1L;
	public NoteBook() {
		folders= new ArrayList<Folder>();
	}
	
	//lab4

	public NoteBook(String file) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();
			this.folders=n.getFolders();
			in.close();
			fis.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean createTextNote(String folderName,String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	//overloading
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title,content);
		return insertNote(folderName,note);
	}
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	public boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for (Folder f1: folders) {
			if(f1.getName().equals(folderName)) {
				f=f1;
			}
		}
		if (f==null) {
			f = new Folder(folderName);
			folders.add(f);
		}
		for(Note n: f.getNotes()) {
			if( n.equals(note)) {
				System.out.println("Creating note "+ note.getTitle()+" under folder "+ folderName+" failed");
				return false;
			}
		}
		f.addNote(note);
		return true;
		}
	public void sortFolders() {
		for (int i =0; i<folders.size(); i++) {
			folders.get(i).sortNotes();
		}
		Collections.sort(folders);
		
	}
	//lab 3 
	public List<Note> searchNotes(String keywords) {
		List<Note> searchResult = new ArrayList<Note>();
		for(Folder f : this.getFolders()) {
			searchResult.addAll(f.searchNotes(keywords));
		}
		return searchResult;
	}
	
	//lab4
	/**
	 * method to save the NoteBook instance to file
	 * 
	 * @param file, the path of the file where to save the object serialization
	 * @return true if save on file is successful, false otherwise
	 */
	public boolean save(String file){
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
				fos = new FileOutputStream(file);
				out = new ObjectOutputStream(fos);
				out.writeObject(this);
				out.close();
				fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;   
		}
		return true;
	}
	



}
