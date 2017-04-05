package Controler;

import Model.Data.Box;
import Model.Data.Item;
import Model.Data.Level;
import Model.Data.Position;
import Model.Data.Wall;
import Model.Policy.MySokobanPolicy;

public class MoveCommand extends FunctionalCommand implements Command {

	public MoveCommand(Level lev) {
		super(lev);
		// TODO Auto-generated constructor stub
	}
	public void execute() throws Exception
	{
		
		Item A=null;
		Item B=null;
		Item C=null;
		Position posB=null,posC=null;
		Position temp;
		int howManyMoves=0;
		String []s=this.getStr().split(" ");
		A=this.getLev().getCharacter();
		this.getLev().getCharacterList().remove(A);
		switch (s[1].toLowerCase())
		{
			case "up":
			{
				posB=A.getPos().getForward();
				posC=posB.getForward();		
				
				break;
			}
			case "down":
				posB=A.getPos().getBackward();
				posC=posB.getBackward();	
				
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
		if (posB==null)
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

	


