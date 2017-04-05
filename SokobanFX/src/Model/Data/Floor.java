package Model.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Floor extends Item {
	
	public char getChar()
	{
		return ' ';
	}
	public String toString()
	{
		return " ";
	}
	public Image getImage()
	{
		
		try {
			return new Image(new FileInputStream("./resources/floor.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void print()
	{
		System.out.print(" ");
	}

}
