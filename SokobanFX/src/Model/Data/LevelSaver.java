package Model.Data;

import java.io.IOException;
import java.io.OutputStream;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 * this interface defined saving level method
 */
public interface LevelSaver {
	/**
	 * save level
	 * @param out-output stream
	 * @param lev-input stream
	 * @throws IOException
	 */
	public void SaveLevel(OutputStream out,Level lev) throws IOException;

}
