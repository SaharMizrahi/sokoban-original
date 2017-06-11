package SokobanSolver;

import java.util.List;

import Model.Data.Level2D;
import StripsLib.PlanAction;
import StripsLib.Strips;

public class SokobanSolver
{
	private SokobanSolution solution;
	public SokobanSolution solveLevel(Level2D level)
	{
		PlannableSokoban ps=new PlannableSokoban(level);
		Strips strips=new Strips();
		List<PlanAction> list=strips.plan(ps);
		solution=new SokobanSolution(list);
		
		return solution;
		
	}

}
