package Controller.Server;

import java.io.InputStream;
import java.io.OutputStream;


/**This is an Interface that defined the Method that Handle the Server and the Client communication
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *
 */

public interface ClientHandler  {
	/**
	 * start communicate
	 * @param in The socket's input
	 * @param out the socket's output
	 */
	public void handleClient(InputStream in,OutputStream out);
	

}
