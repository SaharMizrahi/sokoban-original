package Controler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Model.Data.Level;

public class ExitCommand extends FunctionalCommand implements Command {

	public ExitCommand(Level lev) {
		super(lev);
		// TODO Auto-generated constructor stub
	}
	public void execute() throws FileNotFoundException, IOException, ClassNotFoundException, Exception
	{
		FunctionalCommand FC;
		Scanner sc=new Scanner(System.in);
		
		if (this.getStr()=="exit")
		{
			System.out.println("exitCMD check");
			System.out.println("befor you exit, do you want to save your level? Yes/No?");
			
			String s=sc.next();
			if (s=="yes")
			{
				FC=new SaveCommand(this.getLev());
				System.out.println("enter file in this form : NAME.TYPE");
				s=sc.next();
				FC.setStr("save "+s);
				FC.execute();
				System.out.println("Thank you for playing Sokoban,exit now from the game...");
				//System.exit(0);
			}
			else if (s=="no")
			{
				System.out.println("Thank you for playing Sokoban,exit now from the game...");
				//System.exit(0);
			}
			System.out.println("wrong answer,try again in the main menu...");
			sc.close();
		}
		//System.exit(0);
		
		
	}
	


}
