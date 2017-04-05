package Model.Data;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
public class MyObjectLevelLoader  implements LevelLoader,Serializable{
	@Override
	public Level loadLevel(InputStream in) throws IOException, ClassNotFoundException {
		Level lev;
		ObjectInputStream ois = new ObjectInputStream(in);
		 lev= (Level)ois.readObject(); 
		ois.close();
		return lev;
	}
}