package SokobanSolver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import Model.Data.Level2D;
import Model.Data.LevelLoaderCreator;
import Model.Data.LevelLoaderFactory;
import Model.Data.LevelSaverFactory;

public class Solve
{

	public static void main(String[] args) 
	{
		Level2D level=null;
	
			LevelLoaderFactory llf=new LevelLoaderFactory();
			LevelSaverFactory lsf=new LevelSaverFactory();
			LevelLoaderCreator llc=null;
			
			String arg0Suffix="",arg1Suffix="";
			String []arr;
			arg0Suffix=args[0].substring(args[0].length()-3, args[0].length());
			try {
				System.out.println("loading file : "+args[0]+"...");
				level=(Level2D) llf.getLLHM().get(arg0Suffix).create().loadLevel(new FileInputStream(new File(args[0])));
				SokobanSolver sokobanSolver=new SokobanSolver();
				Writer writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(args[1]))));
				System.out.println("opening file : "+args[1]+"...");
				System.out.println("writing into file");

				writer.write(sokobanSolver.solveLevel(level).toString());
				System.out.println("closing...");

				writer.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			

	


	
		
		
		



	}

}
