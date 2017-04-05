package view;
/** Our view Interface in the sokoban game 
 * 
 * @author Sahar Mizrahi and Gal ezra
 *
 */
public interface ViewInterface {
	/**
	 * 
	 * @return the user command
	 */
	public void setDirection(String s);
	public String getDirection();
	public String getUserCommand();
	/**
	 * close all opened threads
	 */
	public void closeAllThreads();
	/**
	 * 
	 * @return out source level in chars array
	 */
	public String getArrByString();
	/**update the game step's count
	 * 
	 * @param step the new steps counter
	 */
	public void setSteps(int step);
	/**
	 * 
	 * @return the number of steps that made
	 */
	public int getSteps();
	/** update the current level
	 * 
	 * @param arrupodated level array
	 */
	
	public void setArr(char[][]arr);
	/** update if the game end
	 * 
	 * @param isDone true/ false
	 */
	public void setDone(boolean isDone);

}
