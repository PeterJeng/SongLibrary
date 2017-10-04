package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import songLibrary.app.Song;
import songLibrary.app.SongList;

public class SongLibraryController {
	@FXML Button add;
	@FXML Button del;
	@FXML Button edit;
	@FXML Button cancel;
	@FXML TextField nameInput;
	@FXML TextField artistInput;
	@FXML TextField yearInput;
	@FXML TextField albumInput;
	@FXML Text delConfirm;
	@FXML static ListView<SongList> listView;

	private static ObservableList<SongList> obsList;

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
		obsList = FXCollections.observableArrayList(songList);
		listView.setItems(obsList);

		for(int i = 0; i < songList.list.size(); i++) {
			System.out.println(songList.list.get(i).toString());
		}


	}


	public void showItemInputDialog(ActionEvent e){
		nameInput.setDisable(false);
		artistInput.setDisable(false);
		yearInput.setDisable(false);
		albumInput.setDisable(false);
		edit.setDisable(true);
		del.setDisable(true);
		cancel.setDisable(false);
		cancel.setVisible(true);
		add.setText("Confirm");
	};
	public void removeItem(ActionEvent e){
		edit.setDisable(true);
		add.setDisable(true);
		cancel.setDisable(false);
		cancel.setVisible(true);
		del.setText("Confirm");
		delConfirm.setText("Are you sure you want to delete?");
	};
	public void showItemEditDialog(ActionEvent e){
		nameInput.setDisable(false);
		artistInput.setDisable(false);
		yearInput.setDisable(false);
		albumInput.setDisable(false);
		add.setDisable(true);
		del.setDisable(true);
		cancel.setDisable(false);
		cancel.setVisible(true);
		edit.setText("Confirm");
	};
}
