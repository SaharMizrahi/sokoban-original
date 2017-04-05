package Model;

import java.util.Observable;

import Model.Data.Level;
/**implemets model interfce
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *
 */
public class MyModel extends Observable implements ModelInterface {


	private Level CurrentLevel;
	private boolean isDone=false; 
	private boolean isChanged=false;
	
	public boolean isChanged()
	{
		return isChanged;
	}
	public void setChanged(boolean isChanged)
	{
		this.isChanged = isChanged;
	}
	/**Update current level 
	 * @param lev out source level
	 */
	public void UpdateLevel(Level lev)
	{
		this.setCurrentLevel(lev);
		
		this.setChanged();
		
		
	}
	/**check if the level is finish
	 * 
	 * @return true/false
	 */
	public boolean isDone() {
		return isDone;
	}
	/**Sets the flag
	 * 
	 * @param isDone flag that refers if the level is done
	 */
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	/**
	 * default constructor
	 */
	public MyModel() {
		
		// TODO Auto-generated constructor stub
		
		CurrentLevel=new Level();
		
		
	}

	

	public Level getCurrentLevel() {
		return CurrentLevel;
	}
	
	public void setCurrentLevel(Level currentLevel) {
		CurrentLevel = currentLevel;
		this.setDone(CurrentLevel.checkIfFinish());
		this.setChanged();
		this.notifyObservers();
		
	}


}
