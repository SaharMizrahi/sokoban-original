package Model.Policy;

import Model.Data.Box;
import Model.Data.Item;
import Model.Data.Level;
import Model.Data.Position;
import Model.Data.Wall;

public class MySokobanPolicy
{
	public boolean goThrowWalls()
	{
		return false;
	}
	public boolean moveTwoBox()
	{
		return false;
		
	}
	public boolean canPullBox()
	{
		return false;
	}
	public boolean check(Level lev,Item A,Position posB,Position posC)
	{
		if (lev.getItemByPosition(posB) instanceof Wall)
			return this.goThrowWalls();
		if ((lev.getItemByPosition(posB) instanceof Box)&&(lev.getItemByPosition(posC) instanceof Box))
			return this.moveTwoBox();
		return true;
	}
	


}
