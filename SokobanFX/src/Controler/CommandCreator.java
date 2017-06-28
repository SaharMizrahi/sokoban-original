package Controler;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	This interface is our interface for creating new command
 */
public interface CommandCreator {
	/**
	 * This method implementation is to create new functional command
	 * @return new Functional command
	 */
	public FunctionalCommand create();

}
