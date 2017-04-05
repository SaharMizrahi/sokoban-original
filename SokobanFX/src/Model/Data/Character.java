package Model.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.scene.image.Image;

public class Character extends Item implements Serializable {
	
	public char getChar()
	{
		return 'A';
	}
	public Image getImage()
	{
		
		try {
			return new Image(new FileInputStream("./resources/character.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String toString()
	{
		if (this.isOnDest())
			return "a";
		return "A";
	}
	
	public void print()
	{
		System.out.print("A");
	}
	public Character() {
		super();
		// TODO Auto-generated constructor stub
	}


	


}
