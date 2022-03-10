package base;
import java.util.Date;

public class Note implements Comparable<Note>{
	private Date date;
	private String title;
	public Note(String title) {
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	public String getTitle() {
		return title;
	}
	public boolean equals(Note n) {
		return title.equals(n.title);
	}
	public Date getDate() {
		return date;
	}
	@Override
	public int compareTo(Note o) {
		if(date.after(o.date)) {
			return -1;
		}else if(date.before(o.date)) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public String toString() {
		return date.toString() + "\t" + title;
	}
}
