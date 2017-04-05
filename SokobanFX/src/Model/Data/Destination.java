package Model.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.scene.image.Image;

public class Destination extends Item implements Serializable {
	public char getChar()
	{
		return 'o';
	}
	public Destination() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Image getImage()
	{
		
		try {
			return new Image(new FileInputStream("./resources/destination.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String toString()
	{
		return "o";
	}
	
	public void print()
	{
		System.out.print("o");
	}
	

}
