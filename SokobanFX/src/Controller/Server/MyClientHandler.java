package Controller.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Observable;
/** A Class that implements ClientHandler interface
 * it has String that refers to the client command, one string that refers the message to the user
 * 
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *
 */
public class MyClientHandler extends Observable implements ClientHandler {

	private boolean ifHappend;
	private String cmd;
	private String msgToUser;
	private boolean stop;
	/**
	 * The codes of the method from ClientHandler interface
	 */
	@Override
	public void handleClient(InputStream in, OutputStream out) {
		// TODO Auto-generated method stub
		String menuString="MENU:\n"
				+ "-load FILENAME	"
				+ "display	"
				+ "-move DIRECTION	"
				+ "-save FILENAME	"
				+ "exit\n";
		String s=new String();
		PrintStream bw=new PrintStream(out);
		bw.println(menuString);
		System.out.println("start talking");
		while(!stop)
		{
			try {
				
				s=new BufferedReader(new InputStreamReader(in)).readLine();
				String[] arg=s.split(" ");
				if (arg[0].toLowerCase().compareTo("exit")==0)
				{
					
					stop=true;
					
				}
				else
				{
					this.setCmd(s);
					bw.println(msgToUser);

				}
				

				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		bw.println("bye");
		System.out.println("finish talking and waiting to another client");
		
	}

	/**
	 * 
	 * @return the message to the user
	 */
	public String getMsgToUser() {
		return msgToUser;
	}
/**Sets the measage to the user
 * 
 * @param msgToUser out source message to the user
 */
	public void setMsgToUser(String msgToUser) {
		this.msgToUser = msgToUser;
	}
/**Save the level after the client request
 * 
 * @param in The sockets input line
 * @param out The sockets output line
 */
	public void saveLevelBeforExit(InputStream in,OutputStream out)
	{
		String s;
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(out));
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		try {
			bw.write("befor exit the game,do you want to save the level?");
			
			s=br.readLine();
			if (s.compareTo("yes".toLowerCase())==0)
			{
				bw.write("enter file name with extnesion /.txt/.xml/.obj/" );
				s=br.readLine();
				this.setCmd("save "+s);

			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return true or false if the command executed
	 */
	public boolean isIfHappend() {
		return ifHappend;
	}
	/**Update flag
	 * 
	 * @param ifHappend true if executed false if not
	 */
	public void setIfHappend(boolean ifHappend) {
		this.ifHappend = ifHappend;
	}
	/**
	 * Default constructor
	 */
	public MyClientHandler() {
		super();
		// TODO Auto-generated constructor stub
		this.stop=false;
		this.ifHappend=false;
	}
	/**
	 * 
	 * @return the communication flag 
	 */
	public boolean isStop() {
		return stop;
	}
	/**
	 * sets the communication flag
	 * @param stop
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	/**
	 * 
	 * @return return the current command that the client entered
	 */
	public String getCmd() {
		return cmd;
	}
	/**
	 * update the current command that the client entered
	 * @param cmd-Client's command
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
		this.setChanged();
		this.notifyObservers(cmd);
		
	}





}
