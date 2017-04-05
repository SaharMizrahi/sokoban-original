package Controler;


/** This interface define the controller interface 
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *
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
