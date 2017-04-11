package view;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import db.DBManager;
import db.Member;
import db.Record;
import db.RecordsKey;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Our view in the sokoban game
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *
 */

public class MainWindowController extends Observable implements Initializable, ViewInterface
{
	@FXML
	private SokobanDisplayer sd = new SokobanDisplayer();
	private String userCommand;
	private char[][] arr;
	private int count;
	private int steps;
	private HashMap<String, String> hm;
	@FXML
	private Text myText;
	private Timer time;
	@FXML
	private Text mySteps;
	private StringProperty Counter;
	private StringProperty stepCounter;
	private int levelID;
	private Scene dbScene;
	private Stage primarystage;
	private DBWindowController dbwc;
	@FXML

	private Button saveRecButton = new Button();
	

	public Scene getDbScene()
	{
		return dbScene;
	}

	public void setDbScene(Scene dbScene)
	{
		this.dbScene = dbScene;
	}

	public DBWindowController getDbwc()
	{
		return dbwc;
	}

	public void setDbwc(DBWindowController dbwc)
	{
		this.dbwc = dbwc;
	}

	public void openDBStage()
	{
		saveRecButton.setDisable(true);
		DBManager dbm=new DBManager();
		TextInputDialog usernameDialog = new TextInputDialog("USERNAME");
		usernameDialog.setTitle("Saving Record Dialog");
		usernameDialog.setHeaderText("Welcome To Our Sokoban DataBase:");
		usernameDialog.setContentText("Please enter your name:");

		// Traditional way to get the response value.
		Optional<String> result = usernameDialog.showAndWait();
		if(!dbm.isMemberExist(result.get()))
		{
			dbm.addRecord(new Member(result.get()));
			dbm.addRecord(new Record(new RecordsKey(result.get(), levelID), count, steps));
		}
		else
		{
			if(dbm.isRecordExist(result.get(), levelID))
			{
				Record rec=(Record)dbm.findRecord(result.get(), levelID);
				if((rec.getSteps()>steps)||((rec.getSteps()==steps)&&(rec.getTime()>count)))
				{
				dbm.deleteRecord(rec);

				dbm.addRecord(new Record(new RecordsKey(result.get(), levelID), count, steps));
				}
				else
				{
					System.out.println("You have an old record that is better that your current, try again :)");
				}

			}
			else
			{
				dbm.addRecord(new Record(new RecordsKey(result.get(), levelID), count, steps));

			}
		}
		

		dbwc.updateTable();
		if(primarystage!=null)
		{
			if(dbScene!=null)
			{
				
				primarystage.setScene(dbScene);
				primarystage.show();
				
			}
		}
	}
	
		public HashMap<String, String> getHm()
	{
		return hm;
	}

	public void setHm(HashMap<String, String> hm)
	{
		this.hm = hm;
	}

	public Text getMyText()
	{
		return myText;
	}

	public void setMyText(Text myText)
	{
		this.myText = myText;
	}

	public Timer getTime()
	{
		return time;
	}

	public void setTime(Timer time)
	{
		this.time = time;
	}

	public Text getMySteps()
	{
		return mySteps;
	}

	public void setMySteps(Text mySteps)
	{
		this.mySteps = mySteps;
	}

	public StringProperty getCounter()
	{
		return Counter;
	}

	public void setCounter(StringProperty counter)
	{
		Counter = counter;
	}

	public StringProperty getStepCounter()
	{
		return stepCounter;
	}

	public void setStepCounter(StringProperty stepCounter)
	{
		this.stepCounter = stepCounter;
	}

	public int getLevelID()
	{
		return levelID;
	}

	public void setLevelID(int levelID)
	{
		this.levelID = levelID;
	}

	public Scene getDBScene()
	{
		return dbScene;
	}

	public void setDBScene(Scene dbScene)
	{
		this.dbScene = dbScene;
	}

	public Stage getPrimarystage()
	{
		return primarystage;
	}

	public void setPrimarystage(Stage primarystage)
	{
		this.primarystage = primarystage;
		this.primarystage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			
			@Override
			public void handle(WindowEvent event)
			{
				// TODO Auto-generated method stub
				closeAllThreads();
				
			}
		});
	
	}

	public Button getSaveRecButton()
	{
		return saveRecButton;
	}

	public void setSaveRecButton(Button saveRecButton)
	{
		this.saveRecButton = saveRecButton;
	}

	
	
	public void saveLevelRecord()
	{

		String record = "";
		String usernameInput = null;
		TextInputDialog tid = new TextInputDialog();
		tid.setTitle("Saving Record Dialog");
		tid.setHeaderText("Welcome to our Sokoban DataBase :-)");
		tid.setContentText("Enter you name");
		tid.showAndWait();
		usernameInput = tid.getResult();
		record = "record " + usernameInput + " " + levelID + " " + mySteps + " " + time;
		this.setUserCommand(record);
	}

	public void setDone(boolean b)
	{
		this.sd.setDone(b);
		if (b) {
			time.cancel();

			saveRecButton.setDisable(false);
		}
	}

	public String getArrByString()
	{
		if (arr != null) {
			String s = "";
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++)
					s += (arr[i][j]);
				s += "\n";
			}
			return s;
		}
		return "level is empty";
	}



	/**
	 * set the default keys(arrows)
	 */
	public void initializeDefaultKeys()
	{
		hm.put("UP", "move up");
		hm.put("DOWN", "move down");
		hm.put("LEFT", "move left");
		hm.put("RIGHT", "move right");

	}

	/**
	 * set the keys to W S D A
	 */
	public void initializeLettersKeys()
	{
		hm.put("W", "move up");
		hm.put("S", "move down");
		hm.put("A", "move left");
		hm.put("D", "move right");
	}

	/**
	 * set the keys to 8 6 4 2
	 */
	public void initializeNumbersKeys()
	{
		hm.put("8", "move up");
		hm.put("2", "move down");
		hm.put("4", "move left");
		hm.put("6", "move right");
	}

	/**
	 * read the keys from the xml file.. the user can change it by changing the
	 * xml file from outside
	 */
	public void readKeysFromXML()
	{
		XMLDecoder xd;
		try {
			xd = new XMLDecoder(new FileInputStream(new File("./resources/keys.xml")));
			hm.put((String) xd.readObject(), "move up");
			hm.put((String) xd.readObject(), "move down");
			hm.put((String) xd.readObject(), "move right");
			hm.put((String) xd.readObject(), "move left");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * write the default keys to xml file
	 */
	public void writeDefaultKeysToXML()
	{
		try {
			XMLEncoder xe = new XMLEncoder(new FileOutputStream(new File("./resources/Defaultkeys.xml")));
			xe.writeObject("UP");
			xe.writeObject("DOWN");
			xe.writeObject("RIGHT");
			xe.writeObject("LEFT");
			xe.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return the number of steps the has done
	 */
	public int getSteps()
	{
		return steps;
	}

	/**
	 * set the focus
	 */
	public void setFocus()
	{
		Thread t = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				sd.requestFocus();

			}
		});
		t.start();
	}

	public void setSteps(int steps)
	{
		this.steps = steps;
		stepCounter.set("" + steps);

	}

	/**
	 * start the seconds counter
	 */
	public void startCounter()
	{

		setCount(0);
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask()
		{

			@Override
			public void run()
			{
				setCount(getCount() + 1);
			}
		}, 0, 1000);
		if (sd.isDone()) {
			time.cancel();
		}
	}

	/**
	 * initalized all variables
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub
		time = new Timer();
		this.setFocus();

		hm = new HashMap<String, String>();
		this.readKeysFromXML();

		Counter = new SimpleStringProperty();
		stepCounter = new SimpleStringProperty();

		this.setSteps(0);
		this.setCount(0);
		myText.textProperty().bind(Counter);
		mySteps.textProperty().bind(stepCounter);

		sd.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> sd.setFocusTraversable(true));
		// this.setFocus();
		sd.setOnKeyPressed(new EventHandler<KeyEvent>()
		{

			@Override
			public void handle(KeyEvent arg0)
			{
				// TODO Auto-generated method stub

				if (arr != null) {

					setUserCommand(hm.get("" + arg0.getCode()));
				}

			}
		});
		sd.setLevelData(arr);

		this.setChanged();
		this.notifyObservers();


	}

	/**
	 * 
	 * @return the second count
	 */
	public int getCount()
	{
		return count;
	}

	/**
	 * 
	 * @param count
	 *            update the second count
	 */
	public void setCount(int count)
	{
		this.count = count;
		this.Counter.set("" + count);
	}

	public String getUserCommand()
	{
		return userCommand;
	}

	/**
	 * update the user command
	 * 
	 * @param userCommand
	 *            the new user command
	 */
	public void setUserCommand(String userCommand)
	{
		this.userCommand = userCommand;
		this.setChanged();
		this.notifyObservers(userCommand);
	}

	/**
	 * exit the program by clicking on 'exit' button
	 */
	public void exit()
	{
		this.setUserCommand("exit");
	}

	/**
	 * saving the level
	 */
	public void saveFileMethod()
	{
		List<String> choices = new ArrayList<>();
		choices.add("txt");
		choices.add("xml");
		choices.add("obj");

		ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
		dialog.setTitle("Saving File Dialog");
		dialog.setHeaderText("Saving File...");
		dialog.setContentText("Choose your file type:");
		dialog.showAndWait();
		this.setUserCommand("save " + this.levelID + "." + dialog.getSelectedItem());

	}

	/**
	 * loading new level
	 */
	public void loadFileMethod()
	{

		this.saveRecButton.setDisable(true);
		FileChooser fc = new FileChooser();

		fc.setTitle("open sokoban level file:");
		fc.setInitialDirectory(new File("./resources/Levels"));
		
		File chosen = fc.showOpenDialog(null);
		if (chosen != null) {
			String fileName = chosen.getName();
			this.levelID =Integer.parseInt(""+fileName.charAt(0));
			this.setUserCommand("load " + fileName);

			startCounter();
			this.steps = 0;

		}

	}

	/**
	 * .
	 * 
	 * @return the displayer of our level
	 */
	public SokobanDisplayer getSd()
	{
		return sd;
	}

	/**
	 * 
	 * @param sd
	 *            update the displayer
	 */
	public void setSd(SokobanDisplayer sd)
	{
		this.sd = sd;
	}

	/**
	 * 
	 * @return our level in char array
	 */
	public char[][] getArr()
	{
		if (arr != null)
			return arr;
		return null;
	}

	public void setArr(char[][] arr)
	{

		this.arr = arr;
		this.sd.setLevelData(arr);
	}

	@Override
	public void setDirection(String s)
	{
		// TODO Auto-generated method stub
		this.sd.setDirection(s);

	}

	@Override
	public String getDirection()
	{
		// TODO Auto-generated method stub
		return this.sd.getDirection();
	}

	@Override
	public void closeAllThreads()
	{
		// TODO Auto-generated method stub
		this.setUserCommand("exit");
	}

}
