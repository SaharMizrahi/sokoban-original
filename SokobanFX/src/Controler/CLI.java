package Controler;

import java.util.Scanner;

import Model.Data.Level;

public class CLI {
	Level MyLevel;
	

	public  void run() throws Exception
	{
		while (true)
		{
			CommandCreator cc;
			FunctionalCommand FC;
			Scanner sc=new Scanner(System.in);
			String userCommand;
			String []args;
			CommandsFactory MCF=new CommandsFactory();
			System.out.println("MENU:\n"
					+ "-load FILENAME	"
					+ "display	"
					+ "-move DIRECTION	"
					+ "-save FILENAME	"
					+ "exit\n");
			userCommand=sc.nextLine();
			args=userCommand.split(" ");
			try{
				cc=MCF.getCM().get(args[0].toLowerCase());
				if (cc!=null)
				{
					FC=cc.create();
					FC.setStr(userCommand);
					FC.setLev(this.MyLevel);
					try{
						FC.execute();
						this.setMyLevel(FC.getLev());
						if (this.MyLevel.checkIfFinish())
						{
							System.out.println("****CONGRATULATION****\n you finish the game");
							
						}
					}
					catch (Exception e)
					{
						System.out.println(e+" try again");
					}
				}
				else
					throw new Exception("Undefined Command");
			}
			catch (NullPointerException npe)
			{
				System.out.println(npe+" invaild name or root, try again");
			}
			System.out.flush();
			sc.close();
			
		}
	

	}
	public Level getMyLevel() {
		return MyLevel;
	}
	public void setMyLevel(Level myLevel) {
		MyLevel = myLevel;
	}

	public CLI()
	{
		MyLevel=null;
	}
	

}
