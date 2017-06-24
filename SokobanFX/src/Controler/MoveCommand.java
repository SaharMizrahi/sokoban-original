package Controler;

import Model.Data.Box;
import Model.Data.Item;
import Model.Data.Level;
import Model.Data.Position;
import Model.Data.Wall;
import Model.Policy.MySokobanPolicy;

public class MoveCommand extends FunctionalCommand implements Command {
	private MySokobanPolicy msp;

	public MoveCommand(Level lev) {
		super(lev);
		// TODO Auto-generated constructor stub
		msp=new MySokobanPolicy();
	}
	public void execute() throws Exception
	{
		System.out.println("move");
		Item A=null;
		Item B=null;
		Item C=null;
		Position posB=null,posC=null;
		Position temp;
		int howManyMoves=0;
		String []s=this.getStr().split(" ");
		A=this.getLev().getCharacter();
		//this.getLev().getCharacterList().remove(A);
		switch (s[1].toLowerCase())
		{
			case "up":
			{
				posB=A.getPos().getUp();
				posC=posB.getUp();		
				
				break;
			}
			case "down":
				posB=A.getPos().getDown();
				posC=posB.getDown();	
				
				break;
			case "right":
				posB=A.getPos().getRight();
				posC=posB.getRight();	
				break;
			case "left":
				posB=A.getPos().getLeft();
				posC=posB.getLeft();	
				break;
			default:
				throw new Exception("Undefined direction");
				
				
		}
		B=this.getLev().getItemByPosition(posB);
		C=this.getLev().getItemByPosition(posC);
		if(B!=null)
		{
		switch(B.getChar())
		{
		case ' ':
			howManyMoves=1;
			break;
		case '@':
			
			howManyMoves=2;
			break;
		case '$':
			howManyMoves=2;
			break;
		case 'o':
			howManyMoves=1;
			break;
		case '#':
			howManyMoves=0;
			break;
		}
		if(howManyMoves==2)
		{
			if(C!=null)
			{
				if(C.getChar()=='#'||C.getChar()=='$'||C.getChar()=='@')
				{
					howManyMoves=0;
				}
				else
				{
					temp=B.getPos();
					this.getLev().setInPlace(B, C.getPos());
					this.getLev().setInPlace(A, temp);
					this.getLev().setStepsCounter(this.getLev().getStepsCounter()+1);
					this.getmM().setCurrentLevel(this.getLev());
				}
			}
		}
		else if(howManyMoves==1)
		{
			this.getLev().setInPlace(A ,posB);
			this.getLev().setStepsCounter(this.getLev().getStepsCounter()+1);
			this.getmM().setCurrentLevel(this.getLev());

		}

		}
		
		
		/*if (posB==null)
		{
			B=null;
		}
		if (!this.getLev().checkPosition(posB))
		{
			B=null;
			C=null;
		
		}
		else
			B=this.getLev().getItemByPosition(posB);
		 if (!this.getLev().checkPosition(posC))
				C=null;
		 else
			 C=this.getLev().getItemByPosition(posC);
		
			howManyMoves=this.checkIfPossible(A,B,C);
			if (howManyMoves==1)
			{
				this.getLev().setInPlace(A, B.getPos());
				this.getLev().setStepsCounter(this.getLev().getStepsCounter()+1);
				this.getmM().setChanged(true);
			}
				
			else if (howManyMoves==2)
			{
				this.getmM().setChanged(true);

				temp=B.getPos();
				this.getLev().setInPlace(B, C.getPos());
				this.getLev().setInPlace(A, temp);
				this.getLev().setStepsCounter(this.getLev().getStepsCounter()+1);
				
			}
			else
			{
				this.getmM().setChanged(false);

			}
	
			
		
		
			
		
		this.getLev().getCharacterList().add(A);
		//print the level to the console
		//this.getLev().print();
		
	
	*/
	}
	
	public int checkIfPossible(Item A,Item B,Item C)
	{
		MySokobanPolicy MSP=new MySokobanPolicy();
		if (B==null)
			return 0;
		if (C==null)
		{
			if ((B instanceof Box)||
					(B instanceof Wall))
				return 0;
			else
				return 1;
		}
		if (B instanceof Box)
		{
			if (C instanceof Box)
				return 0;
			if (C instanceof Wall )
			{
				if (MSP.goThrowWalls())
					return 1;
				else
					return 0;
			}
			return 2;
		}
		if (B instanceof Wall)
		{
			if ( MSP.goThrowWalls())
				return 1;
			else
				return 0;
		}
		return 1;
		
	}
}

	


