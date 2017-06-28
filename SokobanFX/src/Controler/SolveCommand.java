package Controler;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	this command solve the level using the model
 */
public class SolveCommand extends FunctionalCommand implements Command
{
	/**
	 * implemented method
	 */
	@Override
	public void execute()
	{
		this.getmM().solveLevel();
	}
	

}
