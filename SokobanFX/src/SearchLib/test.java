package SearchLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import Model.Data.Level2D;
import Model.Data.MyTextLevelLoader;
import Model.Data.Position;

public class test
{

	public static void main(String[] args)
	{
		MyTextLevelLoader loader=new MyTextLevelLoader();
		Level2D level=null;
		Solution solution=null;
		try {
			level=(Level2D) loader.loadLevel(new FileInputStream(new File("resources/Levels/test.txt")));
			level.print();
			
			BFS pbfs= new BFS<State<Position>>();
			BFS bbfs= new BFS<State<Position>>();
			PlayerSearchable ps=new PlayerSearchable(level.getCharacter().getPos(), new Position(0,0), level);
			BoxSearchable bs=new BoxSearchable(level.getBoxList().get(0).getPos(),level.getDestinyList().get(0).getPos(), level, pbfs, ps);

				solution=bbfs.search(bs);

				
			System.out.println("solution: ");

			solution.showSolution();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	
		
		
		



	}
}
