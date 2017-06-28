package Model.Data;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.OutputStream;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	this class save level into xml file
 */
public class MyXMLLevelSaver implements LevelSaver {

	/**
	 * implemented method
	 */
	@Override
	public void SaveLevel(OutputStream out, Level lev) throws IOException {
		XMLEncoder XE=new XMLEncoder(out);
		XE.writeObject(lev);
		lev.XMLSaving(XE);
		XE.close();
	}

}

