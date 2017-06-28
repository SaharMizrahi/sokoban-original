package Model.Data;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	this class save level into object level
 */
public class MyObjectLevelSaver implements LevelSaver,Serializable {
	/**
	 * implemented method
	 */
	@Override
	public void SaveLevel(OutputStream out, Level lev) throws IOException {
		// TODO Auto-generated method stub
		
		
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(lev);
		
		
		
		
	}
}