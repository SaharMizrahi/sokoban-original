package StripsLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import Model.Data.Level2D;
import Model.Data.MyTextLevelLoader;
import SokobanSolver.PlannableSokoban;

public class test
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		LinkedList<PlanAction> myPlan=null;
		MyTextLevelLoader loader=new MyTextLevelLoader();
		Level2D level=null;
		try {
			level=(Level2D) loader.loadLevel(new FileInputStream(new File("resources/Levels/test.txt")));
			level.print();
			PlannableSokoban ps=new PlannableSokoban(level);
			System.out.println("kb: "+ps.getKnowledgebase().toString());
			System.out.println("goal: "+ps.getGoal().toString());
			Strips s=new Strips();
			myPlan=(LinkedList<PlanAction>) s.plan(ps);
			if(myPlan!=null)
			{
				System.out.println("The Solution Is:");
				for(PlanAction pa : myPlan)
				{
					System.out.println(pa.getSubActions());
				}
			}
			if(myPlan!=null)
			{
				
			}
			//System.out.println(ps.getGoal().toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
