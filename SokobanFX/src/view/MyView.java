package view;

import java.util.Observable;

import Model.Data.Level;

public class MyView extends Observable implements ViewInterface {


	private Level lev;
	private String userCommand;



		
	
	public MyView() {
		super();
	
		this.userCommand=null;
		this.lev=new Level();

	}
	public String getUserCommand() {
		
		return userCommand;
	}
	public void setUserCommand(String userCommand) {
		this.userCommand = userCommand;
	}
	public Level getLev() {
		return lev;
	}
	public void setLev(Level lev) {
		this.lev = lev;
		this.setChanged();
	}
	@Override
	public void closeAllThreads() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getArrByString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setSteps(int step) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setArr(char[][] arr) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDone(boolean isDone) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getSteps()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setDirection(String s)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getDirection()
	{
		// TODO Auto-generated method stub
		return null;
	}




}
