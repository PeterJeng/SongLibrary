package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import songLibrary.app.Song;
import songLibrary.app.SongList;

public class SongLibraryController {
	@FXML Button add;
	@FXML Button del;
	@FXML Button edit;

	public static void main(String[] args) {
		// ALL OF THE THINGS BELOW ARE JUST TESTS TO MAKE SURE PROGRAM WORKS, deleting
		// this will NOT affect the code
		
		Song song = new Song();

		song.name = "halo";
		song.album = "hello";
		song.artist = "beyonce";
		song.year = "2012";

		SongList songList = new SongList();
		
		songList.songList.add(song);
		
		songList.save();

		System.out.println(song.toString());


	}

	
	public void showItemInputDialog(ActionEvent e){};
	public void removeItem(ActionEvent e){};
	public void showItemEditDialog(ActionEvent e){};
}
