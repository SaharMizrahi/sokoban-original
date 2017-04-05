package Controler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import Model.Data.Level;
import Model.Data.LevelLoader;
import Model.Data.LevelLoaderFactory;

public class LoadLevelCommand extends FunctionalCommand implements Command {
	
	HashMap<String,LevelLoader> LLM;
	public LoadLevelCommand(Level lev) 
	{
		super(lev);
		// TODO Auto-generated constructor stub
	
	}

	public void execute() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		String FileType;
		String []args=this.getStr().split(" ");
		Scanner sc=new Scanner(args[1]).useDelimiter("\\.");
		
		LevelLoader LL;
		LevelLoaderFactory LLF=new LevelLoaderFactory();
		do {
			FileType=sc.next();
		}while(sc.hasNext());
		
		LL=LLF.getLLHM().get(FileType).create();
		try{
		this.setLev(LL.loadLevel(new FileInputStream("./resources/Levels/"+args[1])));
		}
		catch (IOException e)
		{
			System.out.println("caught IOException");
		}
		
		/*if (this.getLev()!=null)
			System.out.println("level upload succsess");
		else
			System.out.println("level upload failed");*/
		sc.close();
		
		
	}

}
