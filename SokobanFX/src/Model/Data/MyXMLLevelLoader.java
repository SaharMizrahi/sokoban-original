package Model.Data;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	this class load level from xml file
 */
public class MyXMLLevelLoader implements LevelLoader {

	/**
	 * implemented method
	 */
	@Override
	public Level loadLevel(InputStream in) throws IOException {
		Level lev;
		XMLDecoder XD=new XMLDecoder(new BufferedInputStream(in));
		lev=(Level) XD.readObject();
		lev.XMLReading(XD);
		XD.close();
		return lev;
		
	}

}
