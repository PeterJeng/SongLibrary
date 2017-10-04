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

	public static void main(String[] args){
		// ALL OF THE THINGS BELOW ARE JUST TESTS TO MAKE SURE PROGRAM WORKS, deleting
		// this will NOT affect the code
		
		Song song = new Song();

		song.name = "test123";
		song.album = "abc";
		song.artist = "yes";
		song.year = "2012";
		 
		SongList songList = new SongList();
		//always initialize with a readFile. The readFile will create a SongFile if file is not found
		songList.readFile();
		songList.list.add(song);
		
		//if you call sort, always call save immediately
		songList.sort();
		songList.save();
		
		for(int i = 0; i < songList.list.size(); i++) {
			System.out.println(songList.list.get(i).toString());
		}
		
		
	}

	
	public void showItemInputDialog(ActionEvent e){};
	public void removeItem(ActionEvent e){};
	public void showItemEditDialog(ActionEvent e){};
}
