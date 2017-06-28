package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

import Model.Data.Level;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *implements model interfce
 */
public class MyModel extends Observable implements ModelInterface {


	private boolean recvSolution;
	private Level CurrentLevel;
	private boolean isDone=false; 
	private boolean isChanged=false;
	private String mySoltuion=null;
	
	

	/**Update current level 
	 * @param lev out source level
	 */
	public void UpdateLevel(Level lev)
	{
		this.setCurrentLevel(lev);
		
		this.setChanged();
		
		
	}
	/**
	 * default constructor
	 */
	public MyModel() {
		
		// TODO Auto-generated constructor stub
		
		CurrentLevel=new Level();
		
		
	}
	/**
	 * solve level and update solution if there is a solution
	 */
	public void solveLevel()
	{
		String solution=null;
	
		try {
			Socket socket=new Socket("127.0.0.1", 8888);//connect to remote server
			PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			LevelCompressorAndGenerator cg=new LevelCompressorAndGenerator();
			out.println("solve-"+cg.compress(this.getCurrentLevel()));//send the compressed level and wait to reply
			out.flush();
			solution=in.readLine();
			if(solution.compareTo("block")!=0)
			{
				this.setSoltuion(solution);//update solution
				notifyObservers();
			}

		 } catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	
		
	}

	/********************************/
	/*****getters and setters********/
	/********************************/
	
	/**
	 * 
	 * @return true if the model got a new solution
	 */
	private boolean isRecvSolution()
	{
		return recvSolution;
	}
	/**
	 * update the level solution
	 * @param newSoltuion
	 */
	public void setSoltuion(String newSoltuion)
	{
		if(newSoltuion!=null)
		{
			this.mySoltuion = newSoltuion;
			this.recvSolution=true;
			notifyObservers();
		}
		else
		{
			this.mySoltuion=null;
			this.recvSolution=false;

		}
	}
	/**
	 * @return true if the model has changed
	 */
	public boolean isChanged()
	{
		return isChanged;
	}
	/**
	 * update the change flag
	 * @param isChanged - new boolean
	 */
	public void setChanged(boolean isChanged)
	{
		this.isChanged = isChanged;
	}
	/**check if the level is finish
	 * 
	 * @return true/false
	 */
	public boolean isDone() {
		return isDone;
	}
	/**Sets the flag
	 * 
	 * @param isDone flag that refers if the level is done
	 */
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	/**
	 * 
	 * @return the level solution
	 */
	public String getSoltuion()
	{
		return mySoltuion;
	}
	/**
	 * update the running level
	 * @param currentLevel-new level
	 */
	public void setCurrentLevel(Level currentLevel) {


		CurrentLevel = currentLevel;
		this.setDone(CurrentLevel.checkIfFinish());
		this.setChanged();
		this.notifyObservers();
		
	}
	/**
	 * return the current level that running
	 * @return-current level
	 */
	public Level getCurrentLevel() {
		return CurrentLevel;
	}
}
