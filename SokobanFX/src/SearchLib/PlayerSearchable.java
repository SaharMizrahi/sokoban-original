package SearchLib;

import java.util.HashMap;

import Model.Data.Item;
import Model.Data.Level2D;
import Model.Data.Position;

public class PlayerSearchable extends CommonSearchable
{

	public char[][] charMap;
	public PlayerSearchable(Position fromPosition, Position toPosition, Level2D level) {
		super(fromPosition, toPosition, level);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HashMap<ComplexAction, State<Position>> getAllPossibleStates(State<Position> s)
	{
		// TODO Auto-generated method stub
		HashMap<ComplexAction,State<Position>> possibleStates=new HashMap<>();
		Position currentPosition=s.getState();
		//move up
		if(checkPossibleMove(currentPosition, Action.UP))
			possibleStates.put(new ComplexAction(Action.UP, null), new State<>(s,s.getCost()+1,currentPosition.getUp(),new ComplexAction(Action.UP, null)));
		//move down
		if(checkPossibleMove(currentPosition, Action.DOWN))
			possibleStates.put(new ComplexAction(Action.DOWN, null), new State<>(s,s.getCost()+1,currentPosition.getDown(),new ComplexAction(Action.DOWN, null)));
		//move left
		if(checkPossibleMove(currentPosition, Action.LEFT))
			possibleStates.put(new ComplexAction(Action.LEFT, null), new State<>(s,s.getCost()+1,currentPosition.getLeft(),new ComplexAction(Action.LEFT, null)));
		//move right
		if(checkPossibleMove(currentPosition, Action.RIGHT))
			possibleStates.put(new ComplexAction(Action.RIGHT, null), new State<>(s,s.getCost()+1,currentPosition.getRight(),new ComplexAction(Action.RIGHT, null)));

		return possibleStates;
	}

	@Override
	public boolean checkPossibleMove(Position currentPosition, Action action)
	{
		// TODO Auto-generated method stub
		Position firstPos=null;
		Position secondPos=null;
		Item firstItem=null;
		Item secondItem=null;

		switch(action)
		{//get the first and second positions
		case UP:
			firstPos=currentPosition.getUp();
			secondPos=firstPos.getUp();
			break;
			
		case DOWN:
			firstPos=currentPosition.getDown();
			secondPos=firstPos.getDown();
			break;
		case LEFT:
			firstPos=currentPosition.getLeft();
			secondPos=firstPos.getLeft();
			break;
		case RIGHT:
			firstPos=currentPosition.getRight();
			secondPos=firstPos.getRight();
			break;
		}
		if(getLevel().isValidPosition(firstPos))
			firstItem=getLevel().getItemByPosition(firstPos);
		if(getLevel().isValidPosition(secondPos))			
			secondItem=getLevel().getItemByPosition(secondPos);
		if(firstItem!=null)
		{
			//if empty or target
			if((firstItem.getChar()==' ')||((firstItem.getChar()=='o')))
				return true;
			//if wall
			else if (firstItem.getChar()=='#')
				return false;
			else//it's a box
			{
				return false;
			}

		}

		return false;
	}

	public char[][] getCharMap()
	{
		return charMap;
	}

	public void setCharMap(char[][] charMap)
	{
		this.charMap = charMap;
	}

	
}
