package Model.Data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MyTextLevelSaver implements LevelSaver {

	public MyTextLevelSaver() {
		// TODO Auto-generated constructor stub
	}

	public void SaveLevel(OutputStream out,Level lev) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedWriter BW=new BufferedWriter(new OutputStreamWriter(out));
		lev.writeToBuffer(BW);
		BW.close();
		
		
	}

}
