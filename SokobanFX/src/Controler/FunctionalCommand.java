package Controler;

import java.io.FileNotFoundException;
import java.io.IOException;

import Model.ModelInterface;
import Model.MyModel;
import Model.Data.Level;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	This is our Functional command class, this class is for holding the model and string command for all the class that extends functional command
 */
public class FunctionalCommand implements Command {
	private ModelInterface mM;
	
	private String str;

	/**
	 * default constructor
	 */
	public FunctionalCommand() {
		super();
		// TODO Auto-generated constructor stub
		mM=new MyModel();
		
	}
	/**
	 *  constructor
	 * @param lev-level to be set
	 */
	public FunctionalCommand(Level lev) {
		super();
		// TODO Auto-generated constructor stub
		this.mM=new MyModel();
		str="";

		this.setLev(lev);
		
	}
	/**
	 * 
	 * @param fc update the last committed command
	 */
	public void updateCommand(FunctionalCommand fc)
	{
		
		this.mM.setCurrentLevel(fc.mM.getCurrentLevel());
	}
	
	/**
 	* implemented method
 */

	@Override
	public void execute() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
		// TODO Auto-generated method stub

	}

	
	/*********************************/
	/*****getters and setters*********/
	/*********************************/
	/**
	 * 
	 * @return The model
	 */
	public ModelInterface getmM() {
		return mM;
	}
	/**
	 * set new model
	 * @param mM-new model
	 */
	public void setmM(ModelInterface mM) {
		this.mM = mM;
		this.setLev(mM.getCurrentLevel());
	}
	/**
	 * 
	 * @return the string that define the command
	 */
	public String getStr() {
		return str;
	}
	/**
	 * update the string command
	 * @param str-new command
	 */
	public void setStr(String str) {
		this.str = str;
	}
	/**
	 * 
	 * @return the level
	 */
	public Level getLev() {
		return mM.getCurrentLevel();
	}
	/**
	 * set new level
	 * @param lev-new level
	 */
	public void setLev(Level lev) {
		mM.setCurrentLevel(lev);
		
	}



}
