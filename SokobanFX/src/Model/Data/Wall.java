package Model.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.scene.image.Image;

public class Wall extends Item implements Serializable {

	public char getChar()
	{
		return '#';
	}
	public void print()
	{
		System.out.print("#");
	}
	public String toString()
	{
		return "#";
	}
	public Wall() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Image getImage()
	{
		
		try {
			return new Image(new FileInputStream("./resources/wall.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
