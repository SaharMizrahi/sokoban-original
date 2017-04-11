package Controler;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Controller.Server.MyServer;
import Model.ModelInterface;
import view.ViewInterface;


/**This is THE Controller of our sokoban game,
 * Variables:
 * View,Model,Controller(of command queue),Commands Factory,Server and boolean flag
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *
 */
public class SokobanController implements Observer {
	
	private ViewInterface mv;
	private ModelInterface MM;
	private Controller controller;
	private CommandsFactory cF;
	private MyServer ms;
	private boolean isFinish=false;
	
	
	
			/**
	 * 
	 * @return the server
	 */
	public MyServer getMs()
	{
		
		return ms;
	}
	/**sets the server
	 * 
	 * @param ms out source server
	 */
	public void setMs(MyServer ms)
	{
		this.ms = ms;
	}
	/**
	 * Starts the Server thread
	 */
	public void runServer()
	{
		try {
			ms.runServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**Saving the level befor exit
	 * 
	 * @param fileName that the saved file will be named
	 */
	public void saveProtocol(String fileName)
	{
		this.runUserCommand("save "+fileName);
		this.closeAll();
	}
	/**
	 * Close all threads and exit the program
	 */
	public void closeAll()
	{
		this.controller.finishAllCommands();
		this.ms.closeAllSockets();

		
	}
	/**After get a command from the GUI/Server this method initlized the command and push it to the queue
	 * 
	 * @param cmd It's the String that reference the command
	 * @return true/false if this is an actual command
	 */
		public boolean runUserCommand(String cmd)
	{
		CommandCreator cC;
		FunctionalCommand command;
		
		String[]s=cmd.split(" ");
		command=cF.getCM().get(s[0]).create();

		if(command!=null)
		{
			command.setStr(cmd);
			command.setmM(this.getMM());
			if(s[0].toLowerCase().compareTo("exit")==0)
			{
				try {
					command.execute();
					this.closeAll();
					System.exit(0);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if(s[0].toLowerCase().compareTo("load")==0)
			{
				this.isFinish=false;

				
			}
			else if (s[0].toLowerCase().compareTo("move")==0)
			{
		
				this.getMv().setDirection(s[1]);
			}
			if(!this.isFinish)
			{
			

			this.controller.getmQ().add(command);
			return true;
			}
			else if (s[0].toLowerCase().compareTo("record")==0)
			{
				try {
					command.execute();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
			
			
		}
		else
		{
		System.out.println("wrong command");
		return false;
		}
		return false;

		


	}
	/**
	 * After one observable did notifyObservers() this method check which observable has changed, and act in a specific way 
	 */
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub		
		boolean ifHappend=false;
	
			if (arg0==ms.getCh())
			{
		
					ifHappend=this.runUserCommand(arg1.toString());
					this.getMv().setDone(this.MM.getCurrentLevel().checkIfFinish());
	
					if(ifHappend)
					{
						if(arg1.toString().toLowerCase().compareTo("display")==0)
						{
							this.ms.getCh().setMsgToUser(""+mv.getArrByString());
	
						}
						else
							this.ms.getCh().setMsgToUser("succsses");
					}
					else
					{
						this.ms.getCh().setMsgToUser("fail");
	
					}
				
			
			}
			if (arg0==MM)
			{
				this.isFinish=MM.getCurrentLevel().checkIfFinish();
				mv.setDone(isFinish);
				mv.setArr((MM.getCurrentLevel().toCharArray()));
				if (MM.isChanged())
				{
					mv.setSteps(mv.getSteps()+1);
					MM.setChanged(false);
				}
				ms.getCh().setIfHappend(true);
			}
			else if (arg0==mv)
			{			
				this.runUserCommand(arg1.toString());
				
				
			}
		
		

	}
	/**
	 * 
	 * @return the queue controller
	 */
	public Controller getController() {
		return controller;
	}
	/**Sets queue
	 * 
	 * @param c out source Controller
	 * 
	 */
	public void setController(Controller c) {
		controller = c;
	}
	/**
	 * 
	 * @return the Command Factory
	 */
	public CommandsFactory getcF() {
		return cF;
	}
	/** Sets the Command Factory
	 * 
	 * @param cF out source Command Factory
	 */
	public void setcF(CommandsFactory cF) {
		this.cF = cF;
	}
	/** Sets the Model
	 * 
	 * @param mM out source Model
	 */
	public void setMM(ModelInterface mM) {
		MM = mM;
		this.controller.getLastCommitedCommand().setmM(mM);
	}

	/**
	 * 
	 * @return the View
	 */
	public ViewInterface getMv() {
		return mv;
	}
	/**Sets the View
	 * 
	 * @param mV out Source View
	 */
	public void setMv(ViewInterface mV) {
		mv = mV;
	}
	/**
	 * 
	 * @return the Model
	 */
	public ModelInterface getMM() {
		return MM;
	}
	/**
	 * Initialize all variables, and start the Controller thread
	 */
	public SokobanController() {
		// TODO Auto-generated constructor stub

	
		
		
		this.controller=new Controller();
		
		this.cF=new CommandsFactory();
		
		this.ms=new MyServer();
		
		ms.getCh().addObserver(this);
		this.controller.start();
	}
	/**Initialize Model and View from out sources, and start the Controller thread
	 * 
	 * @param mV out source View
	 * @param mM out source Model
	 */
	public SokobanController(ViewInterface  mV, ModelInterface mM) {
		super();
		
		mv=  mV;
		MM =  mM;
		this.controller=new Controller();
		this.cF=new CommandsFactory();
		this.controller.start();
	}
	

	
	

}
