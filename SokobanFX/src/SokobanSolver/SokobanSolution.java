package SokobanSolver;

import java.util.LinkedList;
import java.util.List;

import SearchLib.Action;
import StripsLib.PlanAction;

public class SokobanSolution
{
	private List<Action> solution;

	public SokobanSolution(List<PlanAction> solutionList) {
		super();
		solution=new LinkedList<>();
		for (PlanAction pa : solutionList)
		{
			for(Action action : pa.getSubActions())
			{
				solution.add(action);
			}
		}
	}
	public void print()
	{
		System.out.println("Solution is: ");
		System.out.println(toString());
	}
	public String toString()
	{
		String str="";
		for(Action a : solution)
		{
			str+=a.toString();
			str+=" ";
		}
		return str;
	}
	
	
	

}
