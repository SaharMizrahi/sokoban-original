package Model.Data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Sahar Mizrahi and Gal Ezra
 *	this class is our level in sokoban game
 */
public class Level implements Serializable{
		
		
		private ArrayList<Item> BoxList;
		private ArrayList<Item> CharacterList;
		private ArrayList<Item> DestinationList;
		private ArrayList<Item> WallList;
		private ArrayList<Position> ConquredDestinations;
		private String levelName;
		
		private int stepsCounter;
		
		
		

		/**
		 * 
		 * @return the level name
		 */
		public String getLevelName()
		{
			return levelName;
		}
		/**
		 * set the level name
		 * @param levelName
		 */
		public void setLevelName(String levelName)
		{
			this.levelName = levelName;
		}
		/**
		 * 
		 * @return the level map in char array
		 */
		public char[][] toCharArray()
		{
			return null;
		}
		/**
		 * 
		 * @return true if the level is complete
		 */
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
		/**
		 * put item it in pos position
		 * @param it
		 * @param dest
		 */
		public void setInPlace(Item it, Position dest)
		{
			
		}
		/**
		 * return the item that in itemPos position in the map
		 * @param ItemPos
		 * @return
		 */
		public Item getItemByPosition(Position ItemPos)
		{
			
			return this.getCharacter();
		}
		/**
		 * 
		 * @return the character in the game
		 */
		public Item getCharacter()
		{
			if(this.CharacterList!=null)
				return this.CharacterList.get(0);
			else
				return null;
		}
		/**
		 * save level into xml file
		 * @param XE
		 * @return
		 */
		public XMLEncoder XMLSaving(XMLEncoder XE)
		{
			return XE;
			
		}
		/**
		 * load level from xml file
		 * @param XC
		 * @return
		 */
		public XMLDecoder XMLReading(XMLDecoder XC)
		{
			
			return XC;
			
		}
		/**
		 * 
		 * @return list of the conquered destination
		 */
		public ArrayList<Position> getConquredDestinations() {
			return ConquredDestinations;
		}
		/**
		 * set the list of conquerd destination
		 * @param conquredDestinations
		 */
		public void setConquredDestinations(ArrayList<Position> conquredDestinations) {
			ConquredDestinations = conquredDestinations;
		}
		/**
		 * write to bw buffered writer
		 * @param BW
		 * @return
		 * @throws IOException
		 */
		public BufferedWriter writeToBuffer(BufferedWriter BW) throws IOException
		{
			
			return BW;
		}
		/**
		 * 
		 * @return list of the box in the game
		 */
		public ArrayList<Item> getBoxList() {
			return BoxList;
		}
		/**
		 * to string method
		 */
		public String toString()
		{
			return "Level";
		}
		/**
		 * print the level map
		 */
		public void print()
		{
			System.out.println("Level");
		}
		/**
		 * write to output stream
		 * @param out
		 * @return
		 * @throws IOException
		 */
		public OutputStream writeToOutput(OutputStream out) throws IOException
		{
			return out;
		}
		/**
		 * set the box list
		 * @param boxList
		 */
		public void setBoxList(ArrayList<Item> boxList) {
			BoxList = boxList;
		}
	
		/**
		 * 
		 * @return the character list
		 */
		public ArrayList<Item> getCharacterList() {
			return CharacterList;
		}
	
		/**
		 * set the character list
		 * @param characterList
		 */
		public void setCharacterList(ArrayList<Item> characterList) {
			CharacterList = characterList;
		}
	
		/**
		 * 
		 * @return list of destination in the level
		 */
		public ArrayList<Item> getDestinationList() {
			return DestinationList;
		}
	
		/**
		 * set the destination list in the level
		 * @param destinationList
		 */
		public void setDestinationList(ArrayList<Item> destinationList) {
			DestinationList = destinationList;
		}
	
		/**
		 * 
		 * @return the wall list in the game
		 */
		public ArrayList<Item> getWallList() {
			return WallList;
		}
	
		/**
		 * set the wall list in the game
		 * @param wallList
		 */
		public void setWallList(ArrayList<Item> wallList) {
			WallList = wallList;
		}
	
	
	public Level()
	{
		this.WallList=new ArrayList<Item>();
		this.BoxList=new ArrayList<Item>();
		this.DestinationList=new ArrayList<Item>();
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
