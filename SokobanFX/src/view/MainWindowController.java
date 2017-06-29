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
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
import javafx.scene.input.KeyCode;
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
	private SokobanDisplayer sd = new SokobanDisplayer(400, 400);
	private String userCommand;
	private HashMap<String, String> movesHm;
	private Scene dbScene;
	private Stage primarystage;
	private DBWindowController dbwc;
	private int numOfClues=0;
	/******** Time & Steps *******/
	private StringProperty stepCounter;
	@FXML
	private Text mySteps;
	private int steps;
	private int count;
	private Timer time;
	private StringProperty timeCounter;
	@FXML
	private Text myTime;
	/********* Level Info ********/
	private String levelName;
	private LinkedList<String> solution;
	private char[][] arr;
	private int levelID;
	/********* Buttons ***********/
	@FXML
	private Button saveRecButton = new Button();
	@FXML
	private Button solveButton = new Button();
	@FXML
	private Button restartButton = new Button();
	@FXML
	private Button showSolutionButton = new Button();
	@FXML
	private Button clueButton=new Button();
	
	/**************************************************/
	/**************************************************/
	/************** functinal methods *******************/
	/**************************************************/
	/**************************************************/
	@FXML
	private void showClue()
	{
		
		numOfClues++;
		if(numOfClues<=solution.size())
		{
			
			this.setUserCommand("load " + levelName);
			this.setSteps(0);
			Thread t=new Thread(new Runnable()
			{
				
				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					for(int i=0;i<numOfClues;i++)
					{
						try {
							TimeUnit.MILLISECONDS.sleep(500);;
							setUserCommand(solution.get(i));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			t.start();
		}
	}
	
	@FXML
	public void showFullSolution()
	{
		if(this.solution!=null)
		{
			Thread t=new Thread(new Runnable()
			{
				
				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					for(int i=0;i<solution.size();i++)
					{
						try {
							TimeUnit.MILLISECONDS.sleep(500);;
							setUserCommand(solution.get(i));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			t.start();
		}
	}
	public void openDBStage()
	{
		saveRecButton.setDisable(true);
		DBManager dbm = new DBManager();
		TextInputDialog usernameDialog = new TextInputDialog("USERNAME");
		usernameDialog.setTitle("Saving Record Dialog");
		usernameDialog.setHeaderText("Welcome To Our Sokoban DataBase:");
		usernameDialog.setContentText("Please enter your name:");

		// Traditional way to get the response value.
		Optional<String> result = usernameDialog.showAndWait();
		if (!dbm.isMemberExist(result.get())) {
			dbm.addRecord(new Member(result.get()));
			dbm.addRecord(new Record(new RecordsKey(result.get(), levelID), count, steps));
		} else {
			if (dbm.isRecordExist(result.get(), levelID)) {
				Record rec = (Record) dbm.findRecord(result.get(), levelID);
				if ((rec.getSteps() > steps) || ((rec.getSteps() == steps) && (rec.getTime() > count))) {
					dbm.deleteRecord(rec);

					dbm.addRecord(new Record(new RecordsKey(result.get(), levelID), count, steps));
				} else {
					System.out.println("You have an old record that is better that your current, try again :)");
				}

			} else {
				dbm.addRecord(new Record(new RecordsKey(result.get(), levelID), count, steps));

			}
		}

		dbwc.updateTable();
		if (primarystage != null) {
			if (dbScene != null) {

				primarystage.setScene(dbScene);
				primarystage.show();

			}
		}
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
		movesHm.put("UP", "move up");
		movesHm.put("DOWN", "move down");
		movesHm.put("LEFT", "move left");
		movesHm.put("RIGHT", "move right");

	}

	/**
	 * set the keys to W S D A
	 */
	public void initializeLettersKeys()
	{
		movesHm.put("W", "move up");
		movesHm.put("S", "move down");
		movesHm.put("A", "move left");
		movesHm.put("D", "move right");
	}

	/**
	 * set the keys to 8 6 4 2
	 */
	public void initializeNumbersKeys()
	{
		movesHm.put("8", "move up");
		movesHm.put("2", "move down");
		movesHm.put("4", "move left");
		movesHm.put("6", "move right");
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
			movesHm.put((String) xd.readObject(), "move up");
			movesHm.put((String) xd.readObject(), "move down");
			movesHm.put((String) xd.readObject(), "move right");
			movesHm.put((String) xd.readObject(), "move left");

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

	/**
	 * start the seconds counter
	 */
	public void startTimeCounter()
	{

		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask()
		{

			@Override
			public void run()
			{
				if(!sd.isDone())
				setCount(getCount() + 1);
			}
		}, 0, 1000);
		if (sd.isDone()) {
			time.cancel();
		}

	}

	public void startStepsCounter()
	{
		this.setSteps(0);
	}

	public void moveCommandAction(KeyCode code)
	{
		if (movesHm != null) {
			String str = movesHm.get("" + code);
			if (str != null) {
				this.restartButton.setDisable(false);
				if (this.steps == 0)// this is the first move
				{
					startTimeCounter();
				}
				setUserCommand(str);
			}
		}

	}

	/**
	 * initalized all variables
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub

		/**** Initialize buttons disability ****/
		this.solveButton.setDisable(true);
		this.restartButton.setDisable(true);
		this.saveRecButton.setDisable(true);
		this.showSolutionButton.setDisable(true);
		/**************************************/
		/*** setting focus only on the SokobanDisplayer ***/
		this.restartButton.setFocusTraversable(false);
		this.saveRecButton.setFocusTraversable(false);
		this.solveButton.setFocusTraversable(false);
		this.showSolutionButton.setFocusTraversable(false);
		this.clueButton.setFocusTraversable(false);
		/*************************************************/
		/*************** initialize time and steps **********/
		timeCounter = new SimpleStringProperty();
		stepCounter = new SimpleStringProperty();
		time = new Timer();
		this.setSteps(0);
		this.setCount(0);
		mySteps.textProperty().bind(stepCounter);
		myTime.textProperty().bind(timeCounter);

		/**************************************************/
		/********** Initlize moves **************************/
		movesHm = new HashMap<String, String>();
		this.readKeysFromXML();
		sd.addEventFilter(KeyEvent.KEY_PRESSED, (e) -> {
			if (levelName != null) {
				if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.LEFT
						|| e.getCode() == KeyCode.RIGHT) {
					moveCommandAction(e.getCode());
				}

			}
		});
		/**********************************************/

		sd.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> sd.setFocusTraversable(true));

		sd.setLevelData(arr);

	}

	/**
	 * exit the program by clicking on 'exit' button
	 */
	@FXML
	public void exit()
	{
		this.setUserCommand("exit");
	}

	/**
	 * saving the level
	 */
	@FXML
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
	@FXML
	public void loadFileMethod()
	{
		numOfClues=0;
		time.cancel();

		FileChooser fc = new FileChooser();

		fc.setTitle("open sokoban level file:");
		fc.setInitialDirectory(new File("./resources/Levels"));

		File chosen = fc.showOpenDialog(null);
		if (chosen != null) {
			String fileName = chosen.getName();
			this.levelID = Integer.parseInt("" + fileName.charAt(0));
			this.setUserCommand("load " + fileName);
			this.levelName = fileName;
			startStepsCounter();
			this.setCount(0);

		}
		this.saveRecButton.setDisable(true);
		this.solveButton.setDisable(false);
		this.restartButton.setDisable(true);
		this.showSolutionButton.setDisable(true);
		this.clueButton.setDisable(true);


	}

	@Override
	public void closeAllThreads()
	{
		// TODO Auto-generated method stub
		this.setUserCommand("exit");
	}

	public void solveLevel()
	{
		this.setUserCommand("solve");
	}

	@FXML
	public void restartLevel()
	{
		numOfClues=0;
		setCount(0);
		startStepsCounter();
		time.cancel();
		this.setUserCommand("load " + levelName);

		this.restartButton.setDisable(true);
		this.showSolutionButton.setDisable(true);
		this.saveRecButton.setDisable(true);
		this.solveButton.setDisable(false);
		this.clueButton.setDisable(true);

	}

	/**************************************************/
	/**************************************************/
	/************** getters and setters *****************/
	/**************************************************/
	/**************************************************/

	public void setSolution(String otherSolution)
	{
		this.solution = new LinkedList<>();
		char current;
		int num = 0;
		String str = "";
		if(otherSolution!=null)
		{
		if (otherSolution.compareTo("block") != 0) 
		{
			String s[] = otherSolution.split("P");
			for (int i = 0; i < s.length; i++) {
				current = s[i].charAt(0);
				num = Integer.parseInt(s[i].substring(1, s[i].length()));
				switch (current) {
				case 'u':
					str = "up";
					break;
				case 'd':
					str = "down";
					break;
				case 'r':
					str = "right";
					break;
				case 'l':
					str = "left";
					break;
				}
				for (int j = 0; j < num; j++)
					this.solution.addLast("move " + str);
			}
			System.out.println(solution);
			this.showSolutionButton.setDisable(false);
			this.clueButton.setDisable(false);
		}
	}

	}

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

	public HashMap<String, String> getMovesHm()
	{
		return movesHm;
	}

	public void setMovesHm(HashMap<String, String> hm)
	{
		this.movesHm = hm;
	}

	public Timer getTime()
	{
		return time;
	}

	public void setTime(Timer time)
	{
		this.time = time;
	}

	public StringProperty getTimeCounter()
	{
		return timeCounter;
	}

	public void setTimeCounter(StringProperty counter)
	{
		timeCounter = counter;
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

	public void setDone(boolean b)
	{
		this.sd.setDone(b);
		if (b) {
			time.cancel();

			saveRecButton.setDisable(false);
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

	public void setSteps(int steps)
	{

		this.steps = steps;
		stepCounter.set("" + steps);
	}

	/**
	 * 
	 * @return the seconds count
	 */
	public int getCount()
	{
		return count;
	}

	/**
	 * 
	 * @param count
	 *            update the seconds count
	 */
	public void setCount(int count)
	{
		this.count = count;
		this.timeCounter.set("" + count);
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

}
