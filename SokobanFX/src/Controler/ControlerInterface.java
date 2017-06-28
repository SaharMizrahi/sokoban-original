package Controler;


/** 
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *This interface define the commands controller interface 
 */
public interface ControlerInterface  {
	/**
	 * This method start the command queue and poll every command in order
	 */
	public void start();
	/**
	 *This method stop the queue from the method above
	 */
	public void stop();
	
}
