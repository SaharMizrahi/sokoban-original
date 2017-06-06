package Model.Data;

import java.io.Serializable;

public class Position implements Serializable {
	
	private int col;
	private int row;
	
	@Override
	public int hashCode()
	{
		return row*10+col;
		
	}
	public int getCol() {
		return col;
	}
	public void setCol(int x) {
		this.col = x;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int y) {
		this.row = y;
	}
	public Position getUp()
	{
		return new Position(row-1,col);
	}
	public Position getDown()
	{
		return new Position(row+1,col);
	}
	public Position getRight()
	{
		return new Position(row,col+1);
	}
	public Position getLeft()
	{
		return new Position(row,col-1);
	}
	public String toString()
	{
		return "("+row+","+col+")";
	}
	public Position (int r,int c)
	{
		this.row=r;
		this.col=c;
	}
	public Position()
	{
		
	}
	@Override
	public boolean equals(Object obj)
	{
		if ((this.col==((Position)obj).col)&&(this.row==((Position)obj).row))
			return true;
		else
			return false;
		
	}

}
