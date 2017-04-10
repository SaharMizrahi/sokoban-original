package view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import db.DBManager;
import db.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DBWindowController implements Initializable
{
	
	List<Record> resultList;
	DBManager dbm=new DBManager();
	Stage primarystage;
	Scene gameScene;
	@FXML
	TableView<Record> myTable;
	@FXML
	TableColumn<Record, String> usernameCol;
	@FXML
	TableColumn<Record, Integer> stepsCol;
	@FXML
	TableColumn<Record, Integer> timeCol;
	ObservableList<Record> tableData;
	@FXML
	private Text levelID;
	@FXML
	private Text time;	
	@FXML
	private Text steps;
	
	@FXML
	public void exit()
	{
		
	}
	@FXML
	public void openGameScene()
	{
		if (primarystage!=null)
		{
			
			if(gameScene!=null)
			{
				primarystage.setScene(gameScene);
				primarystage.show();
			}
		}
	}
	@FXML
	public void func()
	{
		/*List<Record> list=dbm.showAllRecords();
		for(int i=1;i<list.size();i++)
		{
			tableData.add(list.get(i));
		}
		
		myTable.getColumns().clear();

		myTable.setItems(tableData);
		System.out.println("check123");*/
		


		
	}
	public void sortNRowsByTime()
	{
		List<Record> list=dbm.sortRecordsByTime(5);
		
		for(int i=1;i<list.size();i++)
		{
			tableData.add(list.get(i));
		}
		myTable.setItems(tableData);
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
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub
		dbm=new DBManager();
		tableData=FXCollections.observableArrayList();
		resultList=dbm.showTop(1,"time",5);
		for(int i=0;i<resultList.size();i++)
			tableData.add(resultList.get(i));
		myTable.getColumns().clear();
		myTable.setItems(tableData);
		

		myTable.getColumns().add(usernameCol);
		myTable.getColumns().add(timeCol);
		myTable.getColumns().add(stepsCol);



		usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
		timeCol.setCellValueFactory(new PropertyValueFactory("time"));
		stepsCol.setCellValueFactory(new PropertyValueFactory("steps"));
		

	}
	
	
	
}
