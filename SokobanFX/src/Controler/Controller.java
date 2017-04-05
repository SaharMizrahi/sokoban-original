package Controler;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
/**This class implements the controller interface and holding:
 *  queue of commands
 *  refernce of the current command that we polled
 *  boolean variable that stops the thread
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *
 */
public class Controller implements ControlerInterface {
	
	private ArrayBlockingQueue<FunctionalCommand> mQ;
	private FunctionalCommand lastCommitedCommand;
	
	private boolean isStopped;
	/** Enter new command to the queue
	 * 
	 * @param cmd : is the new command that we enter to the queue
	 */
	public void insertNewCommand(FunctionalCommand cmd)
	{
		mQ.add(cmd);
	}
	/**This is the Controller contructor
	 * initialize the queue,the boolean variable and the command
	 * 
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
		mQ=new ArrayBlockingQueue<FunctionalCommand>(10);
		this.isStopped=false;
		this.lastCommitedCommand=new FunctionalCommand();
		
	}

/**
 * Return the first command in the queue
 * @return Functional Command
 */
	public Command getCmd() {
		return mQ.poll();
	}
	/**This is the Controller constructor that gets blocking queue and set the specific queue to this refernce
	 * 
	 * @param mQ - the blocking queue that this constructor gets from out source
	 */
	public Controller(ArrayBlockingQueue<FunctionalCommand> mQ) {
		super();
		this.mQ = mQ;
		this.isStopped=false;

	}
	/**
	 * 
	 * @return Téhe Blocking queue 
	 */
	public ArrayBlockingQueue<FunctionalCommand> getmQ() {
		return mQ;
	}
	/**set the blocking queue
	 * 
	 * @param mQ out source of blocking queue 
	 */
	public void setmQ(ArrayBlockingQueue<FunctionalCommand> mQ) {
		this.mQ = mQ;
	}
	/**After stopping the thread of the queue, this method execute all the commands that left in the queue 
 * 	
 */
	public void finishAllCommands()
	{
		while(!this.mQ.isEmpty())
		{
			FunctionalCommand command;
			try {
				command = mQ.poll(1, TimeUnit.SECONDS);
				if(command!=null)
				{
					try {
						if(lastCommitedCommand!=null)
							command.updateCommand(lastCommitedCommand);
						command.execute();
						lastCommitedCommand.updateCommand(command);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	}
	/** This method start pooling single command in every 1 second and execute
	 * 
	 */
	@Override
	
	public void start() {
		// TODO Auto-generated method stub
		
			Thread t=new Thread (new Runnable()

			{

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(!isStopped)
					{
						try {
							FunctionalCommand command=mQ.poll(1, TimeUnit.SECONDS);
							if(command!=null)
							{
								try {
									if(lastCommitedCommand!=null)
										command.updateCommand(lastCommitedCommand);
									command.execute();
									lastCommitedCommand.updateCommand(command);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
				}
			});
				
				
			t.start();	
			
			
	
		
	}
	/**
	 * 
	 * @return The last command that executed
	 */
	public FunctionalCommand getLastCommitedCommand() {
		return lastCommitedCommand;
	}
	/**Update the lastCommitedCommand to a new command
	 * 
	 * @param lastCommitedcommand the last command that executed
	 */
	public void setLastCommitedcommand(FunctionalCommand lastCommitedcommand) {
		this.lastCommitedCommand = lastCommitedcommand;
	}
	/**This method stop the queue thread
	 * 
	 */
	@Override
	
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("controller stop");
		
		this.isStopped=true;
		this.finishAllCommands();
	}
	
	

}
