package songLibrary.app;

public class Song {
	public String name;
	public String artist;
	public String album;
	public String year;
	
	public Song() {}
	
	public Song(String name, String artist, String album, String year) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public String toString() {
		return name + "," + artist + "," + album + "," + year;
	}
	
	public void SetFields(String information) {
		int firstComma = information.indexOf(",", 0);
		int secondComma = information.indexOf(",", firstComma + 1);
		int thirdComma = information.indexOf(",", secondComma + 1);
		
		this.name = information.substring(0, firstComma);
		this.artist = information.substring(firstComma + 1, secondComma);
		this.album = information.substring(secondComma + 1, thirdComma);
		this.year = information.substring(thirdComma + 1);
	}
}
