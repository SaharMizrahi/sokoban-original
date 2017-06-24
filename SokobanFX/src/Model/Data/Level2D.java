package Model.Data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;



public class Level2D extends Level implements Serializable {
	private int Length;
	private int Width;
	private Item[][] Map;
	
	

	public boolean isValidPosition(Position position)
	{
		if(position.getRow()<0||position.getRow()>Length-1||position.getCol()<0||position.getCol()>Width-1)
			return false;
		else return true;
		
	}
	public char[][] toCharArray()
	{
		char[][] arr=new char[Length][Width];
		for(int i=0;i<Length;i++)
			for (int j=0;j<Width;j++)
				arr[i][j]=Map[i][j].getChar();
		return arr;
		
	}
	public void setInPlace(Item it, Position dest)
	{
		Position oldPos=it.getPos();
		if (this.Map[dest.getRow()][dest.getCol()] instanceof Destination)
		{
			if (it.isOnDest())
			{
				Item temp=this.Map[it.getPos().getRow()][it.getPos().getCol()];
				this.Map[it.getPos().getRow()][it.getPos().getCol()]=null;		
				this.Map[it.getPos().getRow()][it.getPos().getCol()]=new Destination();
				this.Map[it.getPos().getRow()][it.getPos().getCol()].setPos(temp.getPos());
				this.Map[it.getPos().getRow()][it.getPos().getCol()].setOnDest(false);
				this.Map[dest.getRow()][dest.getCol()]=null;
				this.Map[dest.getRow()][dest.getCol()]=temp;
				this.Map[dest.getRow()][dest.getCol()].setPos(dest);
				this.Map[dest.getRow()][dest.getCol()].setOnDest(true);
			}
			else
			{
				Item temp=this.Map[it.getPos().getRow()][it.getPos().getCol()];
				this.Map[it.getPos().getRow()][it.getPos().getCol()]=null;		
				this.Map[it.getPos().getRow()][it.getPos().getCol()]=new Floor();
				this.Map[it.getPos().getRow()][it.getPos().getCol()].setPos(temp.getPos());
				this.Map[it.getPos().getRow()][it.getPos().getCol()].setOnDest(false);
				this.Map[dest.getRow()][dest.getCol()]=null;
				this.Map[dest.getRow()][dest.getCol()]=temp;
				this.Map[dest.getRow()][dest.getCol()].setPos(dest);
				this.Map[dest.getRow()][dest.getCol()].setOnDest(true);
			}
		}
		else if (it.isOnDest())
		{
			
			if (this.Map[dest.getRow()][dest.getCol()].isOnDest())
			{
				Item temp=this.Map[it.getPos().getRow()][it.getPos().getCol()];
				this.Map[it.getPos().getRow()][it.getPos().getCol()]=null;		
				this.Map[it.getPos().getRow()][it.getPos().getCol()]=new Destination();
				this.Map[it.getPos().getRow()][it.getPos().getCol()].setOnDest(false);
				this.Map[it.getPos().getRow()][it.getPos().getCol()].setPos(oldPos);
				this.Map[dest.getRow()][dest.getCol()]=null;
				this.Map[dest.getRow()][dest.getCol()]=temp;
				this.Map[dest.getRow()][dest.getCol()].setPos(dest);
				this.Map[dest.getRow()][dest.getCol()].setOnDest(true);
			}
			else
			{
				Item temp=this.Map[it.getPos().getRow()][it.getPos().getCol()];
				this.Map[it.getPos().getRow()][it.getPos().getCol()]=null;
				this.Map[it.getPos().getRow()][it.getPos().getCol()]=new Destination();
				this.Map[it.getPos().getRow()][it.getPos().getCol()].setPos(oldPos);
				this.Map[dest.getRow()][dest.getCol()]=null;
				this.Map[dest.getRow()][dest.getCol()]=temp;
				this.Map[dest.getRow()][dest.getCol()].setPos(dest);
				this.Map[dest.getRow()][dest.getCol()].setOnDest(false);
			}
		}
		else
		{
			if (this.Map[dest.getRow()][dest.getCol()].isOnDest())
			{
				Item temp=this.Map[it.getPos().getRow()][it.getPos().getCol()];
				this.Map[it.getPos().getRow()][it.getPos().getCol()]=null;
				
				this.Map[it.getPos().getRow()][it.getPos().getCol()]=new Floor();
				this.Map[it.getPos().getRow()][it.getPos().getCol()].setPos(oldPos);
				this.Map[dest.getRow()][dest.getCol()]=null;
				
				this.Map[dest.getRow()][dest.getCol()]=temp;
				this.Map[dest.getRow()][dest.getCol()].setPos(dest);
				this.Map[dest.getRow()][dest.getCol()].setOnDest(true);

			}
			else
			{
				Item temp=this.Map[it.getPos().getRow()][it.getPos().getCol()];
				this.Map[it.getPos().getRow()][it.getPos().getCol()]=null;
				this.Map[it.getPos().getRow()][it.getPos().getCol()]=new Floor();
				this.Map[it.getPos().getRow()][it.getPos().getCol()].setPos(oldPos);
				this.Map[dest.getRow()][dest.getCol()]=null;
				this.Map[dest.getRow()][dest.getCol()]=temp;
				this.Map[dest.getRow()][dest.getCol()].setPos(dest);
			}
		}
		
		
	}
	public Item[][] getMap() {
		return Map;
	}
	public void setMap(Item[][] map) {
		Map = map;
	}
	public void clearPosition(Position pos)
	{
		if(isValidPosition(pos))
		{
			
			Map[pos.getRow()][pos.getCol()]=new Floor();
			Map[pos.getRow()][pos.getCol()].setPos(pos);
		}
	}
	public Item getItemByPosition(Position initPos)
	{
		int col=initPos.getCol();
		int row=initPos.getRow();

		if((col<0)||(row<0)||(col>=this.Width)||(row>=this.Length))
			return null;
		else
		{
			return Map[row][col];
		}
	}
	public XMLDecoder XMLReading(XMLDecoder XC)
	{
		Map=new Item[this.Length][this.Width];
		for (int i=0;i<Length;i++)
			for(int j=0;j<Width;j++)
				Map[i][j]=(Item)XC.readObject();
		return XC;
		
	}
	public XMLEncoder XMLSaving(XMLEncoder XE)
	{
		for (int i=0;i<Length;i++)
			for(int j=0;j<Width;j++)
				XE.writeObject(Map[i][j]);
		return XE;
		
	}
	public OutputStream writeToOutput(OutputStream out) throws IOException
	{
		out.write((""+Length+"\n"+Width+"").getBytes());
		for (int i=0;i<Length;i++)
		{
			out.write(("\n").getBytes());
			for (int j=0;j<Width;j++)
			{
				
					out.write((Map[i][j].toString()).getBytes());
				
			}
			
		}
		
		return out;
	}
	public BufferedWriter writeToBuffer(BufferedWriter BW) throws IOException
	{
	
		BW.write(this.toString());
		BW.newLine();
		BW.write(""+this.Length);
		BW.newLine();
		BW.write(""+this.Width);
		for (int i=0;i<this.Length;i++)
		{
			BW.newLine();
			for (int j=0;j<this.Width;j++)
			{
				
					BW.write(Map[i][j].toString());
				
			}
		}
		return BW;
	}
	public String toString()
	{
		

		return "Level2D";
	}
	public void print()
	{
		
		for (int i=0;i<Length;i++)
		{
			
			for (int j=0;j<Width;j++)
			{
				if (Map[i][j]!=null)
					Map[i][j].print();
				
			}
			System.out.print("\n");
		}
			
	}
	public int getLength() {
		return Length;
	}
	public void setLength(int length) {
		Length = length;
	}
	public int getWidth() {
		return Width;
	}
	public void setWidth(int width) {
		Width = width;
	}
	public Level2D()
	{
		
		
	}

}
