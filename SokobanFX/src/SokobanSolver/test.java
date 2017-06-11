package SokobanSolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import Model.Data.Level2D;
import Model.Data.MyTextLevelLoader;
import StripsLib.PlanAction;
import StripsLib.Strips;

public class test
{

	public static void main(String[] args)
	{
		Level2D level=null;
		try {
			level=(Level2D) new MyTextLevelLoader().loadLevel(new FileInputStream(new File(args[0])));
			PlannableSokoban ps=new PlannableSokoban(level);
			Strips strips=new Strips();
			List<PlanAction> list=strips.plan(ps);
			SokobanSolution solution=new SokobanSolution(list);
			System.out.println(solution.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	
		
		
		



	}
}
