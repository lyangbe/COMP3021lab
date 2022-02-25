package base;
import java.util.Date;

public class Note {
	private Date date;
	private String title;
	public Note(String title) {
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	public String getTitle() {
		return this.title;
	}
	public boolean equals(Note n) {
		return this.title.equals(n.title);
	}
}
