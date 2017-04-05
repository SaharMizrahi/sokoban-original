package Controller.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
/**The server that runs the communication between the game and the client
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *
 */

public class MyServer   {
	private MyClientHandler ch;
	private int port;
	private boolean stop=false;

	/**
	 * Close all sockets
	 */
	
	public void closeAllSockets()
	{
		this.ch.setMsgToUser("bye");
		//System.out.println("send bye to client");
		this.ch.setStop(true);
		this.stop=true;
	}
	/**Starts the communication thread
	 * 
	 * @throws IOException
	 */
	public void runServer() throws IOException
	{
		ServerSocket server=new ServerSocket(port);
		server.setSoTimeout(1000);
		while(!stop)
		{
			try{
				Socket aClient=server.accept();
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {

							ch.handleClient(aClient.getInputStream(),aClient.getOutputStream());
							aClient.close();
							ch.setStop(false);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}).start();

			}
			catch (SocketTimeoutException e)
			{

			}

		}
		System.out.println("closing server");
		server.close();
	}
	/**
	 * 
	 * @return the client hanlder
	 */
	public MyClientHandler getCh() {
		return ch;
	}

	/**
	 * default constructor
	 */
	public MyServer() {
		super();
		// TODO Auto-generated constructor stub
		ch=new MyClientHandler();
	}

	/**Sets the client handler
	 * 
	 * @param ch out source client handler
	 */
	
	public void setCh(MyClientHandler ch) {
		this.ch = ch;
	}
	/**
	 * 
	 * @return the server's port
	 */
	public int getPort() {
		return port;
	}

	/**Sets the server's port
	 * 
	 * @param port out source port
	 */
	public void setPort(int port) {
		this.port = port;
	}
	/**
	 * 
	 * @return true if communication stopped and false if not
	 */
	public boolean isStop() {
		return stop;
	}
	/** sets the communication's flag
	 * 
	 * @param stop out source flag
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
	}


	/**
	 * 
	 * @return the client command
	 */
	public String getUserCommand() {
		// TODO Auto-generated method stub
		return this.ch.getCmd();
	}



}
