package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import db.DBManager;
import db.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DBWindowController  implements Initializable
{

	List<Record> resultList;
	DBManager dbm;
	Stage primarystage;
	Scene gameScene;
	@FXML
	TableView<Record> myTable;
	@FXML
	TableColumn<Record, String> usernameCol;
	@FXML
	TableColumn<Record, Integer> levelIDCol;
	@FXML
	TableColumn<Record, Integer> stepsCol;
	@FXML
	TableColumn<Record, Integer> timeCol;
	ObservableList<Record> tableData;
	
	
	public void updateTable()
	{
		myTable.getColumns().clear();
		myTable.getItems().clear();
		resultList = dbm.showAllRecords();
		for (int i = 0; i < resultList.size(); i++)
			tableData.add(resultList.get(i));

		myTable.setItems(tableData);

		myTable.getColumns().add(levelIDCol);
		myTable.getColumns().add(usernameCol);
		myTable.getColumns().add(timeCol);
		myTable.getColumns().add(stepsCol);

		levelIDCol.setCellValueFactory(new PropertyValueFactory("levelID"));
		usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
		timeCol.setCellValueFactory(new PropertyValueFactory("time"));
		stepsCol.setCellValueFactory(new PropertyValueFactory("steps"));

	}
	@FXML
	public void openGameScene()
	{
		if (primarystage != null) {

			if (gameScene != null) {
				primarystage.setScene(gameScene);
				primarystage.show();
			}
		}
	}

	@FXML
	public void openDialog()
	{
		
		Alert searchType = new Alert(AlertType.CONFIRMATION);
		searchType.setTitle("Sokoban DataBase Search:");
		searchType.setHeaderText("Choose Your Search Argument Option");
		searchType.setContentText("Username / Level ID");
		ButtonType ByName = new ButtonType("username");
		ButtonType ByLevel = new ButtonType("levelID");
		ButtonType Cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		searchType.getButtonTypes().setAll(ByName, ByLevel, Cancel);
		Optional<ButtonType> searchResult = searchType.showAndWait();


		if (searchResult.get() != Cancel) {
			
			TextInputDialog argDialog = new TextInputDialog("");
			argDialog.setTitle("Sokoban DataBase Search:");
			argDialog.setHeaderText("Enter:");
			argDialog.setContentText("");
			Optional<String> argResult = argDialog.showAndWait();
			
			
			Alert sortType = new Alert(AlertType.CONFIRMATION);
			sortType.setTitle("Sokoban DataBase Search:");
			sortType.setHeaderText("Choose Your Sort Option");
			searchType.setContentText("");
			ButtonType ByTime = new ButtonType("time");
			ButtonType BySteps = new ButtonType("steps");
			searchType.getButtonTypes().setAll(ByTime, BySteps);
			Optional<ButtonType> sortResult = searchType.showAndWait();
			
			
			List<String> numberChoices = new ArrayList<>();
			numberChoices.add("5");
			numberChoices.add("10");
			numberChoices.add("20");
			ChoiceDialog<String> dialog = new ChoiceDialog<>("?", numberChoices);
			dialog.setTitle("Sokoban DataBase Search:");
			dialog.setHeaderText("How Many Records Do Tou Want To See?");
			dialog.setContentText("Choose The Amount:");
			Optional<String> numberResult = dialog.showAndWait();
			myTable.getItems().clear();
			
			resultList= dbm.showTop(searchResult.get().getText(), argResult.get(), sortResult.get().getText(), numberResult.get());
			for (int i = 0; i < resultList.size(); i++)
				tableData.add(resultList.get(i));
			myTable.getColumns().clear();
			myTable.setItems(tableData);
			myTable.getColumns().add(levelIDCol);
			myTable.getColumns().add(usernameCol);
			myTable.getColumns().add(timeCol);
			myTable.getColumns().add(stepsCol);

			levelIDCol.setCellValueFactory(new PropertyValueFactory("levelID"));
			usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
			timeCol.setCellValueFactory(new PropertyValueFactory("time"));
			stepsCol.setCellValueFactory(new PropertyValueFactory("steps"));



		}

	}

	public Stage getPrimarystage()
	{
		return primarystage;
	}

	public void setPrimarystage(Stage primarystage)
	{
		this.primarystage = primarystage;
	}

	public Scene getGameScene()
	{
		return gameScene;
	}

	public void setGameScene(Scene gameScene)
	{
		this.gameScene = gameScene;
	}

	public DBWindowController(TableView<Record> myTable, Stage primarystage, Scene gameScene) {
		super();
		this.myTable = myTable;
		this.primarystage = primarystage;
		this.gameScene = gameScene;

	}

	public DBWindowController() {
		super();
		// TODO Auto-generated constructor stub

	}

	public void cellMouseCLick(Record rec)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Search By Mouse Clicked");
		alert.setHeaderText("what do you prefer to see, level's records or user's records?");
		alert.setContentText("Choose your option.");

		ButtonType buttonTypeOne = new ButtonType("Level");
		ButtonType buttonTypeTwo = new ButtonType("User");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
		Optional<ButtonType> result = alert.showAndWait();

		myTable.getItems().clear();

		if (result.get() == buttonTypeOne)
		{
			resultList= dbm.showTop("levelID",""+rec.getLevelID() , "time", "10");

		}
		else if(result.get()==buttonTypeTwo)
		{
			resultList= dbm.showTop("username",rec.getUsername() , "time", "10");

		}
		else
		{
			alert.close();
		}
		for (int i = 0; i < resultList.size(); i++)
			tableData.add(resultList.get(i));
		myTable.getColumns().clear();
		myTable.setItems(tableData);
		myTable.getColumns().add(levelIDCol);
		myTable.getColumns().add(usernameCol);
		myTable.getColumns().add(timeCol);
		myTable.getColumns().add(stepsCol);

		levelIDCol.setCellValueFactory(new PropertyValueFactory("levelID"));
		usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
		timeCol.setCellValueFactory(new PropertyValueFactory("time"));
		stepsCol.setCellValueFactory(new PropertyValueFactory("steps"));


	}
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub


		dbm = new DBManager();
		tableData = FXCollections.observableArrayList();
		resultList = dbm.showAllRecords();
		for (int i = 0; i < resultList.size(); i++)
			tableData.add(resultList.get(i));
		myTable.getColumns().clear();
		myTable.setItems(tableData);

		myTable.getColumns().add(levelIDCol);
		myTable.getColumns().add(usernameCol);
		myTable.getColumns().add(timeCol);
		myTable.getColumns().add(stepsCol);

		levelIDCol.setCellValueFactory(new PropertyValueFactory("levelID"));
		usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
		timeCol.setCellValueFactory(new PropertyValueFactory("time"));
		stepsCol.setCellValueFactory(new PropertyValueFactory("steps"));

		myTable.setRowFactory( tv -> {
		    TableRow<Record> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Record rowData = row.getItem();
		            cellMouseCLick(rowData);
		        }
		    });
		    return row ;
		});


	}

}
