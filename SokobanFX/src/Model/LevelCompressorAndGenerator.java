package Model;

import Model.Data.Box;
import Model.Data.Character;
import Model.Data.Destination;
import Model.Data.Floor;
import Model.Data.Item;
import Model.Data.Level;
import Model.Data.Level2D;
import Model.Data.Position;
import Model.Data.Wall;

public class LevelCompressorAndGenerator {
	
	public String compress(Level lev)
	{
		char current='*',prev='*';
		int count=0;
		String result="";
		if(lev instanceof Level2D)
		{
			int len=((Level2D) lev).getLength(),wid=((Level2D) lev).getWidth();
			result+="Level2D-"+len+"-"+wid;
			for(int i=0;i<len;i++)
			{
				for(int j=0;j<wid;j++)
				{
					current=((Level2D) lev).getMap()[i][j].getChar();
					if(prev=='*')//this is the first char
					{
						prev=current;
						count=1;
					}
					else if(current==prev)//this is the same char
						count++;
					else//this is diffrent char
					{
						result+="-"+prev+""+count;
						prev=current;
						count=1;
					}
					
				}
			}
			
			result+="-"+prev+""+count;

		}
		
		
		return result;

		
		
	}
	
	public Level generate(String str)
	{
		int num=0;
		char current;
		Level lev=null;
		String strArr[]=str.split("-");
		if(strArr[0].compareTo("Level2D")==0)
		{
			System.out.println("check");
			lev=new Level2D();
			int len=Integer.parseInt(strArr[1]);
			int wid=Integer.parseInt(strArr[2]);
			((Level2D) lev).setLength(len);
			((Level2D) lev).setWidth(wid);
			Item[][]map=new Item[len][wid];
			int j=3;
			int col=0,row=0;
			while(j<strArr.length)
			{
				current=strArr[j].charAt(0);
				num=Integer.parseInt(strArr[j].substring(1, strArr[j].length()));
				while(num>0)
				{
					
					switch(current)
					{
					case ' ':
						map[row][col]=new Floor();
						break;
					case 'A':
						map[row][col]=new Character();
						lev.getCharacterList().add(map[row][col]);
						break;
					case '@':
						map[row][col]=new Box();
						lev.getBoxList().add(map[row][col]);
						break;
					case '$':
						map[row][col]=new Box();
						map[row][col].setOnDest(true);
						lev.getBoxList().add(map[row][col]);
						break;
					case '#':
						map[row][col]=new Wall();
						break;
					case 'o':
						map[row][col]=new  Destination();
						lev.getDestinationList().add(map[row][col]);
						break;

					}
					map[row][col].setPos(new Position(row, col));

					if(col==wid-1)
					{
						row++;
						col=0;
					}
					else
						col++;
					num--;
				}
				j++;
				
			}
			((Level2D) lev).setMap(map);
		}
		return lev;
		
	}

}
