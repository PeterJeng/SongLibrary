package songLibrary.app;

public class Song {
	public String name;
	public String artist;
	public String album;
	public String year;
	
	public String toString() {
		return name + "," + artist + "," + album + "," + year;
	}
}
