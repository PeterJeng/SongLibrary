package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import songLibrary.app.Song;;

public class SongLibraryController {
	public static File songFile = new File("SongFile.txt");

	public static void main(String[] args) {
		//songFile = new File("SongFile.txt");

		//ALL OF THE THINGS BELOW ARE JUST TESTS TO MAKE SURE PROGRAM WORKS, deleting this will NOT affect the code
		Song song = new Song();

		song.name = "halo";
		song.album = "hello";
		song.artist = "beyonce";
		song.year = "2012";

		ArrayList<Song> songList = new ArrayList<Song>();

		songList.add(song);

		save(songList);
		
		String test = "name,artist,album,year";
		
		song.setFields(test);
		
		System.out.println(song.toString());

	}

	/*
	 * Saves the arraylist to songFile.
	 * Currently appends, but actual project will use write which will rewrite the entire file with new arraylist
	 */
	public static void save(ArrayList<Song> songList) {
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(songFile, true);
			bw = new BufferedWriter(fw);

			for (int i = 0; i < songList.size(); i++) {
				bw.append(songList.get(i).toString());
				
				//This new line might cause future problem when we are reading from a file and we need to terminate the reader
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
	 * Read the songFile and puts the information into an arraylist
	 */
	public static ArrayList<Song> readFile(){
		ArrayList<Song> songList = new ArrayList<Song>();
		
		
		
		return songList;
	}

}
