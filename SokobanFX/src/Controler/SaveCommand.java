package Controler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import Model.Data.Level;
import Model.Data.LevelSaver;
import Model.Data.LevelSaverCreator;
import Model.Data.LevelSaverFactory;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	this command save a level to file (txt/obj/xml)
 */
public class SaveCommand extends FunctionalCommand implements Command {

	/**
	 * constructor using level parameter
	 * @param lev
	 */

	public SaveCommand(Level lev) {
		super(lev);
		// TODO Auto-generated constructor stub
	}
	/**
	
	 * implemented method
	 */
	public void execute() throws Exception
	{
		String FileType;
		String []args=this.getStr().split(" ");
		Scanner sc=new Scanner(args[1]).useDelimiter("\\.");
		LevelSaver LS;
		LevelSaverFactory LSF=new LevelSaverFactory();
		LevelSaverCreator LSC;
		do {
			FileType=sc.next();
		}while(sc.hasNext());
		
		LSC=LSF.getLSHM().get(FileType);
		
		if (LSC!=null)
		{
			try
			{
				LS=LSC.creat();
				LS.SaveLevel(new FileOutputStream(new File("./resources/Levels/"+args[1])),this.getLev());
				System.out.println("save level sucsses...");
			}
			catch (IOException e)
			{
				
				System.out.println("caught IOException");
			}
		}
		else
			throw new Exception("your file type isn't legal..try txt/xml/obj");
		sc.close();
	}
}
