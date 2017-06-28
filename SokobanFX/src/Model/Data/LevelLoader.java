package Model.Data;


import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	this interface define a method that loading level
 */
public interface LevelLoader {
	/**
	 * 
	 * @param in-input stream
	 * @return level
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	Level loadLevel(InputStream in) throws IOException, ClassNotFoundException;
	
	
	
			
		


}
