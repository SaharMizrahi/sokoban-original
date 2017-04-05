package Model.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.scene.image.Image;

public class Item implements Serializable {
	
	/**
	 * 
	 */
	public char getChar()
	{
		return '.';
	}
	private static final long serialVersionUID = 1L;
	private Position pos;
	private boolean onDest;
	
	public Image getImage()
	{
		
		return null;
		
	}

	public void print()
	{
		System.out.print(" ");
	}
	public String toString()
	{
		return "";
	}
	public Position getPos()
	{
		return this.pos;
	}
	public void setPos(Position other)
	{
		this.pos=other;
	}
	public boolean isOnDest() {
		return onDest;
	}
	public void setOnDest(boolean onDest) {
		this.onDest = onDest;
	}
	public Item() {
		super();
		// TODO Auto-generated constructor stub
		onDest=false;
	}

}
