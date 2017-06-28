package Model.Data;

import java.io.Serializable;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	this class represent our position class int the sokoban game
 */
public class Position implements Serializable {
	private int row;
	private int col;
	
	
	/**
	 * class hashcode method
	 */
	@Override
	public int hashCode()
	{
		return row*10+col;
		
	}
	/**
	 * return  index 
	 * @return col
	 */
	public int getCol() {
		return col;
	}
	/**
	 * update the column index
	 * @param new Col
	 */
	public void setCol(int newCol) {
		this.col =	newCol;
	}
	/**
	 * 
	 * @return the row index
	 */
	public int getRow() {
		return row;
	}
	/**
	 * update the row index
	 * @param newRow
	 */
	public void setRow(int newRow) {
		this.row = newRow;
	}
	/**
	 * 
	 * @return the position thats up to this position
	 */
	public Position getUp()
	{
		return new Position(row-1,col);
	}
	/**
	 * 
	 * @return the position thats down to this position
	 */
	public Position getDown()
	{
		return new Position(row+1,col);
	}
	/**
	 * 
	 * @return the position thats right to this position
	 */
	public Position getRight()
	{
		return new Position(row,col+1);
	}
	/**
	 * 
	 * @return the position thats left to this position
	 */
	public Position getLeft()
	{
		return new Position(row,col-1);
	}
	/**
	 * to string method
	 */
	public String toString()
	{
		return "("+row+","+col+")";
	}
	/**
	 * constructor using fields
	 * @param r-row
	 * @param c-col
	 */
	public Position (int r,int c)
	{
		this.row=r;
		this.col=c;
	}
	/**
	 * default constructor
	 */
	public Position()
	{
		
	}
	/**
	 * equals method
	 */
	@Override
	public boolean equals(Object obj)
	{
		if ((this.col==((Position)obj).col)&&(this.row==((Position)obj).row))
			return true;
		else
			return false;
		
	}

}
