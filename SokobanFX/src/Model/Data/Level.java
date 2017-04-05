package Model.Data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Level implements Serializable{
		private ArrayList<Item> BoxList;
		private ArrayList<Item> CharacterList;
		private ArrayList<Item> DestinyList;
		private ArrayList<Item> WallList;
		private ArrayList<Position> ConquredDestinations;
		
		
		private int stepsCounter;
		
		
		public char[][] toCharArray()
		{
			return null;
		}
		public boolean checkIfFinish()
		{
			if(!BoxList.isEmpty())
			{
				for (Item it: BoxList)
				{
					if (!it.isOnDest())
					{
						
						return false;
					}
				}
	
				return true;
			}
			return false;
		}
		public void setInPlace(Item it, Position dest)
		{
			
		}
		public boolean checkPosition(Position pos)
		{
			return false;
		}
		public Item getItemByPosition(Position ItemPos)
		{
			return this.getCharacter();
		}
		public Item getCharacter()
		{
			return this.CharacterList.get(0);
		}
		public XMLEncoder XMLSaving(XMLEncoder XE)
		{
			return XE;
			
		}
		public XMLDecoder XMLReading(XMLDecoder XC)
		{
			
			return XC;
			
		}
		public ArrayList<Position> getConquredDestinations() {
			return ConquredDestinations;
		}
		public void setConquredDestinations(ArrayList<Position> conquredDestinations) {
			ConquredDestinations = conquredDestinations;
		}
		public BufferedWriter writeToBuffer(BufferedWriter BW) throws IOException
		{
			
			return BW;
		}
		public ArrayList<Item> getBoxList() {
			return BoxList;
		}
		public String toString()
		{
			return " ";
		}
		public void print()
		{
			System.out.println("Level");
		}
	
		public OutputStream writeToOutput(OutputStream out) throws IOException
		{
			return out;
		}
		public void setBoxList(ArrayList<Item> boxList) {
			BoxList = boxList;
		}
	
	
		public ArrayList<Item> getCharacterList() {
			return CharacterList;
		}
	
	
		public void setCharacterList(ArrayList<Item> characterList) {
			CharacterList = characterList;
		}
	
	
		public ArrayList<Item> getDestinyList() {
			return DestinyList;
		}
	
	
		public void setDestinyList(ArrayList<Item> destinyList) {
			DestinyList = destinyList;
		}
	
	
		public ArrayList<Item> getWallList() {
			return WallList;
		}
	
	
		public void setWallList(ArrayList<Item> wallList) {
			WallList = wallList;
		}
	
	
	public Level()
	{
		this.WallList=new ArrayList<Item>();
		this.BoxList=new ArrayList<Item>();
		this.DestinyList=new ArrayList<Item>();
		this.CharacterList=new ArrayList<Item>();
		this.ConquredDestinations=new ArrayList<Position>();
		
		this.stepsCounter=0;
	}
	public int getStepsCounter() {
		return stepsCounter;
	}
	public void setStepsCounter(int stepsCounter) {
		this.stepsCounter = stepsCounter;
	}
}
