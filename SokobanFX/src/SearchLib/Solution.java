package SearchLib;

import java.util.LinkedList;

public class Solution
{
	LinkedList<Action> actionList;
	
	public void showSolution()
	{
		if (actionList!=null)
		{
			for (Action action : actionList)
			{
				System.out.println(action.toString()+" ");
			}
		}
	}

	public Solution(LinkedList<Action> solution) {
		super();
		this.actionList = solution;
	}

	public Solution() {
		super();
		// TODO Auto-generated constructor stub
		actionList=new LinkedList<>();
	}

	public LinkedList<Action> getActionList()
	{
		return actionList;
	}

	public void setActionList(LinkedList<Action> linkedList)
	{
		this.actionList = linkedList;
	}
	
	


	

}
