package Model.Data;

import java.io.Serializable;

public class Position implements Serializable {
	
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Position getForward()
	{
		return new Position(x-1,y);
	}
	public Position getBackward()
	{
		return new Position(x+1,y);
	}
	public Position getRight()
	{
		return new Position(x,y+1);
	}
	public Position getLeft()
	{
		return new Position(x,y-1);
	}
	public String toString()
	{
		return ""+x+" "+y;
	}
	public Position (int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public Position()
	{
		
	}

}
