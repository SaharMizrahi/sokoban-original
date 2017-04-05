package Model.Data;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.OutputStream;

public class MyXMLLevelSaver implements LevelSaver {

	@Override
	public void SaveLevel(OutputStream out, Level lev) throws IOException {
		XMLEncoder XE=new XMLEncoder(out);
		XE.writeObject(lev);
		lev.XMLSaving(XE);
		XE.close();
	}

}

