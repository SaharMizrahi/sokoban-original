package Model.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.scene.image.Image;

public class Box extends Item implements Serializable{
	public Box() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Image getImage()
	{
		
		try {
			return new Image(new FileInputStream("./resources/box.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public char getChar()
	{
		return '@';
	}
	public String toString()
	{
		if (this.isOnDest())
			return "$";
		return "@";
	}
	public void print()
	{
		System.out.print("@");
	}


}
