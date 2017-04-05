package view;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
/**Our view in the sokoban game
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *
 */

public class MainWindowController extends Observable  implements Initializable,ViewInterface{
	@FXML	private SokobanDisplayer sd=new SokobanDisplayer();
	private String userCommand;
	private char[][]arr;
	int count;
	int steps;
	HashMap<String,String> hm;
	@FXML
	Text myText;
	Timer t;
	


	@FXML
	Text mySteps;
	StringProperty Counter;
	StringProperty stepCounter;
	



	public void setDone(boolean b)
	{
		this.sd.setDone(b);
		if (b)
			t.cancel();
	}
	public String getArrByString()
	{
		if (arr!=null)
		{
			String s="";
			for (int i=0;i<arr.length;i++)
			{
				for(int j=0;j<arr[0].length;j++)
					s+=(arr[i][j]);
				s+="\n";
			}
			return s;
		}
		return "level is empty";
	}
	public void closeAllThreads()
	{
		
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
	 * read the keys from the xml file.. the user can change it by changing the xml file from outside
	 */
	public void readKeysFromXML()
	{
		XMLDecoder xd;
		try {
			xd = new XMLDecoder(new FileInputStream(new File("./resources/keys.xml")));
			hm.put((String) xd.readObject(), "move up");
			hm.put( (String) xd.readObject(), "move down");
			hm.put( (String) xd.readObject(), "move right");
			hm.put( (String) xd.readObject(), "move left");

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
			XMLEncoder xe=new XMLEncoder(new FileOutputStream(new File("./resources/Defaultkeys.xml")));
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
	public int getSteps() {
		return steps;
	}

	/**
	 * set the focus
	 */
	public void setFocus()
	{
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sd.requestFocus();
				
			}
		});
		t.start();
	}
	
	public void setSteps(int steps) {
		this.steps = steps;
		stepCounter.set(""+steps);

	}
	/**
	 * start the seconds counter
	 */
	public void startCounter()
	{
	
		setCount(0);
		t=new  Timer();
		t.scheduleAtFixedRate(new TimerTask() {
					
					@Override
					public void run() {
						setCount(getCount()+1);
					}
				}, 0, 1000);
		if (sd.isDone())
		{
			t.cancel();
		}
	}
	/**
	 * initalized all variables
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		t=new Timer();
		this.setFocus();
		
		hm=new HashMap<String,String>();
		this.readKeysFromXML();
		
		Counter=new SimpleStringProperty();
		stepCounter=new SimpleStringProperty();
		
		
		this.setSteps(0);
		this.setCount(0);
		myText.textProperty().bind(Counter);
		mySteps.textProperty().bind(stepCounter);
		
		sd.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->sd.setFocusTraversable(true));
		//this.setFocus();
		sd.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
		
				
				if(arr!=null)
				{
				
					setUserCommand(hm.get(""+arg0.getCode()));
				}




			}
		});
		sd.setLevelData(arr);
		
		this.setChanged();
		this.notifyObservers();
		//SC.setMWC(this);




	}
	/**
	 * 
	 * @return the seconds count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * 
	 * @param count update the second count
	 */
	public void setCount(int count) {
		this.count = count;
		this.Counter.set(""+count);
	}
	
	public String getUserCommand() {
		return userCommand;
	}
	/**update the user command
	 * 
	 * @param userCommand the new user command
	 */
	public void setUserCommand(String userCommand) {
		this.userCommand = userCommand;
		this.setChanged();
		this.notifyObservers(userCommand);
	}
	/**
	 * exit the program by clicking on 'exit' button
	 */
	public void exit()
	{
		this.setUserCommand("exit no");
	}
	/**
	 * saving the level
	 */
	public void saveFileMethod()
	{
		if (arr!=null)
		{
			FileChooser fc=new FileChooser();
			fc.setInitialDirectory(new File("./resources/Levels"));
			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text doc(*.txt)", "*.txt"));
			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML doc(*.xml)", "*.xml"));
			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Object file(*.obj)", "*.obj"));
	
			File chosen=fc.showSaveDialog(null);
			if (chosen!=null)
			{
				sd.requestFocus();
				String fileName=chosen.getName();
				this.setUserCommand("save "+fileName);
			}
		}
		

	}
	/**
	 * loading new level
	 */
	public void loadFileMethod()
	{

	 FileChooser fc = new FileChooser();

	 fc.setTitle("open sokoban level file:");
	 fc.setInitialDirectory(new File("./resources/Levels"));
	 File chosen  = fc.showOpenDialog(null);
	 if (chosen!=null)
	 {
		String fileName= chosen.getName();
		this.setUserCommand("load "+fileName);
		
		startCounter();
		 this.steps=0;


	 }

	}
	/**
	 * .
	 * @return the displayer of our level
	 */
	public SokobanDisplayer getSd() {
		return sd;
	}
	/**
	 * 
	 * @param sd update the displayer
	 */
	public void setSd(SokobanDisplayer sd) {
		this.sd = sd;
	}
	/**
	 * 
	 * @return our level in char array
	 */
	public char[][] getArr() {
		if (arr!=null)
			return arr;
		return null;
	}

	public void setArr(char[][] arr) {
		
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

