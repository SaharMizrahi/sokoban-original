package Controler;

import Model.Data.Level;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	This command print the level map
 */
public class DisplayLevelCommand extends FunctionalCommand{

	/**
	 * Constructor with level parameter to functional command  supper class
	 * @param lev-Sokoban level
	 */
	DisplayLevelCommand(Level lev) {
		super(lev);
		// TODO Auto-generated constructor stub
	}
	/**
	 * implemented method
	 */
	public void execute() {
		// TODO Auto-generated method stub
		this.getLev().print();

	}

}
