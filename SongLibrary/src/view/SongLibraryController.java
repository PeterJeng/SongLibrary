package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import songLibrary.app.Song;;

public class SongLibraryController {
	public static File songFile;
	
	public static void main(String[] args) {
		songFile = new File("SongFile.txt");
		
		Song song = new Song();
		
		song.name = "halo";
		song.album = "hello";
		song.artist = "beyonce";
		song.year = "2012";
		
		ArrayList<Song> songList = new ArrayList<Song>();
		
		songList.add(song);
		
		save(songList);
		
		
	}
	
	public static void save(ArrayList<Song> songList) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter(songFile, true);
			bw = new BufferedWriter(fw);
			
			for(int i = 0; i <songList.size(); i++) {
				bw.append(songList.get(i).toString());
				System.out.println(songList.get(i).toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
}


