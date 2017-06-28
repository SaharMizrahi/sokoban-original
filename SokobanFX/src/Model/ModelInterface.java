package Model;

import Model.Data.Level;
/** 
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *our sokoban model interface
 */
public interface ModelInterface {
	/**
	 * 
	 * @return the current level
	 */
	public Level getCurrentLevel();
	/**Sets the current level
	 * 
	 * @param lev out soiurce level
	 */
	public void setCurrentLevel(Level lev);
	/**
	 * 
	 * @return true if the model has changed
	 */
	public boolean isChanged();
	/**
	 * update the change flag
	 * @param isChanged-new boolean 
	 */
	public void setChanged(boolean isChanged);
	/**
	 * solve our sokoban level
	 */
	public void solveLevel();
}
