package Controler;

import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	Our command interface
 */
public interface Command {
	
	/**
	 * This method should execute the command functionality
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	public void execute() throws FileNotFoundException, IOException, ClassNotFoundException, Exception;

}
