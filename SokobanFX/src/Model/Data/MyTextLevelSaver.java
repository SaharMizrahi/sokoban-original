package Model.Data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 * this class save level into text file
 */
public class MyTextLevelSaver implements LevelSaver {

	/**
	 * default constructor
	 */
	public MyTextLevelSaver() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * implemented method
	 */
	public void SaveLevel(OutputStream out,Level lev) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedWriter BW=new BufferedWriter(new OutputStreamWriter(out));
		lev.writeToBuffer(BW);
		BW.close();
		
		
	}

}
