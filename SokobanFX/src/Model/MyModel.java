package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

import Model.Data.Level;
/**implemets model interfce
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *
 */
public class MyModel extends Observable implements ModelInterface {


	private boolean recvSolution;
	private Level CurrentLevel;
	private boolean isDone=false; 
	private boolean isChanged=false;
	private String soltuion=null;
	
	
	
	public boolean isRecvSolution()
	{
		return recvSolution;
	}

	public void setSoltuion(String newSoltuion)
	{
		if(newSoltuion!=null)
		{
			this.soltuion = newSoltuion;
			this.recvSolution=true;
			notifyObservers();
		}
		else
		{
			this.recvSolution=false;

		}
	}
	public boolean isChanged()
	{
		return isChanged;
	}
	public void setChanged(boolean isChanged)
	{
		this.isChanged = isChanged;
	}
	/**Update current level 
	 * @param lev out source level
	 */
	public void UpdateLevel(Level lev)
	{
		this.setCurrentLevel(lev);
		
		this.setChanged();
		
		
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
	 * default constructor
	 */
	public MyModel() {
		
		// TODO Auto-generated constructor stub
		
		CurrentLevel=new Level();
		
		
	}

	

	public Level getCurrentLevel() {
		return CurrentLevel;
	}
	
	public void setCurrentLevel(Level currentLevel) {

		CurrentLevel = currentLevel;
		this.setDone(CurrentLevel.checkIfFinish());
		this.setChanged();
		this.notifyObservers();
		
	}
	public String solveLevel()
	{
		String solution=null;
	
		try {
			Socket s=new Socket("127.0.0.1", 8888);
			PrintWriter out=new PrintWriter(s.getOutputStream(),true);
			BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			LevelCompressorAndGenerator cg=new LevelCompressorAndGenerator();
			//very important!!!!!!
			out.println(cg.compress(this.getCurrentLevel()));
			out.flush();
			solution=in.readLine();
			if(solution.compareTo("block")!=0)
			{
				this.soltuion=solution;
				notifyObservers();
			}

		 } catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return solution;
		
	}
	public String getSoltuion()
	{
		return soltuion;
	}



}
