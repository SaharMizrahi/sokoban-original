package Model.Data;

import java.io.Serializable;
/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	wall item in the sokoban level
 */
public class Wall extends Item implements Serializable {

	/**
	 * return the item char
	 */
	public char getChar()
	{
		return '#';
	}
	/**
	 * print the item char
	 */
	public void print()
	{
		System.out.print("#");
	}
	/**
	 * to string method
	 */
	public String toString()
	{
		return "#";
	}
	/**
	 * constructor
	 */
	public Wall() {
		super();
		// TODO Auto-generated constructor stub
	}


}
