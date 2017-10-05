package songLibrary.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SongList {
	public ArrayList<Song> list = new ArrayList<Song>();

	public static File songFile = new File("SongFile.txt");

	/*
	 * Saves the arraylist to songFile. Currently appends, but actual project will
	 * use write which will rewrite the entire file with new arraylist
	 */
	public void save() {
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(songFile, false);
			bw = new BufferedWriter(fw);

			for (int i = 0; i < list.size(); i++) {
				bw.write(list.get(i).stringWithDelimiter());

				// This new line might cause future problem when we are reading from a file and
				// we need to terminate the reader
				bw.newLine();
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				if (bw != null) {
					bw.close();
				}

				if (fw != null) {
					fw.close();
				}

			} catch (IOException ex) {
				ex.printStackTrace();

			}
		}
	}

	/*
	 * Read the songFile and returns an ArrayList of songs
	 */
	public void readFile() {

		BufferedReader br = null;
		FileReader fr = null;

		// check if file exists, if it doesn't create a new SongFile
		if (!songFile.exists()) {
			try {
				songFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String line;

		try {
			fr = new FileReader(songFile);
			br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				Song song = new Song();

				//each line represents information for a song
				//setFields sets the song's instance field to the information given
				song.setFields(line);

				list.add(song);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}

				if (fr != null) {
					fr.close();
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/*
	 * sorts the song by name and artist in lexographically ascending order
	 */
	public void sort() {
		Collections.sort(list);
	}
	
	public int add(Song song) {
		//check to see if list contains the song
		//if it does, return -1 representing bad add
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(song)) 
				return -1;
		}
		
		//add the song and sort
		list.add(song);
		
		sort();
		save();
		
		//return the index of the song in current list
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(song))
				return i;
		}
		
		//this code shouldn't be reached
		return -1;
	}
	
	/*
	 * edits the song inside the list
	 * Parameter: indexOfSong to be edited, newSong is the new Song information to be edited
	 *
	 */
	public int edit(int indexOfSong, Song newSong) {
		Song oldSong = list.get(indexOfSong);
		//check to see if all values are the same as the old song, return -1 if true
		if(oldSong.equals(newSong)  
			&& oldSong.album.equals(newSong.album)
			&& oldSong.year.equals(newSong.year))
			return -1;	
		
		for(int i = 0; i < list.size(); i++) {
			//skip the loop at the indexOfSong so we don't compare the same song if the user didn't change name and artist
			if(i == indexOfSong)
				continue;
			
			//if the song at index i is equal to newSong, the edit can't happen due to duplicate name and artist
			if(list.get(i).equals(newSong))
				return -2;
		}
		
		//change the index of the current song to the information in newSong
		list.get(indexOfSong).name = newSong.name;
		list.get(indexOfSong).artist = newSong.artist;
		list.get(indexOfSong).album = newSong.album;
		list.get(indexOfSong).year = newSong.year;
		
		//sort and save the list
		sort();
		save();
		
		//return index of the new song
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(newSong))
				return i;
		}
			
		//dead code
		return -2;
		
	}

}