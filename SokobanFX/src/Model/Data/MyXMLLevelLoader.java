package Model.Data;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;


public class MyXMLLevelLoader implements LevelLoader {

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
