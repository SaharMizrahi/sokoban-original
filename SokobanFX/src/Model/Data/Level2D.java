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
		if (this.Map[dest.getX()][dest.getY()] instanceof Destination)
		{
			if (it.isOnDest())
			{
				Item temp=this.Map[it.getPos().getX()][it.getPos().getY()];
				this.Map[it.getPos().getX()][it.getPos().getY()]=null;		
				this.Map[it.getPos().getX()][it.getPos().getY()]=new Destination();
				this.Map[it.getPos().getX()][it.getPos().getY()].setPos(temp.getPos());
				this.Map[it.getPos().getX()][it.getPos().getY()].setOnDest(false);
				this.Map[dest.getX()][dest.getY()]=null;
				this.Map[dest.getX()][dest.getY()]=temp;
				this.Map[dest.getX()][dest.getY()].setPos(dest);
				this.Map[dest.getX()][dest.getY()].setOnDest(true);
			}
			else
			{
				Item temp=this.Map[it.getPos().getX()][it.getPos().getY()];
				this.Map[it.getPos().getX()][it.getPos().getY()]=null;		
				this.Map[it.getPos().getX()][it.getPos().getY()]=new Floor();
				this.Map[it.getPos().getX()][it.getPos().getY()].setPos(temp.getPos());
				this.Map[it.getPos().getX()][it.getPos().getY()].setOnDest(false);
				this.Map[dest.getX()][dest.getY()]=null;
				this.Map[dest.getX()][dest.getY()]=temp;
				this.Map[dest.getX()][dest.getY()].setPos(dest);
				this.Map[dest.getX()][dest.getY()].setOnDest(true);
			}
		}
		else if (it.isOnDest())
		{
			
			if (this.Map[dest.getX()][dest.getY()].isOnDest())
			{
				Item temp=this.Map[it.getPos().getX()][it.getPos().getY()];
				this.Map[it.getPos().getX()][it.getPos().getY()]=null;		
				this.Map[it.getPos().getX()][it.getPos().getY()]=new Destination();
				this.Map[it.getPos().getX()][it.getPos().getY()].setOnDest(false);
				this.Map[it.getPos().getX()][it.getPos().getY()].setPos(oldPos);
				this.Map[dest.getX()][dest.getY()]=null;
				this.Map[dest.getX()][dest.getY()]=temp;
				this.Map[dest.getX()][dest.getY()].setPos(dest);
				this.Map[dest.getX()][dest.getY()].setOnDest(true);
			}
			else
			{
				Item temp=this.Map[it.getPos().getX()][it.getPos().getY()];
				this.Map[it.getPos().getX()][it.getPos().getY()]=null;
				this.Map[it.getPos().getX()][it.getPos().getY()]=new Destination();
				this.Map[it.getPos().getX()][it.getPos().getY()].setPos(oldPos);
				this.Map[dest.getX()][dest.getY()]=null;
				this.Map[dest.getX()][dest.getY()]=temp;
				this.Map[dest.getX()][dest.getY()].setPos(dest);
				this.Map[dest.getX()][dest.getY()].setOnDest(false);
			}
		}
		else
		{
			if (this.Map[dest.getX()][dest.getY()].isOnDest())
			{
				Item temp=this.Map[it.getPos().getX()][it.getPos().getY()];
				this.Map[it.getPos().getX()][it.getPos().getY()]=null;
				
				this.Map[it.getPos().getX()][it.getPos().getY()]=new Floor();
				this.Map[it.getPos().getX()][it.getPos().getY()].setPos(oldPos);
				this.Map[dest.getX()][dest.getY()]=null;
				
				this.Map[dest.getX()][dest.getY()]=temp;
				this.Map[dest.getX()][dest.getY()].setPos(dest);
				this.Map[dest.getX()][dest.getY()].setOnDest(true);

			}
			else
			{
				Item temp=this.Map[it.getPos().getX()][it.getPos().getY()];
				this.Map[it.getPos().getX()][it.getPos().getY()]=null;
				this.Map[it.getPos().getX()][it.getPos().getY()]=new Floor();
				this.Map[it.getPos().getX()][it.getPos().getY()].setPos(oldPos);
				this.Map[dest.getX()][dest.getY()]=null;
				this.Map[dest.getX()][dest.getY()]=temp;
				this.Map[dest.getX()][dest.getY()].setPos(dest);
			}
		}
		
		
	}
	public Item[][] getMap() {
		return Map;
	}
	public void setMap(Item[][] map) {
		Map = map;
	}
	public boolean checkPosition(Position pos)
	{
		if (
				(pos.getX()<0)||
				(pos.getX()>=Length)||
				(pos.getY()<0)||
				(pos.getY()>=Width))
			return false;
		else
			return true;
	}
	public Item getItemByPosition(Position ItemPos)
	{
		
		
		return Map[ItemPos.getX()][ItemPos.getY()];
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
