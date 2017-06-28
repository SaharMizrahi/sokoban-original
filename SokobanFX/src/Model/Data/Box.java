package Model.Data;

import java.io.Serializable;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	Box Item in the sokoban level
 */
public class Box extends Item implements Serializable{
	/**
	 * default constructor
	 */
	public Box() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * return the item char in the map
	 */
	public char getChar()
	{
		return '@';
	}
	/**
	 * to string method,prints $ if the box is on target, @ if it isn't
	 */
	public String toString()
	{
		if (this.isOnDest())
			return "$";
		return "@";
	}
	/**
	 * prints the item char $ if the box is on target, @ if it isn't
	 */
	public void print()
	{
		if (this.isOnDest())
			System.out.print("$");
		else
			System.out.print("@");
	}


}
