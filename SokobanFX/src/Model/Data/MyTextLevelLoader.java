package Model.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MyTextLevelLoader implements LevelLoader{
	public int getLen(String s)
	{
		int len=0;
		int slen=s.length();
		for (int i=0;i<slen;i++)
		{
			len=len*10+(s.charAt(i)-48);
		}
		return len;
	}
	public Level loadLevel(InputStream in) throws IOException {
		Level newLev=new Level();
		
		BufferedReader BR=new BufferedReader(new InputStreamReader(in));
		String S=BR.readLine();
		char[][] arr;
		
		if (S.compareTo("Level2D")==0)
		{
			newLev=new Level2D();
			int length,width;
			S=BR.readLine();
			length=getLen(S);
			S=BR.readLine();
			width=getLen(S);
			arr=new char[length][width];
			for (int i=0;i<length;i++)
			{
				S=BR.readLine();
				for (int j=0;j<width;j++)
					arr[i][j]=S.charAt(j);
			}
			
			newLev=Loading2DLevel(arr,length,width);
			
		}
		
		
		return newLev;
	}

	
	public Level Loading2DLevel(char arr[][], int length, int width) throws IOException
	{
		Level2D newLev=new Level2D();
		newLev.setLength(length);
		newLev.setWidth(width);
		String ch;
		ObjectFactory OF=new ObjectFactory();
		Creator c;
		newLev.setMap(new Item[length][width]);
		
		ch=new String();
		for (int i=0;i<length;i++)
		{			
			for (int j=0;j<width;j++)
			{
				ch=""+arr[i][j];
				
					c=OF.getHM().get(ch);
					newLev.getMap()[i][j]=c.create();
					newLev.getMap()[i][j].setPos(new Position(i,j));
					switch(ch)
					{
					case "A":
						newLev.getCharacterList().add(newLev.getMap()[i][j]);
						
						break;
					case "a":
						newLev.getCharacterList().add(newLev.getMap()[i][j]);
						newLev.getMap()[i][j].setOnDest(true);
						break;
					case "@":
						newLev.getBoxList().add(newLev.getMap()[i][j]);
						break;
					case "$":
						newLev.getBoxList().add(newLev.getMap()[i][j]);
						newLev.getMap()[i][j].setOnDest(true);
						break;
					case "o":
						newLev.getDestinyList().add(newLev.getMap()[i][j]);
						break;
					case "#":
						newLev.getWallList().add(newLev.getMap()[i][j]);
						
						break;
					}
				
				
			}
		}
		
		return newLev;
	}
}
				
		
			
	


