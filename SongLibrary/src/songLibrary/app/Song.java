package songLibrary.app;

public class Song implements Comparable<Song>{
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

	//compareTo method that will be used in Collection.sort
	//sorts in ascending order
	@Override
	public int compareTo(Song songToCompare) {
		//checks to see if the names are equal, if they are compare by artist, else compare by name

		if(this.name.equals(songToCompare.name))
			return this.artist.compareTo(songToCompare.artist);
		else
			return this.name.compareTo(songToCompare.name);
	}

	/*
	 * Given a string that contains name;artist;album;year
	 * Sets the instance field of the current song object to the respective fields in the string
	 */
	public void setFields(String information) {
		int firstColon = information.indexOf(";", 0);
		int secondColon = information.indexOf(";", firstColon + 1);
		int thirdColon = information.indexOf(";", secondColon + 1);

		this.name = information.substring(0, firstColon);
		this.artist = information.substring(firstColon + 1, secondColon);
		this.album = information.substring(secondColon + 1, thirdColon);
		this.year = information.substring(thirdColon + 1);
	}

	/*
	 * Separates the song by delimiters of ;
	 */
	public String stringWithDelimiter() {
		return name + ";" + artist + ";" + album + ";" + year;
	}

	public String toString() {
		return name + " - " + artist;
	}


}
