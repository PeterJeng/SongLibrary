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
	}
	public void showItemInputDialog(ActionEvent e){
		nameInput.setEditable(true);
		artistInput.setEditable(true);
		yearInput.setEditable(true);
		albumInput.setEditable(true);
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
		int attempt= songList.add(song);
		obsList.clear();
		obsList = FXCollections.observableArrayList(songList.list);
		listView.setItems(obsList);
		clearing(e);
		if (attempt==-1){
	      	Alert alert = new Alert(AlertType.INFORMATION);
	      	alert.setTitle("Error");
	      	alert.setContentText("Song already in list");
	      	alert.showAndWait();
		}
		else if (attempt==-2){
	      	Alert alert = new Alert(AlertType.INFORMATION);
	      	alert.setTitle("Error");
	      	alert.setContentText("Not enough information. Please provide Song name and Artist");
	      	alert.showAndWait();
		}
		else {
			listView.getSelectionModel().select(attempt);
		}

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
		int index = listView.getSelectionModel().getSelectedIndex();
		songList.list.remove(index);
		songList.sort();
		songList.save();
		obsList.clear();
		obsList = FXCollections.observableArrayList(songList.list);
		listView.setItems(obsList);
		if (songList.list.size()==0){
			nameInput.clear();
			artistInput.clear();
			yearInput.clear();
			albumInput.clear();
		}
		clearing(e);
		if (index<songList.list.size()){
			listView.getSelectionModel().select(index);
		}
		else if (index-1<songList.list.size()){
			listView.getSelectionModel().select(index-1);
		}
	};
	public void showItemEditDialog(ActionEvent e){
		nameInput.setEditable(true);
		artistInput.setEditable(true);
		yearInput.setEditable(true);
		albumInput.setEditable(true);
		add.setDisable(true);
		del.setDisable(true);
		cancel.setDisable(false);
		cancel.setVisible(true);
		edit.setDisable(true);
		edit.setVisible(false);
		editConfirm.setDisable(false);
		editConfirm.setVisible(true);
	};
	public void confirmItemEditDialog(ActionEvent e){
		Song song = new Song();
		song.name = nameInput.getText();
		song.album = albumInput.getText();
		song.artist = artistInput.getText();
		song.year = yearInput.getText();
		int index = listView.getSelectionModel().getSelectedIndex();
		int attempt= songList.edit(index, song);
		obsList.clear();
		obsList = FXCollections.observableArrayList(songList.list);
		listView.setItems(obsList);
		clearing(e);
		if (attempt==-1){
	      	Alert alert = new Alert(AlertType.INFORMATION);
	      	alert.setTitle("Error");
	      	alert.setContentText("No changes occured. Edit was same as original");
	      	alert.showAndWait();
	      	listView.getSelectionModel().select(index);
		}
		else if (attempt==-2){
			Alert alert = new Alert(AlertType.INFORMATION);
	      	alert.setTitle("Error");
	      	alert.setContentText("A song already exists with the same name and artist");
	      	alert.showAndWait();
	      	listView.getSelectionModel().select(index);
		}
		else if (attempt==-3){
			Alert alert = new Alert(AlertType.INFORMATION);
	      	alert.setTitle("Error");
	      	alert.setContentText("Not enough information. Please provide Song name and Artist");
	      	alert.showAndWait();
	      	listView.getSelectionModel().select(index);
		}
		else {
			listView.getSelectionModel().select(attempt);
		}

	};
	public void cancellation(ActionEvent e){
		int index = listView.getSelectionModel().getSelectedIndex();
		nameInput.setEditable(false);
		artistInput.setEditable(false);
		yearInput.setEditable(false);
		albumInput.setEditable(false);
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
		Song song= songList.list.get(index);
		nameInput.setText(song.name);
		artistInput.setText(song.artist);
		yearInput.setText(song.year);
		albumInput.setText(song.album);

	};
	public void clearing(ActionEvent e){
		nameInput.setEditable(false);
		artistInput.setEditable(false);
		yearInput.setEditable(false);
		albumInput.setEditable(false);
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
