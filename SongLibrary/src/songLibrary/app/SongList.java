package songLibrary.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SongList {
	public ArrayList<Song> songList = new ArrayList<Song>();
	
	public static File songFile = new File("SongFile.txt");


	/*
	 * Saves the arraylist to songFile. Currently appends, but actual project will
	 * use write which will rewrite the entire file with new arraylist
	 */
	public void save() {
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(songFile, true);
			bw = new BufferedWriter(fw);

			for (int i = 0; i < songList.size(); i++) {
				bw.append(songList.get(i).toString());

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
	public static ArrayList<Song> readFile() {
		ArrayList<Song> songList = new ArrayList<Song>();

		BufferedReader br = null;
		FileReader fr = null;

		String line;

		try {
			fr = new FileReader(songFile);
			br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				Song song = new Song();

				song.setFields(line);

				songList.add(song);
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

		return songList;
	}
}