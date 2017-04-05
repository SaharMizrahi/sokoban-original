package Model.Data;

import java.io.IOException;
import java.io.OutputStream;

public interface LevelSaver {
	public void SaveLevel(OutputStream out,Level lev) throws IOException;

}
