//Peter Jeng && Jintao Hang
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SongLibraryController {
	@FXML Button add;
	@FXML Button addConfirm;
	@FXML Button del;
	@FXML Button deConfirm;
	@FXML Button edit;
	@FXML Button editConfirm;
	@FXML Button cancel;
	@FXML TextField nameInput;
	@FXML TextField artistInput;
	@FXML TextField yearInput;
	@FXML TextField albumInput;
	@FXML Text delConfirm;
	@FXML ListView<Song> listView;

	private ObservableList<Song> obsList;
	private SongList songList;

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
		  songList = new SongList();
		  //always initialize with a readFile. The readFile will create a SongFile if file is not found
		  songList.readFile();
	      obsList = FXCollections.observableArrayList(songList.list);
	      listView.setItems(obsList);
	      listView.getSelectionModel().select(0);
	      if (songList.list.size()!=0){
	      	nameInput.setText(songList.list.get(0).name);
	      	artistInput.setText(songList.list.get(0).artist);
	      	yearInput.setText(songList.list.get(0).year);
	      	albumInput.setText(songList.list.get(0).album);
	      }
	      listView
	        .getSelectionModel()
	        .selectedIndexProperty()
	        .addListener(
	           (obs, oldVal, newVal) ->
	               displayInfo());

	   }
	public void displayInfo(){
		int index = listView.getSelectionModel().getSelectedIndex();
		nameInput.setText(songList.list.get(index).name);
      	artistInput.setText(songList.list.get(index).artist);
      	yearInput.setText(songList.list.get(index).year);
      	albumInput.setText(songList.list.get(index).album);
      	/*Alert alert = new Alert(AlertType.INFORMATION);
      	alert.setTitle("Error");
      	alert.setContentText("Could not add song");

      	alert.showAndWait();*/
	}
	public void showItemInputDialog(ActionEvent e){
		nameInput.setDisable(false);
		artistInput.setDisable(false);
		yearInput.setDisable(false);
		albumInput.setDisable(false);
		nameInput.clear();
		artistInput.clear();
		yearInput.clear();
		albumInput.clear();
		edit.setDisable(true);
		del.setDisable(true);
		cancel.setDisable(false);
		cancel.setVisible(true);
		add.setDisable(true);
		addConfirm.setDisable(false);
		addConfirm.setVisible(true);

	};
	public void confirmItemInputDialog(ActionEvent e){
		Song song = new Song();
		song.name = nameInput.getText();
		song.album = albumInput.getText();
		song.artist = artistInput.getText();
		song.year = yearInput.getText();
		songList.list.add(song);
		songList.sort();
		songList.save();
		obsList.clear();
		obsList = FXCollections.observableArrayList(songList.list);
		listView.setItems(obsList);
		cancellation(e);

	};
	public void removeItem(ActionEvent e){
		edit.setDisable(true);
		add.setDisable(true);
		cancel.setDisable(false);
		cancel.setVisible(true);
		del.setDisable(true);
		deConfirm.setDisable(false);
		deConfirm.setVisible(true);
		delConfirm.setText("Are you sure you want to delete?");
	};
	public void removeItemConfirmed(ActionEvent e){
		//songList.list.remove(listView.getSelectionModel().selectedIndexProperty());
		int index = listView.getSelectionModel().getSelectedIndex();
		songList.list.remove(index);
		songList.sort();
		songList.save();
		obsList.clear();
		obsList = FXCollections.observableArrayList(songList.list);
		listView.setItems(obsList);
		cancellation(e);
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
		edit.setDisable(true);
		edit.setVisible(false);
		editConfirm.setDisable(false);
		editConfirm.setVisible(true);
	};
	public void cancellation(ActionEvent e){
		if (songList.list.size()!=0){
	      	nameInput.setText(songList.list.get(0).name);
	      	artistInput.setText(songList.list.get(0).artist);
	      	yearInput.setText(songList.list.get(0).year);
	      	albumInput.setText(songList.list.get(0).album);
	    }
		nameInput.setDisable(true);
		artistInput.setDisable(true);
		yearInput.setDisable(true);
		albumInput.setDisable(true);
		add.setDisable(false);
		del.setDisable(false);
		edit.setDisable(false);
		add.setVisible(true);
		del.setVisible(true);
		edit.setVisible(true);
		addConfirm.setDisable(true);
		deConfirm.setDisable(true);
		editConfirm.setDisable(true);
		addConfirm.setVisible(false);
		deConfirm.setVisible(false);
		editConfirm.setVisible(false);
		delConfirm.setText("");
		cancel.setDisable(true);
		cancel.setVisible(false);
	};
}
