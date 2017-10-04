package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
	@FXML ListView<Song> listView;

	private ObservableList<Song> obsList;
	
	public String hello;

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
		//songList.list.add(song);
		//if you call sort, always call save immediately
		songList.sort();
		songList.save();
		/*
		for(int i = 0; i < songList.list.size(); i++) {
			System.out.println(songList.list.get(i).toString());
		}
		*/
		if (songList.list.size()==0) System.out.println("rip");
		/*obsList = FXCollections.emptyObservableList();
		for (int i=0; i<songList.list.size(); i++){
			Song temp= songList.list.get(i);
			String tempinfo= temp.name + " - " + temp.artist;
			obsList.add(tempinfo);
		}
		System.out.println(obsList.get(0));
		listView.setItems(obsList);
		listView.getSelectionModel().select(0);*/
	}

	public void start(Stage mainStage) {
	      // create an ObservableList
	      // from an ArrayList
		  SongList songList = new SongList();
		  //always initialize with a readFile. The readFile will create a SongFile if file is not found
		  songList.readFile();
		  for(int i = 0; i < songList.list.size(); i++) {
				System.out.println(songList.list.get(i).toString());
			}
	      obsList = FXCollections.observableArrayList(songList.list);
	      listView.setItems(obsList);
	      listView.getSelectionModel().select(0);

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
