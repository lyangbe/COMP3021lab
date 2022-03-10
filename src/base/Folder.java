package base;
import java.util.*;
public class Folder implements Comparable<Folder> {
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
	@Override
	public int compareTo(Folder o) {
		return name.compareTo(o.getName());
	}
	
	public void sortNotes() {
		Collections.sort(notes);
		
	}
	public List<Note> searchNotes(String keywords){
		String[] allWords = keywords.toLowerCase().split(" ");
		List<Note> searchList = new ArrayList<Note>();
		//for (int i=0; i<allWords.length; i++) {
			//System.out.println(allWords[i]);
		//}
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i<allWords.length;) {
			List<String> curr = new ArrayList<String>();
			curr.add(allWords[i]); 
			//System.out.println(allWords[i]);
			while((i+1<allWords.length) && (allWords[i+1].equals("or"))) {
				//System.out.println("or detected");
				curr.add(allWords[i+2]);
				i = i + 2;
			}
			result.add(curr);
			//System.out.println(curr);
			i++;
		}
		//System.out.println(result);
		for (int i = 0; i<notes.size();i++) {
			Note note = notes.get(i);
			if(note instanceof ImageNote) {
				//System.out.println("imagenote: "+note.getTitle());
				boolean fit = true;
				boolean hasOne = false;
				for(int j = 0;j<result.size();j++) {
					List<String> orSet = result.get(j);
					for(int k = 0;k<orSet.size();k++) {
						if(note.getTitle().toLowerCase().contains(orSet.get(k))) {
							hasOne = true;
							//System.out.println("80: "+hasOne);
						}
						//System.out.println("82: "+hasOne);
					}
					if(hasOne==false) {
						//System.out.println("imageNote false");
						fit = false;
					}
				}
				if(fit) {
					searchList.add(note);
				}	
			}else if(note instanceof TextNote) {
				//System.out.println("textNote: "+note.getTitle());
				TextNote tNote= (TextNote) note;
				boolean fit = true;
				for(int j = 0;j<result.size();j++) {
					boolean hasOne = false;
					List<String> orSet = result.get(j);
					for(int k = 0;k<orSet.size();k++) {
						if(note.getTitle().toLowerCase().contains(orSet.get(k)) || tNote.getContent().toLowerCase().contains(orSet.get(k))) {
							hasOne = true;
							//System.out.println("hasOne");
					}
					}
					if(hasOne==false) {
						//System.out.println("textNote false");
						fit = false;
					}
				}
				if(fit) {
					searchList.add(note);
				}	
			}
		}	
		return searchList;
	}
}
