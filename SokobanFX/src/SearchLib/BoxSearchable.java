package SearchLib;

import java.util.HashMap;

import Model.Data.Item;
import Model.Data.Level2D;
import Model.Data.Position;

public class BoxSearchable extends CommonSearchable
{
	private Searcher<Position> searcher;
	private PlayerSearchable playerSearchable;
	private Position playerPosition;
	private char[][] charMap;

	

	public BoxSearchable(Position fromPosition, Position toPosition, Level2D level,
			Searcher<Position> searcher,PlayerSearchable playerSearchable) {
		super(fromPosition, toPosition, level);
		// TODO Auto-generated constructor stub
		this.searcher=searcher;
		this.playerSearchable=playerSearchable;
		this.playerPosition=this.getLevel().getCharacter().getPos();
		this.setPlayerPosition(level.getCharacter().getPos());
		initCharMap();
	}
	public void initCharMap()
	{
		
		Item[][] itemMap=this.getLevel().getMap();
		if(this.getLevel()!=null)
		{
			int width=this.getLevel().getWidth();
			int hight=this.getLevel().getLength();
			charMap=new char[hight][width];
			for(int i=0;i<hight;i++)
				for(int j=0;j<width;j++)
				{
					
					charMap[i][j]=itemMap[i][j].getChar();
					if(charMap[i][j]=='@')
					{
						if(!this.getFromPosition().equals(new Position(i,j)))
							charMap[i][j]='#';
						
							
						
					}

				}
		}
	}
	public char[][] getCharMap()
	{
		return charMap;
	}

	public void setCharMap(char[][] charMap)
	{
		this.charMap = charMap;
	}

	public Position getPlayerNeededPosition(Position boxPos,Action action)
	{
		switch (action)
		{
			case UP:
				return boxPos.getDown();
			case DOWN:
				return boxPos.getUp();
			case RIGHT:
				return boxPos.getLeft();
			case LEFT:
				return boxPos.getRight();
		}
		return null;
	}
	public Position getBoxNextPosition(Position position,Action action)
	{
		
		switch(action)
		{
		case UP:
			return position.getUp();
		case DOWN:
			return position.getDown();
		case RIGHT:
			return position.getRight();
		case LEFT:
			return position.getLeft();
		}
		return null;
		
	}

	@Override
	public HashMap<ComplexAction, State<Position>> getAllPossibleStates(State<Position> s)
	{
		// TODO Auto-generated method stub
		if(s.getCameFrom()==null)//it's the initial state
		{
			this.setPlayerPosition(this.getLevel().getCharacter().getPos());
		}
		else//it;s a new state
		{
			this.setPlayerPosition(s.getCameFrom().getState());
		}
		
		
		Solution playerPath=null;
		playerSearchable.setFromPosition(playerPosition);
		playerSearchable.setLevel(getLevel());
		State<Position> state=null;
		Position boxNextPos=null;
		ComplexAction ca=null;
		HashMap<ComplexAction, State<Position>> possibleStates=new HashMap<>();
		Position boxPos=s.getState();
		for(Action action: Action.values())
		{
			if(this.checkPossibleMove(boxPos, action))
			{
				playerSearchable.setFromPosition(playerPosition);
				playerSearchable.setToPosition(this.getPlayerNeededPosition(boxPos, action));
				playerSearchable.setLevel(getLevel());
				//System.out.println("player is at: "+playerSearchable.getFromPosition());
				//System.out.println("player go to"+playerSearchable.getToPosition());
				playerPath=searcher.search(playerSearchable);
				if(playerPath!=null)
				{
					boxNextPos=this.getBoxNextPosition(boxPos, action);
					ca=new ComplexAction(action, playerPath.getActionList());
					state=new State<Position>(s, s.getCost()+1,boxNextPos , ca);
					possibleStates.put(ca, state);
					
				}
			}
		}
		return possibleStates;
		
		

	}



	@Override
	public boolean checkPossibleMove(Position currentPosition, Action action)
	{
		// TODO Auto-generated method stub
		Position playerPos=null;
		Position goalPos=null;
		switch(action)
		{
		case UP:
			playerPos=currentPosition.getDown();
			goalPos=currentPosition.getUp();
			break;
		case DOWN:
			playerPos=currentPosition.getUp();
			goalPos=currentPosition.getDown();

			break;
		case RIGHT:
			playerPos=currentPosition.getLeft();
			goalPos=currentPosition.getRight();
			break;
		case LEFT:
			playerPos=currentPosition.getRight();
			goalPos=currentPosition.getLeft();
			break;
		}
		if(this.getLevel().isValidPosition(playerPos)&&this.getLevel().isValidPosition(goalPos))//it's not out of bounds
		{
			
			//check if player can go there
			if(charMap[playerPos.getRow()][playerPos.getRow()]==' '||charMap[playerPos.getRow()][playerPos.getRow()]=='o'||charMap[playerPos.getRow()][playerPos.getRow()]=='A')
			{
				if(charMap[goalPos.getRow()][goalPos.getRow()]==' '||charMap[goalPos.getRow()][goalPos.getRow()]=='o'||charMap[goalPos.getRow()][goalPos.getRow()]=='A')
					return true;
			}

			

		}

		return false;
		
		
		

	}

	public Searcher<Position> getSearcher()
	{
		return searcher;
	}

	public void setSearcher(Searcher<Position> searcher)
	{
		this.searcher = searcher;
	}

	public PlayerSearchable getPlayerSearchable()
	{
		return playerSearchable;
	}

	public void setPlayerSearchable(PlayerSearchable playerSearchable)
	{
		this.playerSearchable = playerSearchable;
	}

	public Position getPlayerPosition()
	{
		return playerPosition;
	}

	public void setPlayerPosition(Position playerPosition)
	{
		this.playerPosition = playerPosition;
	}
	

}
