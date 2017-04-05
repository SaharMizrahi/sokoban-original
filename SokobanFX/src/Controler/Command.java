package Controler;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Command {
	
	
	public void execute() throws FileNotFoundException, IOException, ClassNotFoundException, Exception;

}
